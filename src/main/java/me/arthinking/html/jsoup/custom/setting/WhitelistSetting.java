package me.arthinking.html.jsoup.custom.setting;

import org.jsoup.safety.Whitelist;


/**
 * 白名单配置项接口
 * 类的说明
 * @author  Jason Peng
 * @create date 2015年1月22日
 */
public interface WhitelistSetting {

    /**
     * 初始化公共配置
     * 具体实现的应用根据自己的需求设置默认的白名单
     * void
     * @author Jason Peng
     * @update date 2015年1月22日
     */
    public void initCommonSetting(Whitelist whitelist);
    
    /**
     * 刷新配置
     * void
     * @author Jason Peng
     * @update date 2015年1月22日
     */
    public void refresh(Whitelist whitelist, Settings settings);
    
    /**
     * 加载设置
     * @return
     * Settings
     * @author Jason Peng
     * @update date 2015年1月22日
     */
    public Settings reloadSetting();
}
