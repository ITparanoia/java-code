package me.arthinking.excel.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import me.arthinking.excel.item.Item;
import me.arthinking.excel.persistent.mysql.MySql;

public class MySqlPersistentService implements PersistentService{
    
    @Override
    public void batchSave(List<Item> itemList) throws SQLException {
        if(itemList.size() <= 0) return;
        Connection connection = MySql.getConnection();
        String sql = itemList.get(0).getInsertSql();   
        PreparedStatement prest = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);   
        for(int x = 0; x < itemList.size(); x++){
            itemList.get(x).setInsertSqlParameter(prest);
            prest.addBatch();   
        }
        prest.executeBatch(); 
        prest.close();
    }

}