/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.utils;

import benguyen.syntaxState.SyntaxStateChecker;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gabriel Nguyen
 */
public class TextUtils {

    //Loại bỏ những thẻ không cần thiết như cmt and script
    //cắt toàn bộ thẻ body dể thu hẹp vùng xử lí cho máy đoc
    public static String refineHTML(String src) {
        src = getBody(src);
        src = removeMiscellaneousTags(src);

        SyntaxStateChecker checker = new SyntaxStateChecker();
        src = checker.check(src);

        //crop 1 more
        src = getBody(src);
        return src;
    }
    
    public static String refineHTMLwithoutCropBody(String src){
        src = removeMiscellaneousTags(src);
        SyntaxStateChecker checker = new SyntaxStateChecker();
        src = checker.check(src);
        return src;
    }

    // cắt và trả phần body từ HTML sd Regex
    private static String getBody(String src) {
        String result = src;

        String expression = "<body.*?</body>";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(result);
        if (matcher.find()) {
            result = matcher.group(0);
        }
        return result;
    }

    // Loại bỏ script, comment và &nbsp
    public static String removeMiscellaneousTags(String src) {
        String result = src;
        //Remove Script tags
        String expression = "<script.*?</script>";
        result = result.replaceAll(expression, "");
        //Remove comment
        expression = "<!--.*?-->";
        result = result.replaceAll(expression, "");
        //Remove all whitespace
        expression = "&nbsp;";
        result = result.replaceAll(expression, "");
        return result;
    }
}
