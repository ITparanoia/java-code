#Jsoup动态刷新白名单与Xss过滤
##项目说明
本项目基于Jsoup，通过该项目提供的CustomWhitelist类实现动态刷新白名单和Xss过滤的支持，白名单的获取方式由用户决定（从网络还是数据库等方式获取）

##使用方式
###1、编写自己的白名单设置类
该类继承AbstractWhitelistSetting，需要实现如下接口：
> `initCommonSetting(Whitelist whitelist)`: 具体实现的应用根据自己的需求设置默认的白名单    
> `reloadSetting()`: 实现重新加载配置        

```java
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
     * 可以从网络，文件或者数据读取，这里暂时写上mockdata
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
```

###2、如果有需要，实现自己的XssValidator
该类继承XssValidator，根据自己的业务需求判断属性名称和值进行过滤    

```java
public class MyXssValidator implements XssValidator{

    @Override
    public boolean validateTag(String tagName) {
        // throw new NotImplementedException();
        return true;
    }

    @Override
    public boolean validateAttribute(Attribute attr) {
        if(attr.getKey().equals("style") || attr.getKey().equals("href")){
            String value = attr.getValue().replaceAll(" ", "")
                                          .replaceAll("\\r", "")
                                          .replaceAll("\\n", "")
                                          .toLowerCase();
            // 如果包含了不允许的字符，报错
            if(value.contains("*")){
                return false;
            }
        }
        return true;
    }
}
```

###3、在调用的地方使用

```java
// 创建配置类
WhitelistSetting whitelistSetting = new MyWhitelistSetting();
// 创建自定义白名单
CustomWhitelist htmlFilter = new CustomWhitelist(whitelistSetting);
// 刷新加载配置
htmlFilter.refreshSetting();
// 添加自定义XSS过滤器
htmlFilter.addXssValidator(new MyXssValidator());
String html = "<a href=\"http://www.google.com/*\">test</a>";
Document dirty = Parser.parseBodyFragment(html, "");
Cleaner cleaner = new Cleaner(htmlFilter);
Document clean = cleaner.clean(dirty);
System.out.println(htmlFilter.isValid());
System.out.println(Jsoup.clean(html, htmlFilter));
```