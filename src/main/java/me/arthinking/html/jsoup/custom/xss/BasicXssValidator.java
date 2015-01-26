package me.arthinking.html.jsoup.custom.xss;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Attribute;

public class BasicXssValidator implements XssValidator{

    private static final Pattern invalidXmlCharacters =
            Pattern.compile("[\\u0000-\\u001F\\uD800-\\uDFFF\\uFFFE-\\uFFFF&&[^\\u0009\\u000A\\u000D]]");
    
    /**
     * implements the basic validation, general rule for all attribute
     * @param attr
     * @return
     * boolean
     * @author Jason Peng
     * @update date 2015年1月22日
     */
    @Override
    public boolean validateAttribute(Attribute attr) {
        Matcher matcher = invalidXmlCharacters.matcher(attr.getValue());
        if(matcher.find()){
            return false;
        }
        if(attr.getKey().equals("style")){
            String value = attr.getValue().replaceAll(" ", "")
                                          .replaceAll("\\r", "")
                                          .replaceAll("\\n", "")
                                          .toLowerCase();
    		
            if(value.contains("script")
                    || value.contains("alert")
                    || value.contains("expression")){
                return false;
            }
        }
        return true;
    }
    
}
