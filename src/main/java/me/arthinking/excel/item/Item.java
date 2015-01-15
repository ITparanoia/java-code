package me.arthinking.excel.item;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Item {

    /**
     * 获取单个对象的插入PreparedStatement语句
     * @return
     * String
     * @author Jason Peng
     * @update date 2015年1月15日
     */
    String getInsertSqlPreparedStatement();

    /**
     * 设置占位符
     * @param statement
     * @throws SQLException
     * void
     * @author Jason Peng
     * @update date 2015年1月15日
     */
    void setInsertSqlParameter(PreparedStatement statement) throws SQLException;
    
    /**
     * 获取合并方式导入的插入sql语句前缀<br />
     * 如：INSERT amazon_item(asin,binding,category,title) VALUES
     * @return
     * String
     * @author Jason Peng
     * @update date 2015年1月15日
     */
    String getInsertSqlPre();
    
    /**
     * 追加插入的value
     * 如：('abc', 'notebook', 1, 'macbook pro')
     * @param sb
     * void
     * @author Jason Peng
     * @update date 2015年1月15日
     */
    void appendInsertValue(StringBuilder sb);
    
}