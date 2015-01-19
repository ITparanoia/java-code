package me.arthinking.html.jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class FilterTest {

    private final static Whitelist user_content_filter = Whitelist.relaxed();
    
    static {
    	// http://jsoup.org/cookbook/cleaning-html/whitelist-sanitizer
        user_content_filter.addTags("html", "body", "header", "url", "style");
        user_content_filter.addAttributes(":all", "style", "class", "id", "name");
        user_content_filter.addAttributes("object", "width", "height","classid","codebase");    
        user_content_filter.addAttributes("param", "name", "value");
        user_content_filter.addAttributes("embed", "src","quality","width","height","allowFullScreen","allowScriptAccess","flashvars","name","type","pluginspage");
    }
    
    /**
     * Hecho por Grekz, http://grekz.wordpress.com
     */
    public static Document inlineStyles(String html) throws IOException {
        // Document doc = Jsoup.connect("http://mypage.com/inlineme.php").get();
        Document doc = Jsoup.parse(html);
        String style = "style";
        Elements els = doc.select(style);// to get all the style elements
        System.out.println(els.size());
        for (Element e : els) {
            String styleRules = e.getAllElements().get(0).data().replaceAll("\n", "").trim(), delims =
                    "{}";
            StringTokenizer st = new StringTokenizer(styleRules, delims);
            while (st.countTokens() > 1) {
                String selector = st.nextToken(), properties = st.nextToken();
                // Process selectors such as "a:hover"
                if (selector.indexOf(":") > 0) {
                    selector = selector.substring(0, selector.indexOf(":"));
                }
                if (StringUtils.isEmpty(selector)) {
                    continue;
                }
                Elements selectedElements = doc.select(selector);
                for (Element selElem : selectedElements) {
                    String oldProperties = selElem.attr(style);
                    selElem.attr(
                        style,
                        oldProperties.length() > 0 ? concatenateProperties(oldProperties,
                            properties) : properties);
                }
            }
            e.remove();
        }
        return doc;
    }

    private static String concatenateProperties(String oldProp, String newProp) {
        oldProp = oldProp.trim();
        if (!newProp.endsWith(";")) {
            newProp += ";";
        }
        return newProp + oldProp; // The existing (old) properties should take precedence.
    }

    public static String encodeForCSS(String input) {
        if (input == null) {
            return input;
        }

        StringBuilder sb = new StringBuilder(input.length());

        for (int i = 0, c = input.length(); i < c; i++) {
            char ch = input.charAt(i);

            // check for alphanumeric characters
            if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' ||
                    ch >= '0' && ch <= '9') {
                sb.append(ch);
            } else {
                // return the hex and end in whitespace to terminate
                sb.append('\\').append(Integer.toHexString(ch)).append(' ');
            }
        }
        return sb.toString();
    }
    
    /**
     * 对用户输入内容进行过滤
     * @param html
     * @return
     */
    public static String filterUserInputContent(String html) {
        if(StringUtils.isBlank(html)) return "";
        return Jsoup.clean(html, user_content_filter);
        //return filterScriptAndStyle(html);
    }
    
    public static void main(String[] args) throws MalformedURLException, IOException {
        String html = "<html>"
                        + "<header><url>a</url></header>"
                        + "<body>"
                        + "<b>test</b>"
                        + "jaksjl<blockquote style=\"asadfjalsdf\">sdf</blockquote>"
                        + "<script></script>"
                        + "ab"
                        + "<STYLE >s</style>"
                        + "<div id=\"123\">asdf</div>"
                        + "</body>"
                        + "</html>";
        System.out.println(new HTMLFilter().filter(html));
        
        html = filterUserInputContent(html);
        Document document = Jsoup.parse(html);
    }
}
