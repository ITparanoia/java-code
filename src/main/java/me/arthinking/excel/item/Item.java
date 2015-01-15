package me.arthinking.excel.item;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public interface Item {

    String getInsertSqlPreparedStatement();
    
    String getInsertSqlPre();
    
    void appendInsertValue(StringBuilder sb);
    
    void setInsertSqlParameter(PreparedStatement statement) throws SQLException;
    
}