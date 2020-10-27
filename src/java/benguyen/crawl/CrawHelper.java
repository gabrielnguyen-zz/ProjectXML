/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.crawl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Gabriel Nguyen
 */
public class CrawHelper {

    public static String getContent(String urlString) {
        String content = "";
        try {
            URL url = new URL(urlString);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content += inputLine + "\n";
            }
            in.close();
            
        } catch (MalformedURLException ex) {
            System.out.println("Get Content Malformed");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Get Content IOException");
            ex.printStackTrace();
        }
        return content;
    }

//    public static DOMResult crawl(String configPath, String xslPath) throws FileNotFoundException{
//        //init files
//        StreamSource xslCate = new StreamSource(xslPath);
//        InputStream is =  new FileInputStream(configPath);
//        //init transformer api
//        TransformerFactory factory = TransformerFactory.newInstance();
//        DOMResult result = new DOMResult();
//        
//    }
}
