package me.arthinking.html.jsoup.custom.setting;

import java.util.ArrayList;
import java.util.List;

/**
 * 白名单过滤使用到的标签
 * 类的说明
 * @author  Jason Peng
 * @create date 2015年1月22日
 */
public class Tag {

    private String tagName;
    
    private List<String> attributes = new ArrayList<String>();

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

}