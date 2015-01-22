package me.arthinking.html.jsoup.custom.demo;

import me.arthinking.html.jsoup.custom.xss.XssValidator;

import org.jsoup.nodes.Attribute;

public class MyXssValidator implements XssValidator{

    @Override
    public boolean validateTag(String tagName) {
        // throw new NotImplementedException();
        return true;
    }

    @Override
    public boolean validateAttribute(Attribute attr) {
        if(attr.getKey().equals("style") || attr.getKey().equals("href")){
            String value = attr.getValue().replaceAll(" ", "")
                                          .replaceAll("\\r", "")
                                          .replaceAll("\\n", "")
                                          .toLowerCase();
            // 如果包含了不允许的字符，报错
            if(value.contains("*")){
                return false;
            }
        }
        return true;
    }
}