package me.arthinking.html.antisamy;

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
        		+ "<div class='abc' style='xss:expression(alert('XSS'));background: url(\"a\");transform:rotate(30deg);text-align:left; width:100px; height:200px;'>"
                + "</div>"
        		+ "<div>test</div>"
        		+ "<img class='test' src='http://www.google.com/'/>"
                + "<a href=\"http://www.google.com/<abc>\">test</a>"
                + "<STYLE >s<abc></style>"
                + "<text>sfss>sdf</text>"
                + "<b style=\"jav&#x0A;ascript:alert('XSS')\">Embedded carriage</b>"
                + "<div style=\"Javas   cript:abc\" id=\"123\">space</div>"
                + "<div style=\"java\0script:alert(\"XSS\")\">Null breaks up JavaScript directive</div>"
                + "<img src=\"http://www.google.com/*\" />"
                + "<img src=\"javascript:alert('XSS')\" />"
                + "<img src=\"http://abc.cc/javascript:alert(a)\" />"
                + "</body>";
    	CleanResults cr = as.scan(html, policy);
    	System.out.println(cr.getCleanHTML());
    	long end = System.currentTimeMillis();
    	System.out.println(end - start);
    }
}