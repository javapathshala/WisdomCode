/*
 * File: WSServerHandler.java Date: 10-Oct-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.service.rpc.handlers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Hashtable;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axis.AxisFault;
import org.apache.axis.Handler;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.keyresolver.KeyResolverException;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignatureException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author dchadha
 */
public class WSServerHandler implements Handler {

	// @Override
	// public QName[] getHeaders() {
	// return null;
	// }
	//
	// @Override
	// public boolean handleRequest(MessageContext context) {
	// // SOAPMessageContext ctx = (SOAPMessageContext) context;
	// // SOAPMessage message = ctx.getMessage();
	// // try {
	// // addXMLSignatures(ctx, message);
	// // } catch (RequestSigningException requestSigningException) {
	// // // //logger.error("Error while signing  request ",
	// // // requestSigningException);
	// // attacheErrorMessage(message, requestSigningException.getMessage());
	// // }
	// System.out.println("SOAP Request: I am in Request");
	// return super.handleRequest(context);
	// }
	//
	// @Override
	// public boolean handleResponse(MessageContext context) {
	// System.out.println("SOAP Response: I am in Response");
	// return super.handleResponse(context);
	// }
	//
	// @Override
	// public boolean handleFault(MessageContext context) {
	// return super.handleFault(context);
	// }

	/**
	 * 
	 */
	private static final long serialVersionUID = 2040702296629141824L;

	@Override
	public boolean canHandleBlock(QName arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateWSDL(org.apache.axis.MessageContext arg0) throws AxisFault {
		// TODO Auto-generated method stub

	}

	@Override
	public Element getDeploymentData(Document arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getOption(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hashtable getOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getUnderstoodHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void invoke(MessageContext messageContext) throws AxisFault {
		Message message = null;
		if (!messageContext.getPastPivot()) {
			message = messageContext.getRequestMessage();
		} else {
			message = messageContext.getResponseMessage();
		}

		// SOAPMessage soapMessage = (SOAPMessage) currentMessage;
		// SOAPMessage rsMess=(SOAPMessage)responseMessage;
		boolean valid = true;
		try {
			SOAPHeader hh = message.getSOAPPart().getEnvelope().getHeader();

			SOAPEnvelope soapEnvelope = message.getSOAPEnvelope();
			SOAPBody soapBody = soapEnvelope.getBody();

			Document document = soapEnvelope.getAsDocument();
			System.out.println("###################################################################################################");
			System.out.println("###################################################################################################");
			dumpDocument(document);
			Element rootEl = document.getDocumentElement();
			Element item = (Element) rootEl.getChildNodes().item(0);
			System.out.println(item);

			Element nextSibling = (Element) item.getNextSibling();
			nextSibling.setIdAttribute("Id", true);
			System.out.println(nextSibling);
			// rootEl.setIdAttribute("Id", true);
			validateCert va=new validateCert();
			valid=va.authenticateUserSOAPSignature(document);
			
//			if (isSOAPSignaturePresent(document)) {
//				System.out.println("Message is signed");
//				System.out.println(document.getElementById("Body"));
//				valid = authenticateUserSOAPSignature(document);
//			}

			if (!valid) {
				throw new AxisFault("Invalid Signature");
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SOAPException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Node getUnSignedRequest(SOAPPart soapPart) {
		// logger.info("Get the root node of SOAP Request");
		Source source;
		Node soapRequest = null;
		Document doc = null;
		try {
			source = soapPart.getContent();
			if (source instanceof DOMSource) {
				soapRequest = ((DOMSource) source).getNode();
			} else if (source instanceof SAXSource) {
				InputSource inSource = ((SAXSource) source).getInputSource();
				DocumentBuilderFactory dbf = getDocumentFactory();
				DocumentBuilder db = getDocumentBuilder(dbf);
				doc = db.parse(inSource);
				soapRequest = (Node) doc.getDocumentElement();
			}
		} catch (SOAPException e) {
			// logger.error("SOAP Exception occures !", e);
			// throw new RequestSigningException(e);
		} catch (ParserConfigurationException e) {
			// logger.error("Configuration error occured", e);
			// throw new RequestSigningException(e);
		} catch (SAXException e) {
			// logger.error("Exception while parsing the document", e);
			// throw new RequestSigningException(e);
		} catch (IOException e) {
			// logger.error("Exception due to IO", e);
			// throw new RequestSigningException(e);
		}
		// logger.info("Finished Getting Root Node of Request");
		return soapRequest;
	}

	private static void dumpDocument(Node root) {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
			transformer.transform(new DOMSource(root), new StreamResult(System.out));
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onFault(org.apache.axis.MessageContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setName(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOption(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOptions(Hashtable arg0) {
		// TODO Auto-generated method stub

	}

	protected final boolean isSOAPSignaturePresent(Document document) {
		NodeList nodes = document.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature");
		if (nodes.getLength() != 0) {
			System.out.println("Document is signed: " + document);
			return true;
		}
		return false;
	}

	private Document createDocumentFromSOAP(Node soapRequest) throws Exception {
		ByteArrayInputStream byteRequest = translateReqToStream(soapRequest);
		DocumentBuilderFactory dbf = getDocumentFactory();
		DocumentBuilder db;
		Document signedDocument = null;
		try {
			db = getDocumentBuilder(dbf);
			db.setErrorHandler(new org.apache.xml.security.utils.IgnoreAllErrorHandler());
			signedDocument = db.parse(byteRequest);
			// signedDocument = db.parse(new
			// ByteArrayInputStream(soapRequest.getTextContent().getBytes()));
		} catch (ParserConfigurationException e) {
			// logger.error(e.getMessage(), e);
			throw new Exception(e);
		} catch (SAXException e) {
			// logger.error(e.getMessage(), e);
			throw new Exception(e);
		} catch (IOException e) {
			// logger.error(e.getMessage(), e);
			throw new Exception(e);
		}
		return signedDocument;
	}

	/**
	 * Translate SOAP Request to Stream
	 * 
	 * @param soapRequest
	 * @return
	 * @throws RequestSigningException
	 */
	private ByteArrayInputStream translateReqToStream(Node soapRequest) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			// transformer.setURIResolver(resolver);
			Result outputTarget = new StreamResult(outputStream);
			transformer.transform(new DOMSource(soapRequest), outputTarget);
		} catch (TransformerConfigurationException e) {
			// logger.error(e.getMessage(), e);
			throw new Exception(e);
		} catch (TransformerFactoryConfigurationError e) {
			// logger.error(e.getMessage(), e);
			throw new Exception(e);
		} catch (TransformerException e) {
			// logger.error(e.getMessage(), e);
			throw new Exception(e);
		}
		return new ByteArrayInputStream(outputStream.toByteArray());
	}

	/**
	 * Get Document Factory
	 * 
	 * @return
	 */
	private DocumentBuilderFactory getDocumentFactory() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setAttribute("http://xml.org/sax/features/namespaces", Boolean.TRUE);

		return dbf;
	}

	/**
	 * Get Document Builder
	 * 
	 * @param dbf
	 * @return
	 * @throws ParserConfigurationException
	 */
	private DocumentBuilder getDocumentBuilder(DocumentBuilderFactory dbf) throws ParserConfigurationException {
		return dbf.newDocumentBuilder();
	}

	protected final boolean authenticateUserSOAPSignature(Document document) {
		// this.//logger.enter();
		// messageContext.removeProperty("AUTHENTICATED_USER");

		NodeList nodes = document.getElementsByTagNameNS(org.apache.xml.security.utils.Constants.SignatureSpecNS, "Signature");

		if (nodes.getLength() != 0) {
			Element sigElement = (Element) nodes.item(0);
			XMLSignature signature;
			try {
				signature = new XMLSignature(sigElement, "");
			} catch (XMLSecurityException e) {
				System.out.println("Wrong format of digital signautre");
				return false;
			}

			Element signedInfoElement = signature.getSignedInfo().getElement();
			if (signedInfoElement == null) {
				System.out.println("Missing SignedInfo element");
				return false;
			}

			NodeList referenceNodeList = signedInfoElement.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Reference");
			if (referenceNodeList.getLength() != 1) {
				System.out.println("The ds:Refrence element either is missing or occures more than once. This is forbidden.");
				return false;
			}
			String uri = ((Element) referenceNodeList.item(0)).getAttribute("URI");
			if (!"#Body".equals(uri)) {
				System.out.println("ds:Reference element must contain URI set to #Body.");
				return false;
			}
			X509Certificate certificate;
			KeyInfo keyInfo = signature.getKeyInfo();
			if (keyInfo != null) {
				if (keyInfo.containsX509Data()) {
					// X509Certificate certificate;
					try {
						certificate = keyInfo.getX509Certificate();
					} catch (KeyResolverException e) {
						System.out.println("Could not read client certificate from the SOAP Header");
						return false;
					}

					if (certificate == null) {
						System.out.println("Client certificate was not found in the SOAP Header");
						return false;
					}
				} else {
					System.out.println("Client certificate was not found in the SOAP Header. X509Data element is missing");
					return false;
				}
			} else {
				System.out.println("Client certificate was not found in the SOAP Header. KeyInfo element is missing");
				return false;
			}
			// X509Certificate certificate;
			// try {
			// CertificateChecker.checkCertificate(certificate,
			// "Client certificate", 0L, this.globalConfigReader.getHostName(),
			// this.globalConfigReader.getInstanceId());
			// }
			// catch (CertificateCheckFailed certificateCheckFailed)
			// {
			// this.//logger.error(new Object[] {
			// "Client certificate is invalid" });
			// return false;
			// }

			// if (verifyCertificate(certificate, configuration)) {
			// String soapUserName = getSoapUserName(certificate,
			// configuration);
			// if (soapUserName != null) {
			// ProvGWUserInterface provGwUser =
			// this.userManagement.getUserBySoapUserName(soapUserName,
			// this.userManagementIntfType);
			// if (provGwUser != null) {
			// messageContext.setProperty("AUTHENTICATED_USER", provGwUser);
			// this.//logger.info(new Object[] {
			// "Client certificate was verified, SOAP user was mapped to Provisioning Gateway user"
			// });
			// } else {
			// this.//logger.error(new Object[] {
			// "Unknown user. User Management did not find SOAP user with folowing user name: ",
			// soapUserName });
			// return false;
			// }

			PublicKey publicKey = certificate.getPublicKey();
			System.out.println("VALIDATION PUBLIC KEY :: " + certificate.getPublicKey());
			if (publicKey != null) {
				try {
					boolean result = signature.checkSignatureValue(publicKey);
					if (result) {
						System.out.println("X509SecurityInterceptor: Digital signature is valid");
						return true;
					}
					System.out.println("X509SecurityInterceptor: Digital signature is invalid");
				} catch (XMLSignatureException e) {
					System.out.println("Error encountered during signature validation");
					return false;
				}
			}
			System.out.println("There is no public key in the client certificate");
			return false;
		}

		return false;
		// }

		// System.out.println("Siganture of the client certificate is invalid");
		// return false;
		// }

		// System.out. "No Signature element was found" });
		// return false;
	}

}
