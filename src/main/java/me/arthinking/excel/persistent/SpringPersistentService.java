package me.arthinking.excel.persistent;

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
    public void batchFiledSave(String filePath) {
        // TODO Auto-generated method stub
        
    }

}