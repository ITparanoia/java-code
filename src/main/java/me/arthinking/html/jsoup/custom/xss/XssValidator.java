package me.arthinking.html.jsoup.custom.xss;

import org.jsoup.nodes.Attribute;

public interface XssValidator {

    boolean validateAttribute(Attribute attr);
}