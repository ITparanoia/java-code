package me.arthinking.html.jsoup.custom;

import org.jsoup.Jsoup;

public class FilterTest {

    public static void main(String[] args) {
        CustomWhitelist htmlFilter = new CustomWhitelist();
        htmlFilter.addTags("html", "body", "header", "url", "style", "text", "img");
        htmlFilter.addAttributes(":all", "src", "style");
        htmlFilter.addAttributes("object", "width", "height","classid","codebase");    
        htmlFilter.addAttributes("param", "name", "value");
        htmlFilter.addAttributes("img", "src", "onload");
        htmlFilter.addAttributes("embed", "src","quality","width","height","allowFullScreen","allowScriptAccess","flashvars","name","type","pluginspage");

        /*
        String html = ""
                + "<body>"
                + "jaksjl<blockquote style=\"asadfjalsdf\">sdf</blockquote>"
                + "<script></script>"
                + "ab"
                + "<a href=\"http://www.google.com/<abc>\">test</a>"
                + "<STYLE >s<abc></style>"
                + "<text>sfss>sdf</text>"
                + "<b style=\"jav&#x0A;ascript:alert('XSS')\">Embedded carriage</b>"
                + "<div style=\"Javas   cript:abc\" id=\"123\">space</div>"
                + "<div style=\"java\0script:alert(\"XSS\")\">Null breaks up JavaScript directive</div>"
                + "<img src=\"http://www.google.com/,ssd\" />"
                + "<img src=\"&#0000106&#0000097&#0000118&#0000097&#0000115#0000108&#0000101&#0000114&#0000116&#0000040&#0000039&#0000088&#0000083&#0000083&#0000039&#0000041\" />"
                + "</body>";
        // System.out.println(Jsoup.clean(html, htmlFilter));
        html += "<div style=\"&#x6A&#x61&#x76&#x61&#x73&#x63&#x72&#x69&#x70&#x74&#x3A&#x61&#x6C&#x65&#x72&#x74&#x28&#x27&#x58&#x53&#x53&#x27&#x29\"></div>";
         */
        String html = "exp/*<A STYLE='no\\xss:noxss(\"*//*\");xss:ex/*XSS*//*/*/pression(alert(\"XSS\"))'>";
        html="<div style='background:url(\"/i/eg_bg_03.gif\")'>test</div>";
        // html = "background-image:\0075\0072\006C\0028'\006a\0061\0076\0061\0073\0063\0072\0069\0070\0074\003a\0061\006c\0065\0072\0074\0028.1027\0058.1053\0053\0027\0029'\0029";
        System.out.println(Jsoup.clean(html, htmlFilter));
        
    }
}