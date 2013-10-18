/*
 * File: XMLSignature.java Date: 09-Oct-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.service.client.rpc.handler;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author dchadha
 */
public class XMLSignatureHandler extends GenericHandler {

	private KeyStore.PrivateKeyEntry keyEntry;

	public XMLSignatureHandler() {

	}

	@Override
	public QName[] getHeaders() {
		return null;
	}

	@Override
	public boolean handleRequest(MessageContext context) {
		SOAPMessageContext ctx = (SOAPMessageContext) context;
		SOAPMessage message = ctx.getMessage();
		try {
			addXMLSignatures(ctx, message);
		} catch (RequestSigningException requestSigningException) {
			// ////logger.error("Error while signing  request ",
			// requestSigningException);
			attacheErrorMessage(message, requestSigningException.getMessage());
		}
		// System.out.println("SOAP Request: " + getStringMessage(context));
		return super.handleRequest(context);
	}

	@Override
	public boolean handleResponse(MessageContext context) {
		// System.out.println("SOAP Response: " + getStringMessage(context));
		return super.handleResponse(context);
	}

	@Override
	public boolean handleFault(MessageContext context) {
		// System.out.println("SOAP Fault: " + getStringMessage(context));
		return super.handleFault(context);
	}

	/**
	 * Add XML Signature to SOAP Message
	 * 
	 * @param context
	 * @param message
	 * @throws RequestSigningException
	 */
	private void addXMLSignatures(SOAPMessageContext context, SOAPMessage message) throws RequestSigningException {
		// logger.info("Adding Header to SOAP Request");
		try {
			SOAPPart soapPart = message.getSOAPPart();
			SOAPEnvelope soapEnvelope = createSoapEnvelope(soapPart);
			createSoapHeader(soapEnvelope);
			addSoapBody(soapEnvelope);
			Node root = getRoot(soapPart);
			// logger.info("Creating DOM XMLSignatureFactory -Enveloped signature");
			XMLSignatureFactory signFactory = XMLSignatureFactory.getInstance("DOM");
			// <ds:SignedInfo>
			SignedInfo signInfo = createSignInfo(signFactory);

			// Read X509 Certificate from KeyStore
			X509Certificate certificate = readCertificate();

			// Create KeyInfo
			KeyInfo keyInfo = createKeyInfo(signFactory, certificate);
			// get Signature
			XMLSignature signature = createSignature(signFactory, signInfo, keyInfo);
			// logger.info("Signature from Sign Info & Key Info objects created Successfully");

			PrivateKey privateKey = getPrivateKey();
			Element envelope = getFirstChildElement(root);
			Element header = getFirstChildElement(envelope);

			// Sign Message
			signMessage(signature, privateKey, header);
			dumpDocument(root);
		} catch (TransformerException e) {
			// logger.error("KeyStore File Not Found. " + e);
		}
	}

	/**
	 * Create a DOMSignContext & Sign the message.
	 * 
	 * @param signature
	 * @param privateKey
	 * @param header
	 * @throws RequestSigningException
	 */
	private void signMessage(XMLSignature signature, PrivateKey privateKey, Element header) throws RequestSigningException {
		// logger.info("Start Signing the Message");
		try {
			DOMSignContext sigContext = new DOMSignContext(privateKey, header);
			sigContext.putNamespacePrefix(XMLSignature.XMLNS, "ds");
			sigContext.setIdAttributeNS(getNextSiblingElement(header), "", "id");
			signature.sign(sigContext);
		} catch (MarshalException e) {
			// logger.error("Exception occured during the XML marshalling or unmarshalling process.",
			// e);
			throw new RequestSigningException(e);
		} catch (XMLSignatureException e) {
			// logger.error("Exception occured during the XML signature generation or validation process.",
			// e);
			throw new RequestSigningException(e);
		}
		// logger.info("Message Signing Successful");
	}

	/**
	 * Create SOAP Header
	 * 
	 * @param soapEnvelope
	 * @throws SOAPException
	 */
	private void createSoapHeader(SOAPEnvelope soapEnvelope) throws RequestSigningException {
		// logger.info("Creating SOAP Header");
		SOAPHeader soapHeader;
		try {
			soapHeader = soapEnvelope.getHeader();
			if (soapHeader == null) {
				soapHeader = soapEnvelope.addHeader();
			}
			// SOAP Header Elements
			soapHeader.addHeaderElement(soapEnvelope
					.createName("Signature", "SOAP-SEC", "http://schemas.xmlsoap.org/soap/security/2000-12"));
		} catch (SOAPException e) {
			// logger.error("Exception occured while adding to SOAP Header	",
			// e);
			throw new RequestSigningException(e);
		}
		// logger.info("SOAP Header Created Successful");
	}

	/**
	 * Modify SOAP Envelope
	 * 
	 * @param soapPart
	 * @return
	 * @throws SOAPException
	 */
	private SOAPEnvelope createSoapEnvelope(SOAPPart soapPart) throws RequestSigningException {
		// logger.info("Creating SOAP Envelope");
		SOAPEnvelope soapEnvelope = null;
		try {
			soapEnvelope = soapPart.getEnvelope();
			addToSoapEnvelope(soapEnvelope);
		} catch (SOAPException e) {
			// logger.error("Exception occured while adding to SOAP envelope",
			// e);
			throw new RequestSigningException(e);
		}
		// logger.info("SOAP Envelope Created Successful");
		return soapEnvelope;
	}

	/**
	 * Add Attribute to SOAP Body
	 * 
	 * @param soapEnvelope
	 * @throws SOAPException
	 */
	private void addSoapBody(SOAPEnvelope soapEnvelope) throws RequestSigningException {
		// logger.info("Adding Attribute to SOAP Body");
		try {
			SOAPBody soapBody = soapEnvelope.getBody();
			Name createName = soapEnvelope.createName("id", "", "");
			soapBody.addAttribute(createName, "Body");
		} catch (SOAPException e) {
			// logger.error("Exception occured while adding to SOAP Header	",
			// e);
			throw new RequestSigningException(e);
		}
		// logger.info("Adding Attribute to SOAP Body Successful");
	}

	/**
	 * Get the Root Node of SOAP Request
	 * 
	 * @param soapPart
	 * @return
	 * @throws SOAPException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private Node getRoot(SOAPPart soapPart) throws RequestSigningException {
		// logger.info("Get the root node of SOAP Request");
		Source source;
		Node root = null;
		Document doc = null;
		try {
			source = soapPart.getContent();
			if (source instanceof DOMSource) {
				root = ((DOMSource) source).getNode();
			} else if (source instanceof SAXSource) {
				InputSource inSource = ((SAXSource) source).getInputSource();
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				dbf.setNamespaceAware(true);
				DocumentBuilder db = null;
				db = dbf.newDocumentBuilder();
				doc = db.parse(inSource);
				root = (Node) doc.getDocumentElement();
			}
		} catch (SOAPException e) {
			// logger.error("SOAP Exception occures !", e);
			throw new RequestSigningException(e);
		} catch (ParserConfigurationException e) {
			// logger.error("Configuration error occured", e);
			throw new RequestSigningException(e);
		} catch (SAXException e) {
			// logger.error("Exception while parsing the document", e);
			throw new RequestSigningException(e);
		} catch (IOException e) {
			// logger.error("Exception due to IO", e);
			throw new RequestSigningException(e);
		}
		// logger.info("Finished Getting Root Node of Request");
		return root;
	}

	/**
	 * Get the Private Key
	 * 
	 * @return
	 */
	private PrivateKey getPrivateKey() {
		return keyEntry.getPrivateKey();
	}

	/**
	 * Create Signature from Sign Info & Key Info objects
	 * 
	 * @param signFactory
	 * @param signInfo
	 * @param keyInfo
	 * @return
	 */
	private XMLSignature createSignature(XMLSignatureFactory signFactory, SignedInfo signInfo, KeyInfo keyInfo) {
		// logger.info("Create Signature from Sign Info & Key Info objects");
		return signFactory.newXMLSignature(signInfo, keyInfo);
	}

	/**
	 * Create KeyInfo Object Tag
	 * 
	 * @param signFactory
	 * @param certificate
	 * @return
	 * @throws RequestSigningException
	 */
	private KeyInfo createKeyInfo(XMLSignatureFactory signFactory, X509Certificate certificate) throws RequestSigningException {
		// logger.info("Creation of KeyInfo Tag Started");
		KeyInfo keyInfo = null;
		try {
			// Create the KeyInfo containing the X509Data.
			KeyInfoFactory kif = signFactory.getKeyInfoFactory();
			List<Object> x509Content = new ArrayList<Object>();
			x509Content.add(certificate.getSubjectX500Principal().getName());
			x509Content.add(certificate);
			X509Data kv = kif.newX509Data(x509Content);
			KeyValue kvalue = kif.newKeyValue(certificate.getPublicKey());
			List<Object> content = new ArrayList<Object>();
			content.add(kv);
			content.add(kvalue);
			keyInfo = kif.newKeyInfo(content);
		} catch (KeyException e) {
			// logger.error("Exception occured in keystore key values", e);
			throw new RequestSigningException(e);
		}
		// logger.info("Creation of KeyInfo Tag Successful");
		return keyInfo;
	}

	/**
	 * Create a SignInfo Object Tag - Reference to the enveloped document (in
	 * this case, you are signing the body, so a URI of "#Body" signifies that
	 * 
	 * @param signFactory
	 * @return
	 */
	private SignedInfo createSignInfo(XMLSignatureFactory signFactory) throws RequestSigningException {
		// logger.info("Creation of SignInfo Tag Started");
		SignedInfo signedInfo = null;
		try {
			List<Transform> transformList = new ArrayList<Transform>();
			Transform transform = signFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null);
			transformList.add(transform);
			transform = signFactory.newTransform(CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS, (TransformParameterSpec) null);
			transformList.add(transform);

			DigestMethod newDigestMethod = signFactory.newDigestMethod(DigestMethod.SHA1, null);

			// Calculate Digest value on Soap Body

			Reference ref = signFactory.newReference("#Body", newDigestMethod, transformList, null, null);

			// Create the SignedInfo.
			SignatureMethod newSignatureMethod = signFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null);
			signedInfo = signFactory.newSignedInfo(
					signFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS, (C14NMethodParameterSpec) null),
					newSignatureMethod, Collections.singletonList(ref));
		} catch (NoSuchAlgorithmException e) {
			// logger.error("Requested Cryptographic algorithm is not available in the environment",
			// e);
			throw new RequestSigningException(e);
		} catch (InvalidAlgorithmParameterException e) {
			// logger.error("invalid or inappropriate algorithm parameters", e);
			throw new RequestSigningException(e);
		}
		// logger.info("Signed Info Tag Successful");
		return signedInfo;
	}

	/**
	 * Read Certificate from Keystore file provided.
	 * 
	 * @return
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnrecoverableEntryException
	 */
	private X509Certificate readCertificate() throws RequestSigningException {
		// logger.info("Reading Certificate");
		KeyStore ks;
		try {
			ks = KeyStore.getInstance(KeyStore.getDefaultType());
			String keyStoreLocation = getKeyStoreLocation();
			// BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
			// basicTextEncryptor.setPassword(System.getProperty("APP_ENCRYPTION_PASSWORD"));
			char[] keyPassword = getKeyPassword();
			String keyAlias = getKeyAlias();
			ks.load(new FileInputStream(keyStoreLocation), keyPassword);
			keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(keyAlias, new KeyStore.PasswordProtection(keyPassword));
		} catch (KeyStoreException e) {
			// logger.error("Exception occured in keystore", e);
			throw new RequestSigningException(e);
		} catch (NoSuchAlgorithmException e) {
			// logger.error("Requested Cryptographic algorithm is not available in the environment",
			// e);
			throw new RequestSigningException(e);
		} catch (CertificateException e) {
			// logger.error("Issues with certificate key", e);
			throw new RequestSigningException(e);
		} catch (FileNotFoundException e) {
			throw new RequestSigningException("KeyStore File Not Found. Check the location of KeyStore File", e);
		} catch (IOException e) {
			throw new RequestSigningException("KeyStore File Not Found. Check the location of KeyStore File", e);
		} catch (UnrecoverableEntryException e) {
			// logger.error("Some issues with KeyEntry in Keystore", e);
			throw new RequestSigningException(e);
		}
		// logger.info("Certificate Read  Successfully");
		return (X509Certificate) keyEntry.getCertificate();
	}

	/**
	 * Add Attribute to SOAP Envelope
	 * 
	 * @param soapEnvelope
	 * @throws SOAPException
	 */
	private void addToSoapEnvelope(SOAPEnvelope soapEnvelope) throws SOAPException {
		// logger.info("Add Attribute to SOAP Envelope");
		soapEnvelope.addAttribute(soapEnvelope.createName("xmlns:xsd"), "http://www.w3.org/2001/XMLSchema");
		soapEnvelope.addAttribute(soapEnvelope.createName("xmlns:xsi"), "http://www.w3.org/2001/XMLSchema-instance");
		// logger.info("Adding Attribute to SOAP Envelope Successful");
	}

	/**
	 * Print Response- Will be deleted from final version
	 * 
	 * @param context
	 * @return
	 * @throws RequestSigningException
	 */
	private String getStringMessage(SOAPMessageContext context) throws RequestSigningException {
		String res = null;
		try {
			// SOAP Message
			SOAPMessage message = context.getMessage();
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			message.writeTo(stream);
			byte[] items = stream.toByteArray();
			res = new String(items);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Print SOAP Message - Will be deleted from final version
	 * 
	 * @param root
	 * @throws TransformerException
	 */
	private void dumpDocument(Node root) throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(new DOMSource(root), new StreamResult(System.out));
	}

	private String getKeyAlias() {
		return "tomcat";
	}

	private char[] getKeyPassword() {
		return "dimit123".toCharArray();
	}

	private String getKeyStoreLocation() {
		return "D:\\certi\\Dimit.jks";
	}

	private Element getFirstChildElement(Node node) {
		Node child = node.getFirstChild();
		while ((child != null) && (child.getNodeType() != Node.ELEMENT_NODE)) {
			child = child.getNextSibling();
		}
		return (Element) child;
	}

	private Element getNextSiblingElement(Node node) {
		Node sibling = node.getNextSibling();
		while ((sibling != null) && (sibling.getNodeType() != Node.ELEMENT_NODE)) {
			sibling = sibling.getNextSibling();
		}
		return (Element) sibling;
	}

	private void attacheErrorMessage(SOAPMessage errorMessage, String cause) {
		try {
			SOAPBody soapBody = errorMessage.getSOAPPart().getEnvelope().getBody();
			SOAPFault soapFault = soapBody.addFault();
			soapFault.setFaultString(cause);
			// throw new SOAPFaultException(soapFault);
		} catch (SOAPException e) {
			// //logger.info("Soap exception occures!");
		}
	}
}
