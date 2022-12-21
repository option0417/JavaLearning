package tw.com.wd;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;


public class FamiPointUtils {

    public static void main(String[] args) throws Exception {
        FamiPointUtils.getUserPoint();
    }


    public static void getUserPoint() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.newDocument();

        Element rootElement = doc.createElement("XML");

        Element procCodeElement = doc.createElement("PROC_CODE");
        procCodeElement.setTextContent("2Q10");

        Element memberIdElement = doc.createElement("MEMBER_ID");
        memberIdElement.setTextContent("1451870360");

        Element pItemCodeElement = doc.createElement("PITEM_CODE");
        pItemCodeElement.setTextContent("9778887");

        Element securityCodeElement = doc.createElement("SECURITY_CODE");
        securityCodeElement.setTextContent("");

        doc.appendChild(rootElement);
        rootElement.appendChild(procCodeElement);
        rootElement.appendChild(memberIdElement);
        rootElement.appendChild(pItemCodeElement);
        rootElement.appendChild(securityCodeElement);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");


        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));

        //String output = writer.getBuffer().toString().replaceAll("\n|\r", "");

        System.out.println(writer.getBuffer().toString());

        System.out.println(doc.toString());
    }

}
