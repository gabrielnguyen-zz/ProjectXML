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
    public static boolean checkDomainStartWithString(List<String> listDomain, String domain){
        for (String string : listDomain) {
            if(domain.startsWith(domain)){
                return true;
            }
        }
        return false;
    }
}
