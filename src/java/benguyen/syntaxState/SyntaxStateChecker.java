/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.syntaxState;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import benguyen.syntaxState.SyntaxState.*;

/**
 *
 * @author Gabriel Nguyen
 */
public class SyntaxStateChecker {

    // đọc hiểu cú pháp nội dung html và convert thành xml well formed
    public String check(String src) {
        src = src + " ";
        char[] reader = src.toCharArray();
        StringBuilder writer = new StringBuilder();
        StringBuilder openTag = new StringBuilder();
        boolean isEmptyTag = false, isOpenTag = false, isCloseTag = false;
        StringBuilder closeTag = new StringBuilder();
        StringBuilder attrName = new StringBuilder();
        StringBuilder attrValue = new StringBuilder();
        Map<String, String> attributes = new HashMap<>();

        StringBuilder content = new StringBuilder();
        Stack<String> stack = new Stack<>();
        String state = SyntaxState.CONTENT;
        //1. Duyet character neu gap "<" , chuyen sang state openBracket
        for (int i = 0; i < reader.length; i++) {
            char c = reader[i];
            switch (state) {
                case SyntaxState.CONTENT:
                    if (c == SyntaxState.LT) {
                        state = SyntaxState.OPEN_BRACKET;
                        writer.append(content.toString().trim()
                                .replace("&", "&amp;"));
                    } else {
                        content.append(c);
                    }
                    break;
                //2. 
                //TH1 : gặp ngay kí tự mở đầu tên tag thì set cờ isOpenTag và reset biến c
                //TH2 : gặp kí tự "/" đóng thì set cờ isCloseTag
                case SyntaxState.OPEN_BRACKET:
                    if (SyntaxState.isStartTagChars(c)) {
                        state = SyntaxState.OPEN_TAG_NAME;
                        isOpenTag = true;
                        isCloseTag = false;
                        isEmptyTag = false;
                        openTag.setLength(0);
                        openTag.append(c);
                    } else if (c == SyntaxState.SLASH) {
                        state = SyntaxState.CLOSE_TAG_SLASH;
                        isOpenTag = false;
                        isCloseTag = true;
                        isEmptyTag = false;
                    }
                    break;
                // Ghi nhận tên thẻ mở nếu kí tự hiện tại hợp lệ với tên thẻ
                //Nếu gặp space, reset biến, còn lại chuyển đến state tương ứng
                case SyntaxState.OPEN_TAG_NAME:
                    if (SyntaxState.isTagChars(c)) {
                        openTag.append(c);
                    } else if (SyntaxState.isSpace(c)) {
                        state = SyntaxState.TAG_INNER;
                        attributes.clear();
                    } else if (c == SyntaxState.GT) {
                        state = SyntaxState.CLOSE_BRACKET;
                    } else if (c == SyntaxState.SLASH) {
                        state = SyntaxState.EMPTY_SLASH;
                    }
                    break;
                // Ghi nhận tên attr vào biến attrName
                case SyntaxState.TAG_INNER:
                    if (SyntaxState.isSpace(c)) {

                    } else if (SyntaxState.isStartAttrChars(c)) {
                        state = SyntaxState.ATTR_NAME;
                        attrName.setLength(0);
                        attrName.append(c);
                    } else if (c == SyntaxState.GT) {
                        state = SyntaxState.CLOSE_BRACKET;
                    } else if (c == SyntaxState.SLASH) {
                        state = SyntaxState.EMPTY_SLASH;
                    }
                    break;
                // Tiến hành ghi nhận tên attr và stop ở space hay dấu =
                // Else cuối cùng dành cho khi attr ko có value
                // VD: <br/>
                case SyntaxState.ATTR_NAME:
                    if (SyntaxState.isAttrChars(c)) {
                        attrName.append(c);
                    } else if (c == SyntaxState.EQ) {
                        state = SyntaxState.EQUAL;
                    } else if (SyntaxState.isSpace(c)) {
                        state = SyntaxState.EQUAL_WAIT;
                    } else {
                        if (c == SyntaxState.SLASH) {
                            attributes.put(attrName.toString(), "true");
                            state = SyntaxState.EMPTY_SLASH;
                        } else if (c == SyntaxState.GT) {
                            attributes.put(attrName.toString(), "true");
                            state = SyntaxState.CLOSE_BRACKET;
                        }
                    }
                    break;
                // Skip space đến khi gặp dấu =
                //Else là khi đọc được tên ATTR khác ko phải dấu =
                //Thêm value "true" vào attr hiện tại và lưu vào attributes, ghi attr tiếp theo
                case SyntaxState.EQUAL_WAIT:
                    if (SyntaxState.isSpace(c)) {

                    } else if (c == SyntaxState.EQ) {
                        state = SyntaxState.EQUAL;
                    } else {
                        if (SyntaxState.isStartAttrChars(c)) {
                            attributes.put(attrName.toString(), "true");
                            state = SyntaxState.ATTR_NAME;
                            attrName.setLength(0);
                            attrName.append(c);
                        }
                    }
                    break;
                // Skip space đến gặp dấu nháy đầu tiên, lưu lại gia trị để nhạn biết và value
                // Tuy nhiên, nếu ko nằm trong nháy, ghi nhận attr ko space hay dóng thẻ
                case SyntaxState.EQUAL:
                    if (SyntaxState.isSpace(c)) {

                    } else if (c == SyntaxState.D_QUOT || c == SyntaxState.S_QUOT) {
                        quote = c;
                        state = SyntaxState.ATTR_VALUE_Q;
                        attrValue.setLength(0);
                    } else if (!SyntaxState.isSpace(c) && c != SyntaxState.GT) {
                        state = SyntaxState.ATTR_VALUE_NQ;
                        attrValue.setLength(0);
                        attrValue.append(c);
                    }
                    break;
                // Ghi nhận value của attr và dừng khi gặp dúng loại dấu nhấy mở mà ta đã lưu vào quote ở state Equal
                // Tiến hành lưu attr name và value vào attributes, về state Tag_inner để thu thập tiếp nếu còn.
                case SyntaxState.ATTR_VALUE_Q:
                    if (c != quote) {
                        attrValue.append(c);
                    } else if (c == quote) {
                        state = SyntaxState.TAG_INNER;
                        attributes.put(attrName.toString(), attrValue.toString());
                    }
                    break;
                // Ghi nhận value của attr và chỉ dừng lại khi gặp kí tự kết thúc thẻ ">" or space.
                // Lưu attr name và value vào attrs,chuyển dến state kế tiêp 
                case SyntaxState.ATTR_VALUE_NQ:
                    if (!SyntaxState.isSpace(c) && c != SyntaxState.GT) {
                        attrValue.append(c);
                    } else if (SyntaxState.isSpace(c)) {
                        state = SyntaxState.TAG_INNER;
                        attributes.put(attrName.toString(), attrValue.toString());
                    } else if (c == SyntaxState.GT) {
                        state = SyntaxState.CLOSE_BRACKET;
                        attributes.put(attrName.toString(), attrValue.toString());
                    }
                    break;
                // Xử lí thẻ rỗng
                case SyntaxState.EMPTY_SLASH:
                    if (c == SyntaxState.GT) {
                        state = SyntaxState.CLOSE_BRACKET;
                        isEmptyTag = true;
                    }
                    break;
                case SyntaxState.CLOSE_TAG_SLASH:
                    if (SyntaxState.isStartTagChars(c)) {
                        state = SyntaxState.CLOSE_TAG_NAME;
                        closeTag.setLength(0);
                        closeTag.append(c);
                    }
                    break;
                case SyntaxState.CLOSE_TAG_NAME:
                    if (SyntaxState.isTagChars(c)) {
                        closeTag.append(c);
                    } else if (SyntaxState.isSpace(c)) {
                        state = SyntaxState.WAIT_END_TAG_CLOSE;
                    } else if (c == SyntaxState.GT) {
                        state = SyntaxState.CLOSE_BRACKET;
                    }
                    break;
                // skip space đến khi gặp ">"
                case SyntaxState.WAIT_END_TAG_CLOSE:
                    if (SyntaxState.isSpace(c)) {

                    } else if (c == SyntaxState.GT) {
                        state = SyntaxState.CLOSE_BRACKET;
                    }
                    break;
                case SyntaxState.CLOSE_BRACKET:
                    if (isOpenTag) {
                        String openTagName = openTag.toString().toLowerCase();
                        if (SyntaxState.INLINE_TAG.contains(openTagName)) {
                            isEmptyTag = true;
                        }
                        writer.append(SyntaxState.LT)
                                .append(openTagName)
                                .append(convert(attributes))
                                .append((isEmptyTag ? "/" : ""))
                                .append(SyntaxState.GT);
                        attributes.clear();
                        //STACK:push open-tag
                        if (!isEmptyTag) {
                            stack.push(openTagName);
                        }
                    } else if (isCloseTag) {
                        //STACK: pop out open-tag having same name
                        String closeTagName = closeTag.toString().toLowerCase();
                        //Bỏ qua các thẻ nếu ko có open tag
                        //Nếu thiếu thẻ đóng thì phải xử lí vì đẫ có trong stack
                        if (!stack.isEmpty() && stack.contains(closeTagName)) {
                            while (!stack.isEmpty() && !stack.peek().equals(closeTagName)) {
                                writer.append(SyntaxState.LT)
                                        .append(SyntaxState.SLASH)
                                        .append(stack.pop())
                                        .append(SyntaxState.GT);
                            }
                        }
                        if (!stack.isEmpty() && stack.peek().equals(closeTagName)) {
                            writer.append(SyntaxState.LT)
                                    .append(SyntaxState.SLASH)
                                    .append(stack.pop())
                                    .append(SyntaxState.GT);
                        }
                    }
                    if (c == SyntaxState.LT) {
                        state = SyntaxState.OPEN_BRACKET;
                    } else {
                        state = SyntaxState.CONTENT;
                        content.setLength(0);
                        content.append(c);
                    }
            }
        }
        if (SyntaxState.CONTENT.equals(state)) {
            writer.append(content.toString().trim().replace("&", "&amp;"));
        }
        //pop out all left tags
        while (!stack.isEmpty()) {
            writer.append(SyntaxState.LT)
                    .append(SyntaxState.SLASH)
                    .append(stack.pop())
                    .append(SyntaxState.GT);
        }
        return writer.toString();

    }

    private Character quote;

    // chuyển attr bao gồm key-value thành xml
    private String convert(Map<String, String> attributes) {
        if (attributes.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String value = entry.getValue()
                    .replace("&", "&amp;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&apos;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;");
            builder.append(entry.getKey()).append("=")
                    .append("\"").append(value).append("\"")
                    .append(" ");
        }
        String result = builder.toString().trim();
        if (!result.equals("")) {
            result = " " + result;
        }
        return result;
    }

}
