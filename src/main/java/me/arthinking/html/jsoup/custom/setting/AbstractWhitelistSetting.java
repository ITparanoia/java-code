package me.arthinking.html.jsoup.custom.setting;

import java.util.List;

import org.jsoup.safety.Whitelist;

/**
 * 聚超值的白名单配置项加载类
 * @author  Jason Peng
 * @create date 2015年1月22日
 */
public abstract class AbstractWhitelistSetting implements WhitelistSetting{

    @Override
    public void refresh(Whitelist whitelist, Settings settings) {
        initCommonSetting(whitelist);
        List<String> tagNames = settings.getTagNames();
        whitelist.addTags(tagNames.toArray(new String[tagNames.size()]));
        List<Tag> tagList = settings.getTags();
        for(Tag tag : tagList){
            List<String> attributes = tag.getAttributes();
            whitelist.addAttributes(tag.getTagName(), attributes.toArray(new String[attributes.size()]));
        }
    }

}