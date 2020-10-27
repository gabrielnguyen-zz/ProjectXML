/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.test;

import benguyen.crawl.AloNhaTroHousesCrawling;
import benguyen.crawl.NhaTroTotHousesCrawling;
import benguyen.crawl.SpaceCrawling;
import benguyen.utils.TextUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Gabriel Nguyen
 */
public class TestChecker {

    public static void main(String[] args) throws IOException {
//        AloNhaTroHousesCrawling test = new AloNhaTroHousesCrawling();
//        test.crawlAloNhaTroHouses();
//          SpaceCrawling test = new SpaceCrawling();
//          test.crawlSpace();
        NhaTroTotHousesCrawling test = new NhaTroTotHousesCrawling();
        test.crawlNhaTroTotHouses();
    }

    public static void testWellformed(String urlString) throws MalformedURLException, IOException {
       
    }
}
