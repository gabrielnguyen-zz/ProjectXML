/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.crawl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Gabriel Nguyen
 */
public class CrawHelper {

//    public static String getContent(String urlString) {
//        String content = "";
//        try {
//            URL url = new URL(urlString);
//            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                content += inputLine + "\n";
//            }
//            in.close();
//            
//        } catch (MalformedURLException ex) {
//            System.out.println("Get Content Malformed");
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            System.out.println("Get Content IOException");
//            ex.printStackTrace();
//        }
//        return content;
//    }
    private static HttpURLConnection getConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        return (HttpURLConnection) url.openConnection();
    }

    static String getContent(String urlString) {
        try {
            HttpURLConnection conn = getConnection(urlString);
            // add because some websites (Pexels) return 503 if there is no User-Agent header
            conn.addRequestProperty("User-Agent", "Mozilla/4.0");
            if (isOK(conn.getResponseCode())) {
                StringBuilder sb = new StringBuilder();
                String line;
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }
                } catch (IOException ignored) {
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static boolean isOK(int code) {
        return code / 100 == 2;
    }

}
