package me.arthinking.html.jsoup.custom.demo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import me.arthinking.html.jsoup.custom.CustomWhitelist;
import me.arthinking.html.jsoup.custom.setting.WhitelistSetting;
import me.arthinking.html.jsoup.custom.xss.UrlXssValidator;

import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Cleaner;

public class FilterTest {

    public static void main(String[] args) {
        // 创建配置类
        WhitelistSetting whitelistSetting = new MyWhitelistSetting();
        // 创建自定义白名单
        CustomWhitelist htmlFilter = new CustomWhitelist(whitelistSetting);
        // 刷新加载配置
        htmlFilter.refreshSetting();
        // 添加自定义XSS过滤器
        htmlFilter.addXssValidator(new UrlXssValidator());
        htmlFilter.addXssValidator(new MyXssValidator());
        String html = ""
                + "<body>"
                + "jaksjl<blockquote>sdf</blockquote>"
                + "ab"
                + "<STYLE >s<abc></style>"
                + "<text>sfss>sdf</text>"
                + "<div style=\"javassccripty:aalbert(123)\">Null breaks up JavaScript directive</div>"
                + "<img src=\"javascript:alert('XSS')\" />"
                + "</body>";
        // System.out.println(Jsoup.clean(html, htmlFilter));
        // html += "<div style=\"&#x6A&#x61&#x76&#x61&#x73&#x63&#x72&#x69&#x70&#x74&#x3A&#x61&#x6C&#x65&#x72&#x74&#x28&#x27&#x58&#x53&#x53&#x27&#x29\"></div>";
        // String html = "exp/*<A STYLE='no\\xss:noxss(\"*//*\");xss:ex/*XSS*//*/*/pression(alert(\"XSS\"))'>**sdf</a>";
        // html = "<a href=\"http://www.google.com/*\">test</a>";
        // html = "<a href=''></a>";
        // html="<div style='background:url(\"/i/eg_bg_03.gif\")'>test</div>";
        // html = "background-image:\0075\0072\006C\0028'\006a\0061\0076\0061\0073\0063\0072\0069\0070\0074\003a\0061\006c\0065\0072\0074\0028.1027\0058.1053\0053\0027\0029'\0029";
        StringBuffer content = new StringBuffer();
        try {
            // 新建URL对象
            URL u = new URL("http://best.pconline.com.cn/shaiwu/117468.html");
            InputStream in = new BufferedInputStream(u.openStream());
            InputStreamReader theHTML = new InputStreamReader(in, "UTF8");
            int c;
            while ((c = theHTML.read()) != -1) {
                content.append((char) c);
            }
        } catch (MalformedURLException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        long start = System.currentTimeMillis();
        Document dirty = Parser.parseBodyFragment(content.toString(), "");
        Cleaner cleaner = new Cleaner(htmlFilter);
        Document clean = cleaner.clean(dirty);
        System.out.println(clean.html());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(htmlFilter.isValid());
        // System.out.println(htmlFilter.isValid());
        // System.out.println(Jsoup.clean(html, htmlFilter));
        // System.out.println(clean.html());
    }
}