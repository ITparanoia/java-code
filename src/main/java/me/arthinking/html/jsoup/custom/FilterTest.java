package me.arthinking.html.jsoup.custom;

import org.jsoup.Jsoup;

public class FilterTest {

    public static void main(String[] args) {
        CustomWhitelist htmlFilter = new CustomWhitelist();
        htmlFilter.addTags("html", "body", "header", "url", "style");
        htmlFilter.addAttributes(":all", "style", "class", "id", "name");
        htmlFilter.addAttributes("object", "width", "height","classid","codebase");    
        htmlFilter.addAttributes("param", "name", "value");
        htmlFilter.addAttributes("embed", "src","quality","width","height","allowFullScreen","allowScriptAccess","flashvars","name","type","pluginspage");

        String html = ""
                + "<header><url>a</url></header>"
                + "<body>"
                + "jaksjl<blockquote style=\"asadfjalsdf\">sdf</blockquote>"
                + "<script></script>"
                + "ab"
                + "<STYLE >s</style>"
                + "<b style=\"jav&#x0A;ascript:alert('XSS')\">Embedded carriage</b>"
                + "<div style=\"Javaas   crbipt:abc\" id=\"123\">space</div>"
                + "<div style=\"java\0script:alert(\"XSS\")\">Null breaks up JavaScript directive</div>"
                + "</body>";
        // System.out.println(Jsoup.clean(html, htmlFilter));
        html += "<div style=\"&#x6A&#x61&#x76&#x61&#x73&#x63&#x72&#x69&#x70&#x74&#x3A&#x61&#x6C&#x65&#x72&#x74&#x28&#x27&#x58&#x53&#x53&#x27&#x29\"></div>";
        System.out.println(Jsoup.clean(html, htmlFilter));
        
    }
}
