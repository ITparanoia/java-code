package me.arthinking.lucene.ikanalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class IKanalyzerTest {

    public static void main(String[] args) {
        /*
        // 检索内容
        String text = "据说WWDC要推出iPhone6要出了？与iPhone5s土豪金相比怎样呢？@2014巴西世界杯 test中文";
        List<String> list = new ArrayList<String>();
        list.add("test中文");

        // 尚未初始化，因为第一次执行分词的时候才会初始化，为了在执行分此前手动添加额外的字典，需要先手动的初始化一下
        Dictionary.initial(DefaultConfig.getInstance());
        Dictionary.getSingleton().addWords(list);

        //创建分词对象  
        Analyzer analyzer = new IKAnalyzer(true);       
        StringReader reader = new StringReader(text);  

        TokenStream ts = analyzer.tokenStream("", reader);  
        CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);  
        //遍历分词数据  
        try {
            while(ts.incrementToken()){  
                System.out.print(term.toString()+"|");  
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            reader.close();
        }
        */
    }
}
