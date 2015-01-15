package me.arthinking.excel.item;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.lang.StringEscapeUtils;

public class AmazonItem implements Item{

    /**
     * 主键自动生成，无需写入到插入sql语句中
     */
    private long id;
    private String asin;
    private String binding;
    private int category;
    private String title;
    
    @Override
    public String getInsertSqlPreparedStatement() {
        return "INSERT amazon_item(asin,binding,category,title) VALUES(?,?,?,?)";
    }
    
    @Override
    public String getInsertSqlPre() {
        return "INSERT amazon_item(asin,binding,category,title) VALUES";
    }

    @Override
    public void appendInsertValue(StringBuilder sb) {
        sb.append("('"+ StringEscapeUtils.escapeSql(this.getAsin()) + "','" + StringEscapeUtils.escapeSql(this.getBinding()) + 
                "'," + this.getCategory() + ",'" + StringEscapeUtils.escapeSql(this.getTitle()) +"')");
    }

    @Override
    public void setInsertSqlParameter(PreparedStatement statement) throws SQLException{
        statement.setString(1, this.getAsin());
        statement.setString(2, this.getBinding());
        statement.setInt(3, this.getCategory());
        statement.setString(4, this.getTitle());
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getAsin() {
        return asin;
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