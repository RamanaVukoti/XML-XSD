package validations;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XmlValid {

	public static void main(String[] args) {
		String xsdPath = "C:/SVNWorkSpace/Xmlvalidations/src/validations/example.xsd";
		String xmlPath = "C:/SVNWorkSpace/Xmlvalidations/src/validations/NewFile.xml";
		
		System.out.println(validateXml(xsdPath, xmlPath));

	}

	public static boolean validateXml(String xsdPath, String xmlPath) {

		try {

			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File(xsdPath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlPath)));

		} catch (SAXException e) {

			System.out.println(e.getMessage());
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
