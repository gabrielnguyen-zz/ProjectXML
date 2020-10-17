/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.crawl;

import benguyen.constants.DomainConstant;
import static benguyen.crawl.CrawHelper.getContent;
import benguyen.resolver.StyleSheetApplier;
import benguyen.utils.TextUtils;
import java.io.ByteArrayOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Gabriel Nguyen
 */
public class SpaceCrawling {

    private StyleSheetApplier applier = new StyleSheetApplier();

    public void crawlSpace() {
        String content = getContent(DomainConstant.ALONHATRO + 1);
        if (content != null) {
            String wellformed = TextUtils.refineHTML(content);
            try {
                ByteArrayOutputStream xml = applier.applyStyleSheet("src/java/benguyen/resource/xsl/space.xsl", wellformed);
                System.out.println(xml);
            } catch (TransformerException ex) {
                Logger.getLogger(SpaceCrawling.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
