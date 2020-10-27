/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.utils;

import java.util.List;

/**
 *
 * @author Gabriel Nguyen
 */
public class StringUtil {

    public String getSpace(String src) {
        String temp = src;
        if (src.contains("Diện")) {
            temp = src.split(":")[1];
        }
        return temp.substring(0, 2).trim();
    }

    public String getDistrict(String src) {
        String temp = src;
        if (temp != null) {
            if (temp.contains("Hồ Chí Minh")) {
                temp = src.split(",")[0];
            }else
                temp = src.split(",")[1];
        }
        return temp.trim();
    }

    public String getPrice(String src) {
        String temp = src;
        if (temp != null && temp.contains("Triệu") || temp.contains("triệu")) {
            temp = src.split(" ")[0];
        }
        return temp.trim();
    }
}
