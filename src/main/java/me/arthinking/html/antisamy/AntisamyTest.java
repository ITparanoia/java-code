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
                + "<a href=\"http://www.zhongsou.com/third.cgi?w=%3Cscript%3E+alert%28%2F70826450%2F%29%3B%3C%2Fscript%3E&y=5&k=&netid=&v=%D7%DB%BA%CF\">test</a>";
        /*
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
        */
    	CleanResults cr = as.scan(html, policy);
    	System.out.println(cr.getCleanHTML());
    	long end = System.currentTimeMillis();
    	System.out.println(end - start);
    }
}