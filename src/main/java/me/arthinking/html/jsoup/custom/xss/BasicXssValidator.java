package me.arthinking.html.jsoup.custom.xss;

import me.arthinking.html.jsoup.custom.exception.NotImplementedException;

import org.jsoup.nodes.Attribute;

public class BasicXssValidator implements XssValidator{

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
        if(attr.getKey().equals("style") || attr.getKey().equals("href")){
            String value = attr.getValue().replaceAll(" ", "")
                                          .replaceAll("\\r", "")
                                          .replaceAll("\\n", "")
                                          .toLowerCase();
            if(value.contains("javascript") 
                    || value.contains("script")
                    || value.contains("alert")
                    || value.contains("vbscript")
                    || value.contains("livescript")
                    || value.contains("expression")){
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean validateTag(String tagName) {
        // TODO Auto-generated method stub
        // throw new NotImplementedException();
        return true;
    }
}
