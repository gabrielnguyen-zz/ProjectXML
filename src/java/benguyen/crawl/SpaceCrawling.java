/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.crawl;

import benguyen.client.NewClient;
import benguyen.constants.DomainConstant;
import static benguyen.crawl.CrawHelper.getContent;
import benguyen.generated.Spaces;
import benguyen.models.Space;
import benguyen.resolver.StyleSheetApplier;
import benguyen.resolver.XMLHandler;
import benguyen.utils.TextUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.TrustManager;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Gabriel Nguyen
 */
public class SpaceCrawling {

    private StyleSheetApplier applier = new StyleSheetApplier();
    private XMLHandler handler = new XMLHandler();
    
    private String xsltFile = "src/java/benguyen/resource/xsl/space.xsl";
    private String xsdFile = "src/java/benguyen/resource/xsd/space.xsd";
    
    public void crawlSpace() {
        String content = getContent(DomainConstant.ALONHATRO+1);
        Spaces spaces = new Spaces();
        NewClient client = new NewClient();
        if (content != null) {
            String wellformed = TextUtils.refineHTML(content);
            
            try {
                String xml = applier.applyStyleSheet(xsltFile, wellformed);
                System.out.println(xml);
                if(xml!=null){
                    spaces = XMLHandler.unmarshall(xml, xsdFile, Spaces.class);
                    System.out.println(spaces.getSpace());
                    List<String> listSpaces = spaces.getSpace();
                    //insert db
                    for (int i = 0; i < listSpaces.size(); i++) {
                        Space space = new Space();
                        System.out.println(listSpaces.get(i));
                        space.setSpace(listSpaces.get(i));
                        client.create_XML(space);
                        System.out.println("Insert " + space.getSpace().toString() + " to dtb" );
                    }
                }
            } catch (TransformerException ex) {
                Logger.getLogger(SpaceCrawling.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
