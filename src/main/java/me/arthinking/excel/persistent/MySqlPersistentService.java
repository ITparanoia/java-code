package me.arthinking.excel.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import me.arthinking.excel.item.Item;
import me.arthinking.excel.persistent.mysql.MySql;

public class MySqlPersistentService implements PersistentService{
    
    protected static Logger logger = Logger.getLogger("access");
    
    @Override
    public void batchSave(List<Item> itemList) throws SQLException {
        if(itemList.size() <= 0) return;
        Connection connection = MySql.getConnection();
        String sql = itemList.get(0).getInsertSqlPreparedStatement();   
        PreparedStatement prest = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);   
        for(int i = 0; i < itemList.size(); i++){
            itemList.get(i).setInsertSqlParameter(prest);
            prest.addBatch();   
        }
        prest.executeBatch(); 
        prest.close();
    }

    @Override
    public void batchMultiSave(List<Item> itemList) throws SQLException {
        if(itemList.size() <= 0) return;
        Connection connection = MySql.getConnection();
        StringBuilder sql = new StringBuilder(itemList.get(0).getInsertSqlPre());
        for(int i=0; i<itemList.size(); i++){
            if(i>0) sql.append(",");
            itemList.get(i).appendInsertValue(sql);
        }
        logger.info(sql.toString());
        Statement statement = connection.createStatement();
        statement.execute(sql.toString());
        
    }

    @Override
    public void batchFiledSave(String filePath) {
        // TODO Auto-generated method stub
        
    }
    
}