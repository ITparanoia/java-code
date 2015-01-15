package me.arthinking.excel.parser;

import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import me.arthinking.excel.item.Item;
import me.arthinking.excel.persistent.PersistentService;

public interface ExcelParser {

    void setPersistentService(PersistentService persistentService);
    
    Item parseItem(HSSFRow row);
    
    void parseFile(HSSFWorkbook workbook) throws SQLException;
}
