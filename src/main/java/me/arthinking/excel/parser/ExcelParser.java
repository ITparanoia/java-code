package me.arthinking.excel.parser;

import java.sql.SQLException;

import me.arthinking.excel.persistent.PersistentService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface ExcelParser {

    void setPersistentService(PersistentService persistentService);
    
    /**
     * 解析文件
     * @param workbook
     * @throws SQLException
     * void
     * @author Jason Peng
     * @update date 2015年1月15日
     */
    void parseFile(HSSFWorkbook workbook) throws SQLException;
}