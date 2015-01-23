package me.arthinking.html.antisamy;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

public class AntisamyTest {

	/**
	 * https://code.google.com/p/owaspantisamy/downloads/list
	 * @param args
	 * @throws ScanException
	 * @throws PolicyException
	 */
    public static void main(String[] args) throws ScanException, PolicyException {
    	Policy policy = Policy.getInstance("src/main/java/me/arthinking/html/antisamy/antisamy-ebay-1.4.4.xml");
    	AntiSamy as = new AntiSamy();
    	long start = System.currentTimeMillis();
        String html = ""
                + "<body>"
        		+ "<div class='abc' style='font-family:\"宋体\",Times New Roman,Georgia,Serif;box-shadow:2px 2px 5px #333333;text-align:left; width:100px; height:200px;'>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                +	"abc"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
                + "</div>"
        		+ "<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                +	"abc"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
                + "</div>"
        		+ "<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                +	"abc"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
                + "</div>"
        		+ "<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                + 	"<div>"
                +	"abc"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
        		+ 	"</div>"
                + "</div>"
        		+ "<div>test</div>"
        		+ "<img class='test' src='http://www.google.com/'/>"
        		+ "<div class='abc' style='align:center;'>"
                + 	"<div class='test'>"
        		+   	"<div class='abc' style='align:left' attr=attr>"
        		+   	"<div class='abc' style='align:left' attr=attr>"
        		+   	"<div class='abc' style='align:left' attr=attr>"
        		+   	"<div class='abc' style='align:left' attr=attr>"
        		+   	"<div class='abc' style='align:left' attr=attr>"
        		+   	"<div class='abc' style='align:left' attr=attr>"
                +		"</div>"
                +		"</div>"
                +		"</div>"
                +		"</div>"
                +		"</div>"
                +		"</div>"
        		+ 	"</div>"
                + "</div>"
                + "<a href=\"http://www.google.com/<abc>\">test</a>"
                + "<STYLE >s<abc></style>"
                + "<text>sfss>sdf</text>"
                + "<b style=\"jav&#x0A;ascript:alert('XSS')\">Embedded carriage</b>"
                + "<div style=\"Javas   cript:abc\" id=\"123\">space</div>"
                + "<div style=\"java\0script:alert(\"XSS\")\">Null breaks up JavaScript directive</div>"
                + "<img src=\"http://www.google.com/*,ssd\" />"
                + "<img src=\"&#0000106&#0000097&#0000118&#0000097&#0000115#0000108&#0000101&#0000114&#0000116&#0000040&#0000039&#0000088&#0000083&#0000083&#0000039&#0000041\" />"
                + "</body>";
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
    	CleanResults cr = as.scan(content.toString(), policy);
    	System.out.println(cr.getCleanHTML());
    	long end = System.currentTimeMillis();
    	System.out.println(end - start);
    }
}