/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.crawl;

import benguyen.clients.SpaceClient;
import benguyen.constants.DomainConstant;
import static benguyen.crawl.CrawHelper.getContent;
import benguyen.generated.Spaces;
import benguyen.models.Space;
import benguyen.resolver.StyleSheetApplier;
import benguyen.resolver.XMLHandler;
import benguyen.utils.TextUtils;
import static java.lang.System.out;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
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
        String content = getContent(DomainConstant.ALONHATRO + 1);
        Spaces spaces = new Spaces();
        SpaceClient client = new SpaceClient();
        if (content != null) {
            String wellformed = TextUtils.refineHTML(content);

            try {
                String xml = applier.applyStyleSheet(xsltFile, wellformed);
                System.out.println(xml);
                if (xml != null) {
                    System.out.println(XMLHandler.unmarshall(xml, xsdFile, Spaces.class).toString());
                    spaces = XMLHandler.unmarshall(xml, xsdFile, Spaces.class);
                    System.out.println(spaces.getSpace());
                    List<String> listSpaces = spaces.getSpace();
//                    //insert db
//                    for (int i = 0; i < listSpaces.size(); i++) {
//                        Space space = new Space();
//                        space.setId(0);
//                        space.setSpace(listSpaces.get(i).toString());
//                        client.createSpace_XML(space, Space.class);
//
//                        System.out.println("Insert " + space.getSpace().toString() + " to dtb");
//                    }
                }
            } catch (TransformerException ex) {
                Logger.getLogger(SpaceCrawling.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
