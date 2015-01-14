package me.arthinking.excel.item;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public interface Item {

    String getInsertSql();
    
    void setInsertSqlParameter(PreparedStatement statement) throws SQLException;
}
