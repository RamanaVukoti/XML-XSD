package validations;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class DOmParsing {
	static File file = new File(
			"C:/SVNWorkSpace/Xmlvalidations/src/validations/outputt.xml");

	public static void main(String[] arg) {
		getDataOfSingleElement();
		loopEntireDoc();

	}

	public static void getDataOfSingleElement() {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("Project");

			System.out
					.println("-----------------here is the elements-----------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("Nmae-- : "
							+ eElement.getElementsByTagName("Nmae").item(0)
									.getTextContent());
					System.out.println("Id-- : "
							+ eElement.getElementsByTagName("Id").item(0)
									.getTextContent());
					System.out.println("POC-- : "
							+ eElement.getElementsByTagName("POC").item(0)
									.getTextContent());
					System.out.println("priority-- : "
							+ eElement.getElementsByTagName("priority").item(0)
									.getTextContent());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loopEntireDoc() {

		try {

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			Document doc = dBuilder.parse(file);

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());

			if (doc.hasChildNodes()) {

				printNote(doc.getChildNodes());

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void printNote(NodeList nodeList) {

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				System.out.println("\nNode Name =" + tempNode.getNodeName()
						+ " [OPEN]");
				System.out.println("Node Value =" + tempNode.getTextContent());

				if (tempNode.hasAttributes()) {

					NamedNodeMap nodeMap = tempNode.getAttributes();

					for (int i = 0; i < nodeMap.getLength(); i++) {

						Node node = nodeMap.item(i);
						System.out.println("attr name : " + node.getNodeName());
						System.out.println("attr value : "
								+ node.getNodeValue());

					}

				}

				if (tempNode.hasChildNodes()) {

					
					printNote(tempNode.getChildNodes());

				}

				System.out.println("Node Name =" + tempNode.getNodeName()
						+ " [CLOSE]");

			}

		}
	}

}
