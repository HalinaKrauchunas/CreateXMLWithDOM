package create;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;

public class CreateXMLWithDOM {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {

        List<Customer> data = DataProvider.getData(DataProvider.SMALL);

        DOMCreator domCreator = new DOMCreator();
        Document document = domCreator.createXMLDoc(data);

        OutputToString(document);

        OutputAsFile(document, "D:\\XMLWithDOM\\CreateDocumentDOM\\src\\main\\resources\\myXML.xml");
    }

    private static void OutputToString(Document document) throws TransformerException {

        DOMSource domSource = new DOMSource(document);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);

        Transformer transformer = getTransformer();
        transformer.transform(domSource, result);
        String xmlString = writer.toString();

        System.out.println(xmlString);
    }

    private static void OutputAsFile(Document document, String filename) throws TransformerException {

        DOMSource domSource = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filename));
        getTransformer().transform(domSource, result);
    }

    private static Transformer getTransformer() throws TransformerConfigurationException {

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        return transformer;
    }
}
