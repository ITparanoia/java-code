package me.arthinking.excel.demo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import me.arthinking.excel.item.Item;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * 业务实体，现有的业务实体可以直接继承Item接口，实现重要的方法
 * @author  Jason Peng
 * @create date 2015年1月16日
 */
public class AmazonItem implements Item{

    /**
     * 主键自动生成，无需写入到插入sql语句中
     */
    private long id;
    private String binding;
    private int category;
    private String title;
    
    @Override
    public String getInsertSqlPreparedStatement() {
        return "INSERT amazon_item(binding,category,title) VALUES(?,?,?)";
    }

    @Override
    public void setInsertSqlPreparedStatemetParameter(PreparedStatement statement) throws SQLException{
        statement.setString(1, this.getBinding());
        statement.setInt(2, this.getCategory());
        statement.setString(3, this.getTitle());
    }
    
    @Override
    public String getInsertSqlPre() {
        return "INSERT amazon_item(binding,category,title) VALUES";
    }

    @Override
    public void appendInsertValue(StringBuilder sb) {
        sb.append("('" + StringEscapeUtils.escapeSql(this.getBinding()) + 
                "'," + this.getCategory() + ",'" + StringEscapeUtils.escapeSql(this.getTitle()) +"')");
    }

    @Override
    public String getItemScript() {
        String script = "'" + StringEscapeUtils.escapeSql(this.binding) + "'," 
                            + this.category + ",'" 
                            + StringEscapeUtils.escapeSql(this.title) + "'\r\n";
        return script;
    }
    
    /**
     * LOAD DATA INFILE 'D:\\temp.db' IGNORE INTO TABLE amazon_item CHARACTER SET utf8 FIELDS TERMINATED BY ',' ENCLOSED BY '\'' LINES TERMINATED BY '\r\n' (`binding`,`category`,`title`)
     * 注意SQL语句在Java代码中的"\"和"'"两个字符的转义<br />
     *     \ --> \\
     *     ' --> ''
     */
    @Override
    public String getLoadDataScript() {
        String script = "load data infile 'D:\\\\temp.db' ignore into table amazon_item character set utf8 fields terminated by ',' enclosed by '''' lines terminated by '\\r\\n' (`binding`,`category`,`title`)";
        return script;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
   
}