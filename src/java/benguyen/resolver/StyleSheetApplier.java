/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.resolver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Gabriel Nguyen
 */
public class StyleSheetApplier {

    private TransformerFactory factory = TransformerFactory.newInstance();

    private Transformer getTransformer(String styleSheet) throws TransformerConfigurationException{
        StreamSource styleSheetFile = new StreamSource(styleSheet);
        Templates templates = factory.newTemplates(styleSheetFile);
        return templates.newTransformer();
    }
    
    private ByteArrayOutputStream applyStyleSheet(String styleSheet, InputStream xmlStream) 
            throws TransformerConfigurationException, TransformerException{
        Transformer transformer = getTransformer(styleSheet);
        StreamSource xmlFile = new StreamSource(xmlStream);
        StreamResult resultStream = new StreamResult(new ByteArrayOutputStream());
        transformer.transform(xmlFile, resultStream);
        return (ByteArrayOutputStream) resultStream.getOutputStream();
    }
    
    public ByteArrayOutputStream applyStyleSheet(String styleSheet, String xmlContent) throws TransformerException{
        return applyStyleSheet(styleSheet, new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8)));
    }
}
