package me.arthinking.excel.persistent;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import me.arthinking.excel.item.Item;

public class SpringPersistentService implements PersistentService{

   
    @Override
    public void batchSave(List<Item> itemList) {
        
    }

    @Override
    public void batchMultiSave(List<Item> itemList) throws SQLException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void filebatchSave(InputStream is, String sqlScript) {
        // TODO Auto-generated method stub
        
    }

}