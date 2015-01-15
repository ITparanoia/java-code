package me.arthinking.excel.persistent;

import java.sql.SQLException;
import java.util.List;

import me.arthinking.excel.item.Item;

/**
 * 持久化服务类
 * @author  Jason Peng
 * @create date 2015年1月14日
 */
public interface PersistentService {

    /**
     * 批量插入，多个插入sql语句一起提交
     * @param itemList
     * @throws SQLException
     * void
     * @author Jason Peng
     * @update date 2015年1月15日
     */
    void batchSave(List<Item> itemList) throws SQLException;
    
    /**
     * 使用inser into table(a,b,c) values(1,2,3),(1,23),(1,2,3)这种格式加快插入速度
     * 
     * 关于MySQL的插入效率：http://dev.mysql.com/doc/refman/5.1/en/insert-speed.html
     * 
     * @param itemList
     * @throws SQLException
     * void
     * @author Jason Peng
     * @update date 2015年1月15日
     */
    void batchMultiSave(List<Item> itemList) throws SQLException;
    
    /**
     * 通过文件的形式导入数据库，更快的写入方法
     * @param filePath
     * void
     * @author Jason Peng
     * @update date 2015年1月15日
     */
    void batchFiledSave(String filePath);
}