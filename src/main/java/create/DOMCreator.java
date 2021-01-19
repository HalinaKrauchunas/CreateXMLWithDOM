package create;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.text.*;
import java.util.*;

public class DOMCreator {

    @SuppressWarnings("unused")
    private static final String XMLDATEFORMAT = "MM/dd/yyyy";
    Document document;

    public Document createXMLDoc(List<Customer> data) throws ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();

        Element root = document.createElement("customers");
        document.appendChild(root);

        for (Customer customer : data) {
            Element customerElement = addElement(root, "customer", "");
            root.appendChild(customerElement);
            customerElement.setAttribute(Customer.ID, Integer.toString(customer.getId()));

            addElement(customerElement, Customer.NAME, customer.getName());
            addElement(customerElement, Customer.PHONE, customer.getPhone());
            addElement(customerElement, Customer.AGE, (Integer.toString(customer.getAge())));
            addElement(customerElement, Customer.BALANCE, customer.getBalance());
            addElement(customerElement, Customer.ACTIVE, (Boolean.toString(customer.isActive())));

            Element about = addElement(customerElement, Customer.ABOUT, "");
            CDATASection cdata = document.createCDATASection(customer.getAbout());
            about.appendChild(cdata);

            DateFormat df = new SimpleDateFormat(XMLDATEFORMAT);
            addElement(customerElement, Customer.JOINED, df.format(customer.getJoined()));
        }
        return document;
    }

    private Element addElement(Element parent, String elementName, String textValue) {

        Element childElement = document.createElement(elementName);
        childElement.setTextContent(textValue);
        parent.appendChild(childElement);
        return childElement;
    }
}
