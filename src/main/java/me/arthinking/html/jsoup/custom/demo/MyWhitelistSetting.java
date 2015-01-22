package me.arthinking.html.jsoup.custom.demo;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.safety.Whitelist;

import me.arthinking.html.jsoup.custom.setting.AbstractWhitelistSetting;
import me.arthinking.html.jsoup.custom.setting.Settings;
import me.arthinking.html.jsoup.custom.setting.Tag;

public class MyWhitelistSetting extends AbstractWhitelistSetting{

    /**
     * 具体实现的应用根据自己的需求设置默认的白名单
     */
    @Override
    public void initCommonSetting(Whitelist whitelist) {
        whitelist.addTags(
                "a", "b", "blockquote", "br", "caption", "cite", "code", "col",
                "colgroup", "dd", "div", "dl", "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6",
                "i", "img", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong",
                "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u",
                "ul")

        .addAttributes("a", "href", "title")
        .addAttributes("blockquote", "cite")
        .addAttributes("col", "span", "width")
        .addAttributes("colgroup", "span", "width")
        .addAttributes("img", "align", "alt", "height", "src", "title", "width")
        .addAttributes("ol", "start", "type")
        .addAttributes("q", "cite")
        .addAttributes("table", "summary", "width")
        .addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width")
        .addAttributes(
                "th", "abbr", "axis", "colspan", "rowspan", "scope",
                "width")
        .addAttributes("ul", "type")
        // .addProtocols("a", "href", "ftp", "http", "https", "mailto")
        .addProtocols("blockquote", "cite", "http", "https")
        .addProtocols("cite", "cite", "http", "https")
        .addProtocols("img", "src", "http", "https")
        .addProtocols("q", "cite", "http", "https")
        ;
    }
    
    /**
     * 可以从网络，文件或者数据库读取，这里暂时写上mock data
     */
    @Override
    public Settings reloadSetting() {
        Settings settings = new Settings();
        List<Tag> tags = new ArrayList<Tag>();
        List<String> tagNames = new ArrayList<String>();
        tagNames.add("html");
        tagNames.add("body");
        tagNames.add("header");
        tagNames.add("url");
        tagNames.add("style");
        tagNames.add("text");
        tagNames.add("img");
        
        Tag tag = new Tag();
        List<String> attribute = new ArrayList<String>();
        tag.setTagName("object");
        attribute = new ArrayList<String>();
        attribute.add("width");
        attribute.add("height");
        attribute.add("classid");
        attribute.add("codebase");
        tag.setAttributes(attribute);
        tags.add(tag);
        tagNames.add("object");
        
        tag = new Tag();
        tag.setTagName("param");
        attribute = new ArrayList<String>();
        attribute.add("name");
        attribute.add("value");
        tag.setAttributes(attribute);
        tags.add(tag);
        tagNames.add("parm");
        
        tag = new Tag();
        tag.setTagName("img");
        attribute = new ArrayList<String>();
        attribute.add("src");
        attribute.add("onload");
        tag.setAttributes(attribute);
        tags.add(tag);
        tagNames.add("img");
        
        tag = new Tag();
        tag.setTagName("embed");
        attribute = new ArrayList<String>();
        attribute.add("src");
        attribute.add("quality");
        attribute.add("width");
        attribute.add("height");
        attribute.add("allowFullScreen");
        attribute.add("allowScriptAccess");
        attribute.add("flashvars");
        attribute.add("name");
        attribute.add("type");
        attribute.add("pluginspage");
        tag.setAttributes(attribute);
        tags.add(tag);
        tagNames.add("embed");
        
        settings.setTags(tags);
        settings.setTagNames(tagNames);
        return settings;
    }
    
}
