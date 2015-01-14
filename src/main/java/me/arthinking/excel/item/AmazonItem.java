package me.arthinking.excel.item;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AmazonItem implements Item{

    /**
     * 主键自动生成
     */
    private long id;
    private String asin;
    private String binding;
    private int category;
    private String title;
    
    @Override
    public String getInsertSql() {
        return "INSERT amazon_item(asin,binding,category,title) VALUES(?,?,?,?)";
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