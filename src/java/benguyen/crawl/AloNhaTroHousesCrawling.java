/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.crawl;

import benguyen.constants.DomainConstant;
import static benguyen.crawl.CrawHelper.getContent;
import benguyen.generated.Houses;
import benguyen.generated.Benefits;
import benguyen.generated.HouseList;

import benguyen.resolver.StyleSheetApplier;
import benguyen.resolver.XMLHandler;
import benguyen.utils.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Gabriel Nguyen
 */
public class AloNhaTroHousesCrawling {

    private StyleSheetApplier applier = new StyleSheetApplier();
    private String xsltHouseFile = "src/java/benguyen/resource/xsl/alonhatrohouse.xsl";
    private String xsdHouseFile = "src/java/benguyen/resource/xsd/alonhatrohouse.xsd";
    private String xsdBenefitFile = "src/java/benguyen/resource/xsd/benefit.xsd";
    private String xslBenefitFile = "src/java/benguyen/resource/xsl/alonhatrohousebenefits.xsl";

    public Houses crawlAloNhaTroHouses() {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> listLinks = new ArrayList<>();
        Houses houses = new Houses();
        List<String> urlLinks = new ArrayList<>();
        for (int j = 1; j < 3; j++) {
            urlLinks.add(DomainConstant.ALONHATRO + j);
        }
        for (String urlLink : urlLinks) {
            
            String content = getContent(urlLink);
            if (content != null) {
                String wellformed = TextUtils.refineHTML(content);
                try {
                    String xml = applier.applyStyleSheet(xsltHouseFile, wellformed);
                    if (xml != null) {
                        Houses temp = new Houses();
                        temp = XMLHandler.unmarshall(xml, xsdHouseFile, Houses.class);
                        for (int i = 0; i < temp.getHouse().size(); i++) {
                            if (!temp.getHouse().get(i).getName().equals("")) {
                                listLinks.add(temp.getHouse().get(i).getHouselink());
                                houses.getHouse().add(temp.getHouse().get(i));
                            }
                        }
                    }
                } catch (TransformerException ex) {
                    Logger.getLogger(AloNhaTroHousesCrawling.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        houses = crawlAloNhaTroHouseBenefitsAndMap(listLinks, houses);
        return houses;
    }

    public Houses crawlAloNhaTroHouseBenefitsAndMap(List<String> listLinks, Houses houses) {
        for (int i = 0; i < listLinks.size(); i++) {
            Benefits benefits = new Benefits();
            String content = getContent(listLinks.get(i));
            if (content != null) {
                String wellformed = TextUtils.refineHTML(content);
                try {
                    String benefit = applier.applyStyleSheet(xslBenefitFile, wellformed);
                    if (!benefit.trim().equals("<benefits/>")) {
                        benefits = XMLHandler.unmarshall(benefit, xsdBenefitFile, Benefits.class);
                        houses.getHouse().get(i).setBenefits(benefits.getBenefit());
                    } else {
                        houses.getHouse().get(i).setBenefits(new ArrayList<>());
                    }
                } catch (TransformerException ex) {
                    Logger.getLogger(AloNhaTroHousesCrawling.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return houses;
    }
}
