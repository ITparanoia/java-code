package me.arthinking.excel.demo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import me.arthinking.excel.item.Item;

import org.apache.commons.lang.StringEscapeUtils;

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
    public String getInsertSqlPre() {
        return "INSERT amazon_item(binding,category,title) VALUES";
    }

    @Override
    public void appendInsertValue(StringBuilder sb) {
        sb.append("('" + StringEscapeUtils.escapeSql(this.getBinding()) + 
                "'," + this.getCategory() + ",'" + StringEscapeUtils.escapeSql(this.getTitle()) +"')");
    }

    @Override
    public void setInsertSqlParameter(PreparedStatement statement) throws SQLException{
        statement.setString(1, this.getBinding());
        statement.setInt(2, this.getCategory());
        statement.setString(3, this.getTitle());
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