package provider;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParameterReader {

    private String mProfile;
    private XPath mXPath;
    private XPathExpression mExpression;
    private Document mDocument;
    private Logger logger = Logger.getLogger(ParameterReader.class.getName());

    public ParameterReader() {
        setupParser();
        readParameters();
    }

    private void readParameters() {
        mProfile = readProfile();
    }

    private String readProfile(){
        try {
            mExpression = mXPath.compile("/parameters/browser/profile/text()");
            return (String) mExpression.evaluate(mDocument, XPathConstants.STRING);
        } catch (XPathExpressionException e){
            logger.log(Level.WARNING,
                    "There was an issue parsing profile. Please refer to the following error: " + e);
            return null;
        }
    }

    private void setupParser(){
        try {
            File param = new File("src/test/resources/config/parameters/parameters.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            mDocument = builder.parse(param);
            XPathFactory xFactory = XPathFactory.newInstance();
            mXPath = xFactory.newXPath();
        } catch (ParserConfigurationException | SAXException | IOException e){
            logger.log(Level.WARNING,
                    "There was an issue with setting up parser. Please refer to the following error: " + e);
        }
    }

    public String getProfile(){
        return mProfile;
    }
}
