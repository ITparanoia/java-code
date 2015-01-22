package me.arthinking.html.jsoup.custom;

import java.util.ArrayList;
import java.util.List;

import me.arthinking.html.jsoup.custom.demo.MyXssValidator;
import me.arthinking.html.jsoup.custom.setting.Settings;
import me.arthinking.html.jsoup.custom.setting.WhitelistSetting;
import me.arthinking.html.jsoup.custom.xss.XssValidator;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

/**
 * singleton, call refreshSetting() when need refresh the whitelist setting.
 * @author  Jason Peng
 * @create date 2015-01-22
 */
public class CustomWhitelist extends Whitelist{

    private WhitelistSetting whitelistSetting = null;
    
    private List<XssValidator> validators = new ArrayList<XssValidator>();

    /**
     * validate if the HTML is legal
     */
    private boolean isValid = true;
    private CustomWhitelist(){}

    /**
     * constructor 
     * @param whitelistSetting
     */
    public CustomWhitelist(WhitelistSetting whitelistSetting){
        XssValidator validator = new MyXssValidator();
        this.addXssValidator(validator);
        this.whitelistSetting = whitelistSetting;
    }
    
    /**
     * call this method for init setting after instance or update setting source
     * void
     * @author Jason Peng
     * @update date 2015-01-22
     */
    public void refreshSetting(){
        // load settings
        Settings settings = this.whitelistSetting.reloadSetting();
        // init settings
        this.whitelistSetting.refresh(this, settings);
    }

    /**
     * add custom implement xssValidator, use these validators when call 
     * org.jsoup.safety.Cleaner.clean(Document dirtyDocument)
     * 
     * @param xssValidator
     * void
     * @author Jason Peng
     * @update date 2015年1月22日
     */
    public void addXssValidator(XssValidator xssValidator){
        this.validators.add(xssValidator);
    }
    
    /**
     * for fiter xss css style
     * Jsoup只是根据判断是否属性外的文本进行编码，无法对可能产生的XSS问题进行编码，
     * 所以可以在这里直接过滤掉可能产生XSS问题的内容
     * 
     * html传入Jsoup构建成Document的时候，会把Decimal HTML character解码，所以处理明确的编码就可以了
     * 
     * 过滤规则：
     *   - Jsoup先转换为解码后的HTML
     *   - 过滤script  style LINK标签
     *   - 处理href src style 属性（javascript alert vbscript livescript），先过滤掉无用的字符（空格，tab，换行，注释）
     *       style样式表禁止expression属性，禁止链接，防止远程样式表或脚本注入
     *   - 各种标签的事件属性
     * 
     * jsoup只对设置了协议的属性进行过滤，只能判断跳转的协议是否合法，协议后的字符串注入不理会，这里需要处理下
     *     在 Cleaner.copySafeNodes()里面获取安全的HTML
     * 
     */
    @Override
    protected boolean isSafeAttribute(String tagName, Element el, Attribute attr) {
        for(XssValidator xssValidator : validators){
            if(!xssValidator.validateAttribute(attr)){
                isValid = false;
                return false;
            }
        }
        return super.isSafeAttribute(tagName, el, attr);
    }

    @Override
    protected boolean isSafeTag(String tag) {
        if(!super.isSafeTag(tag)){
            // find unsafe tag
            isValid = false;
            return false;
        }
        return super.isSafeTag(tag);
    }

    public boolean isValid() {
        return isValid;
    }

}