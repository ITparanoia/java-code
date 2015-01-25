package me.arthinking.html.jsoup.custom.xss;

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