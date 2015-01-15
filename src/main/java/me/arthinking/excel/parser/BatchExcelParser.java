package me.arthinking.excel.parser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.arthinking.excel.item.Item;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 批量导入到数据库
 * @author  Jason Peng
 * @create date 2015年1月15日
 */
public class BatchExcelParser extends AbstractExcelParser{

    public BatchExcelParser(ParseStrategy strategy) {
        super(strategy);
    }
    
    private int batchSize = 1000;
    
    
    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    /**
     * 解析Excel文件
     * @param hssfWorkbook
     * void
     * @author Jason Peng
     * @throws SQLException 
     * @throws IOException 
     * @update date 2015年1月14日
     */
    @Override
    public void parseFile(HSSFWorkbook workbook) throws SQLException{
        int sheetNum = workbook.getNumberOfSheets();
        logger.info("sheetNum: " + sheetNum);
        HSSFRow row = null;
        for(int k=0; k<sheetNum; k++){
            HSSFSheet sheet = workbook.getSheetAt(k);
            int totalSize = sheet.getLastRowNum();
            logger.info("current process sheet: " + sheetNum);
            if(totalSize <= 0){
                continue;
            }
            List<Item> itemList = new ArrayList<Item>();
            for(int i = 1; i < totalSize; i++){
                row = sheet.getRow(i);
                if(row != null){
                    Item item = strategy.parseItem(row);
                    if(itemList.size() <= batchSize){
                        itemList.add(item);
                    } else {
                        persistentService.batchMultiSave(itemList);
                        itemList.clear();
                    }
                }
            }
        }
    }
    
}
