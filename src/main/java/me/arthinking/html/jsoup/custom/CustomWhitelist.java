package me.arthinking.html.jsoup.custom;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

public class CustomWhitelist extends Whitelist{

    /**
     * relax whiteList
     * This whitelist allows a full range of text and structural body HTML: <code>a, b, blockquote, br, caption, cite,
     * code, col, colgroup, dd, div, dl, dt, em, h1, h2, h3, h4, h5, h6, i, img, li, ol, p, pre, q, small, span, strike, strong, sub,
     * sup, table, tbody, td, tfoot, th, thead, tr, u, ul</code>
     * <p/>
     * Links do not have an enforced <code>rel=nofollow</code> attribute, but you can add that if desired.
     */
    public CustomWhitelist() {
        this.addTags(
                "a", "b", "blockquote", "br", "caption", "cite", "code", "col",
                "colgroup", "dd", "div", "dl", "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6",
                "i", "img", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong",
                "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u",
                "ul")

        .addAttributes("a", "href", "title")
        .addAttributes("blockquote", "cite")
        .addAttributes("col", "span", "width")
        .addAttributes("colgroup", "span", "width")
        .addAttributes("img", "align", "alt", "height", "src", "title", "width")
        .addAttributes("ol", "start", "type")
        .addAttributes("q", "cite")
        .addAttributes("table", "summary", "width")
        .addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width")
        .addAttributes(
                "th", "abbr", "axis", "colspan", "rowspan", "scope",
                "width")
        .addAttributes("ul", "type")

        .addProtocols("a", "href", "ftp", "http", "https", "mailto")
        .addProtocols("blockquote", "cite", "http", "https")
        .addProtocols("cite", "cite", "http", "https")
        .addProtocols("img", "src", "http", "https")
        .addProtocols("q", "cite", "http", "https")
        ;
    }
    
    /**
     * for fiter xss css style
     */
    @Override
    protected boolean isSafeAttribute(String tagName, Element el, Attribute attr) {
        if(attr.getKey().equals("style")){
            String value = attr.getValue().replaceAll(" ", "").toLowerCase();
            if(value.contains("javascript") 
                    || value.contains("script")
                    || value.contains("alert")
                    || value.contains("expression")){
                return false;
            }
            
        }
        return super.isSafeAttribute(tagName, el, attr);
    }
}