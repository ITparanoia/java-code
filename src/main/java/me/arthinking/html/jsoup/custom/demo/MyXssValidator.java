package me.arthinking.html.jsoup.custom.demo;

import me.arthinking.html.jsoup.custom.xss.XssValidator;

import org.jsoup.nodes.Attribute;

public class MyXssValidator implements XssValidator{

    @Override
    public boolean validateAttribute(Attribute attr) {
        if(attr.getKey().equals("style")){
            String value = attr.getValue().replaceAll(" ", "")
                                          .replaceAll("\\r", "")
                                          .replaceAll("\\n", "")
                                          .toLowerCase();
            // 如果包含了不允许的字符，则判断为不合法的属性
            if(value.contains("*")){
                return false;
            }
        }
        return true;
    }
}