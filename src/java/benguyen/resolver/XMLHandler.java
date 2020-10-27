/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.resolver;

import benguyen.constants.PathConstant;
import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Gabriel Nguyen
 */
public class XMLHandler implements Serializable{
    public static Document parseHTMLSourceToXMLDOM(String source) {
        Document doc = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
    
    public static <T> T unmarshall(String str, String schemaPath, Class<?> obj){
        try {
            Unmarshaller un = getUnmarshaller(obj);
            if(!schemaPath.equals("")){
                Validator validator = getSchema(schemaPath).newValidator();
                validator.validate(new SAXSource(new InputSource(new StringReader(str))));
            }
            return (T) un.unmarshal(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)));
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    public static Unmarshaller getUnmarshaller(Class<?> obj) throws JAXBException{
        JAXBContext jaxb = JAXBContext.newInstance(obj);
        return jaxb.createUnmarshaller();
    }
    
    public static Schema getSchema(String path) throws SAXException{
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return factory.newSchema(new File(path));
    }
    public static void generateClassFromSchema(String filePath, String location) throws IOException {
        String sourcePackage = "src/java";

        SchemaCompiler schemaCompiler = XJC.createSchemaCompiler();
        schemaCompiler.setErrorListener(new ErrorListener() {
            @Override
            public void error(SAXParseException saxpe) {
                System.out.println("error: " + saxpe.getMessage());
            }

            @Override
            public void fatalError(SAXParseException saxpe) {
                System.out.println("error: " + saxpe.getMessage());
            }

            @Override
            public void warning(SAXParseException saxpe) {
                System.out.println("error: " + saxpe.getMessage());
            }

            @Override
            public void info(SAXParseException saxpe) {
                System.out.println("error:  " + saxpe.getMessage());
            }
        });
        schemaCompiler.forcePackageName(PathConstant.GENERATED_PACKAGE_NAME + location);

        File schema = new File(filePath);
        InputSource inputSource = new InputSource(schema.toURI().toString());
        schemaCompiler.parseSchema(inputSource);
        S2JJAXBModel model = schemaCompiler.bind();
        JCodeModel jCodeModel = model.generateCode(null, null);
        jCodeModel.build(new File(sourcePackage));
    }
}
