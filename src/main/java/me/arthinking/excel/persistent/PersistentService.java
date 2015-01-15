package me.arthinking.excel.persistent;

import java.sql.SQLException;
import java.util.List;

import me.arthinking.excel.item.Item;

/**
 * 持久化服务类
 * @author  Jason Peng
 * @create date 2015年1月14日
 */
public interface PersistentService {

    void batchSave(List<Item> itemList) throws SQLException;
    
    void batchCombineSave(List<Item> itemList) throws SQLException;
}