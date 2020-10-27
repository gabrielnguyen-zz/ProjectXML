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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Gabriel Nguyen
 */
public class AloNhaTroHousesCrawling {

    private StyleSheetApplier applier = new StyleSheetApplier();

    public void crawlAloNhaTroHousesLink() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 1; j < 10; j++) {
            String content = getContent(DomainConstant.ALONHATRO + j);
            if (content != null) {
                
                stringBuilder.append(content);
            }
        }
        String wellformed = TextUtils.refineHTML(stringBuilder.toString());
        try {
            String xml = applier.applyStyleSheet("src/java/benguyen/resource/xsl/alonhatrolink.xsl", wellformed);
            System.out.println("Aloha" + xml);

        } catch (TransformerException ex) {
            Logger.getLogger(AloNhaTroHousesCrawling.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
