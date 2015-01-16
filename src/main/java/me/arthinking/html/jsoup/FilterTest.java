package me.arthinking.html.jsoup;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class FilterTest {

    private final static Whitelist user_content_filter = Whitelist.relaxed();
    
    static {
        user_content_filter.addTags("style");
        // user_content_filter.addAttributes(":all", "style", "class", "id", "name");
        // user_content_filter.addAttributes("object", "width", "height","classid","codebase");    
        // user_content_filter.addAttributes("param", "name", "value");
        // user_content_filter.addAttributes("embed", "src","quality","width","height","allowFullScreen","allowScriptAccess","flashvars","name","type","pluginspage");
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
        StringBuilder temp = new StringBuilder();
        String url = "http://www.guitargg.com/video_3201.html";  
        HttpURLConnection uc = (HttpURLConnection)new URL(url).  
                               openConnection();  
        uc.setConnectTimeout(10000);  
        uc.setDoOutput(true);  
        uc.setRequestMethod("GET");  
        uc.setUseCaches(false);  
        DataOutputStream out = new DataOutputStream(uc.getOutputStream());  

        out.flush();  
        out.close();  
        InputStream in = new BufferedInputStream(uc.getInputStream());  
        Reader rd = new InputStreamReader(in, "UTF-8");  
        int c = 0;  
        while ((c = rd.read()) != -1) {  
            temp.append((char) c);  
        }  
        in.close();  
        
        System.out.println(filterUserInputContent(temp.toString()));
    }
}
