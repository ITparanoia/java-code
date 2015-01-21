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
     * Jsoup只是根据判断是否属性外的文本进行编码，无法对可能产生的XSS问题进行编码，
     * 所以可以在这里直接过滤掉可能产生XSS问题的内容
     * 
     * html传入Jsoup构建成Document的时候，会把Decimal HTML character解码，所以处理明确的编码就可以了
     * 
     * 过滤规则：
     *   - Jsoup先转换为解码后的HTML
     *   - 过滤script  style LINK标签
     *   - 处理href src style 属性（javascript alert vbscript livescript），先过滤掉无用的字符（空格，tab，换行，注释）
     *       style样式表禁止expression属性，禁止链接，防止远程样式表或脚本注入
     *   - 各种标签的事件属性
     * 
     * 
     * 
     * jsoup只对设置了协议的属性进行过滤，只能判断跳转的协议是否合法，协议后的字符串注入不理会，这里需要处理下
     *     在 Cleaner.copySafeNodes()里面获取安全的HTML
     * 
     */
    @Override
    protected boolean isSafeAttribute(String tagName, Element el, Attribute attr) {
        if(attr.getKey().equals("style") || attr.getKey().equals("href")){
            String value = attr.getValue().replaceAll(" ", "")
                                          .replaceAll("\\r", "")
                                          .replaceAll("\\n", "")
                                          .replaceAll("\\*", "")
                                          .toLowerCase();
            if(value.contains("javascript") 
                    || value.contains("script")
                    || value.contains("alert")
                    || value.contains("expression")){
                return false;
            }
            
        }
        return super.isSafeAttribute(tagName, el, attr);
    }

    @Override
    protected boolean isSafeTag(String tag) {
        if(!super.isSafeTag(tag)){
            // 校验失败，返回校验结果
        }
        return super.isSafeTag(tag);
    }
    
}