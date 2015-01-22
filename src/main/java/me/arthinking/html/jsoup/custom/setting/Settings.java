package me.arthinking.html.jsoup.custom.setting;

import java.util.ArrayList;
import java.util.List;

/**
 * Whitelist的Tag配置
 * 类的说明
 * @author  Jason Peng
 * @create date 2015年1月22日
 */
public class Settings {

    private List<Tag> tags = new ArrayList<Tag>();
    
    private List<String> tagNames = new ArrayList<String>();

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(List<String> tagNames) {
        this.tagNames = tagNames;
    }
    
}