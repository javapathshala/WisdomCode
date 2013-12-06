/**
 * 
 */
package com.jp.service.client.rpc.handler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xml.security.exceptions.XMLSecurityException;
import org.apache.xml.security.keys.KeyInfo;
import org.apache.xml.security.keys.keyresolver.KeyResolverException;
import org.apache.xml.security.signature.XMLSignature;
import org.apache.xml.security.signature.XMLSignatureException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author dchadha
 * 
 */
public class validateCert {

	public void validateSignedMessage(Document document) {
		// org.apache.xml.security.Init.init();
		// String signatureFileName = "SignedRequest.xml";
		// javax.xml.parsers.DocumentBuilderFactory dbf =
		// javax.xml.parsers.DocumentBuilderFactory.newInstance();
		// dbf.setNamespaceAware(true);
		// dbf.setAttribute("http://xml.org/sax/features/namespaces",
		// Boolean.TRUE);
		try {
			org.apache.xml.security.Init.init();
			// File f = new File(signatureFileName);
			// System.out.println("Verifying " + signatureFileName);
			// javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
			// db.setErrorHandler(new
			// org.apache.xml.security.utils.IgnoreAllErrorHandler());
			// org.w3c.dom.Document doc = db.parse(new
			// java.io.FileInputStream(f));

//			StringWriter writer = new StringWriter();
//			Transformer transformer = TransformerFactory.newInstance().newTransformer();
//			transformer.transform(new DOMSource(soapRequest), new StreamResult(writer));
//			String xml = writer.toString();
//			System.out.println("#############################");
//			System.out.println(xml);
//			System.out.println("#############################");
		//	Document document = createDocumentFromSOAP(soapRequest);
			Element rootEl = document.getDocumentElement();
		Element item = (Element)rootEl.getChildNodes().item(0);
		System.out.println(item);
		
		Element nextSibling = (Element)item.getNextSibling();
		nextSibling.setIdAttribute("Id", true);
//		System.out.println(nextSibling);
		//	rootEl.setIdAttribute("Id", true);
			if (isSOAPSignaturePresent(document)) {
				System.out.println("Message is signed");
			System.out.println(document.getElementById("Body"));
				 authenticateUserSOAPSignature(document);
			}

//			Element sigElement = null;
//			NodeList nodes = document.getElementsByTagNameNS(org.apache.xml.security.utils.Constants.SignatureSpecNS,
//					"Signature");
//
//			if (nodes.getLength() != 0) {
//				System.out.println("Found " + nodes.getLength() + " Signature elements.");
//				sigElement = (Element) nodes.item(0);
//				XMLSignature signature = new XMLSignature(sigElement, "");
//
//				Element signedInfoElement = signature.getSignedInfo().getElement();
//				if (signedInfoElement == null) {
//					System.out.println("signedInfo element is " + signedInfoElement);
//				}
//
//				KeyInfo ki = signature.getKeyInfo();
//				if (ki != null) {
//					if (ki.containsX509Data()) {
//						System.out.println("Could find a X509Data element in the KeyInfo");
//
//						X509Certificate cert = ki.getX509Certificate();
//						if (cert != null) {
//							System.out.println("The XML signature is "
//									+ (signature.checkSignatureValue(cert) ? "valid (good)" : "invalid !!!!! (bad)"));
//						} else {
//							System.out.println("Did not find a Certificate");
//							PublicKey pk = signature.getKeyInfo().getPublicKey();
//							if (pk != null) {
//								System.out.println("The XML signatur is "
//										+ (signature.checkSignatureValue(pk) ? "valid (good)" : "invalid !!!!! (bad)"));
//							} else {
//								System.out.println("Did not find a public key, so I can't check the signature");
//							}
//						}
//					}
//				} else {
//					System.out.println("Did not find a KeyInfo");
//				}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected final boolean isSOAPSignaturePresent(Document document) {
		NodeList nodes = document.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#", "Signature");
		if (nodes.getLength() != 0) {
			System.out.println("Document is signed: " + document);
			return true;
		}
		return false;
	}

	private Document createDocumentFromSOAP(Node soapRequest) throws RequestSigningException {
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
			throw new RequestSigningException(e);
		} catch (SAXException e) {
			// logger.error(e.getMessage(), e);
			throw new RequestSigningException(e);
		} catch (IOException e) {
			// logger.error(e.getMessage(), e);
			throw new RequestSigningException(e);
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
	private ByteArrayInputStream translateReqToStream(Node soapRequest) throws RequestSigningException {
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
			throw new RequestSigningException(e);
		} catch (TransformerFactoryConfigurationError e) {
			// logger.error(e.getMessage(), e);
			throw new RequestSigningException(e);
		} catch (TransformerException e) {
			// logger.error(e.getMessage(), e);
			throw new RequestSigningException(e);
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

		NodeList nodes = document.getElementsByTagNameNS(org.apache.xml.security.utils.Constants.SignatureSpecNS,
				"Signature");

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

			NodeList referenceNodeList = signedInfoElement.getElementsByTagNameNS("http://www.w3.org/2000/09/xmldsig#",
					"Reference");
			if (referenceNodeList.getLength() != 1) {
				System.out
						.println("The ds:Refrence element either is missing or occures more than once. This is forbidden.");
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
					System.out
							.println("Client certificate was not found in the SOAP Header. X509Data element is missing");
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

//#####

////Find Signature element
//NodeList nl = signedDocument.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
//
//
//
//// Create a DOMValidateContext and specify a KeyValue KeySelector and document context
//DOMValidateContext valContext = new DOMValidateContext(new KeyValueKeySelector(), nl.item(0)); 
//
//// Create a DOM XMLSignatureFactory that will be used to unmarshal the 
//// document containing the XMLSignature 
//XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
//
//// unmarshal the XMLSignature
//XMLSignature signature = fac.unmarshalXMLSignature(valContext);
//
//boolean coreValidity = signature.validate(valContext);                                      
//
//// Check core validation status
//if (coreValidity == false) {
//    System.out.println("Signature failed core validation"); 
//    boolean sv = signature.getSignatureValue().validate(valContext);
//    System.out.println("signature validation status: " + sv);
//    // check the validation status of each Reference
//    Iterator i = signature.getSignedInfo().getReferences().iterator();
//    for (int j=0; i.hasNext(); j++) {
//        Reference ref = (Reference) i.next();
//        boolean refValid = ref.validate(valContext);
//        System.out.println("ref["+j+"] validity status: " + refValid);
//    }
//} else {
//    System.out.println("Signature passed core validation");
//}
