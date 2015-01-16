package me.arthinking.excel.parser;

import me.arthinking.excel.persistent.MySqlPersistentService;
import me.arthinking.excel.persistent.PersistentService;

import org.apache.log4j.Logger;

public abstract class AbstractExcelParser implements ExcelParser{

    protected static Logger logger = Logger.getLogger("access");
    
    /**
     * 默认使用MySQL的持久化服务
     */
    PersistentService persistentService = new MySqlPersistentService();
    
    /**
     * 调用者传入自己的解析策略进行解析
     */
    ParseStrategy strategy;
    
    /**
     * 禁止调用无参构造函数，必须初始化策略
     */
    private AbstractExcelParser(){}
    
    public AbstractExcelParser(ParseStrategy strategy){
        this.strategy = strategy;
    }
    
    @Override
    public void setPersistentService(PersistentService persistentService){
        this.persistentService = persistentService;
    }
}