package me.arthinking.excel.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import me.arthinking.excel.item.Item;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

/**
 * 使用load data从文件批量导入到数据库，比BatchExcelParser速度更快
 * @author  Jason Peng
 * @create date 2015年1月15日
 */
public class FileImportExcelParser extends AbstractExcelParser{

    private int batchSize = 1000;
    
    FileOutputStream out = null; 

    public FileImportExcelParser(ParseStrategy strategy) {
        super(strategy);
    }
    
    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }
    
    private void beginWriteFile() throws FileNotFoundException{
        out = new FileOutputStream(new File("D://temp.db"));
    }
    
    private void writeItemToDataScript(Item item) throws IOException{
        out.write(item.getItemScript().getBytes());
    }
    
    private void endWriteFile() throws IOException{
        out.close();
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
        try {
            this.beginWriteFile();
            String loadDataScript = "";
            for(int k=0; k<sheetNum; k++){
                HSSFSheet sheet = workbook.getSheetAt(k);
                int totalSize = sheet.getLastRowNum();
                logger.info("current process sheet: " + sheetNum);
                if(totalSize <= 0){
                    continue;
                }
                for(int i = 1; i < totalSize; i++){
                    row = sheet.getRow(i);
                    if(row != null){
                        Item item = strategy.parseItem(row);
                        if(item != null){
                            // 写数据到文件
                            this.writeItemToDataScript(item);
                            if(StringUtils.isEmpty(loadDataScript)){
                                loadDataScript = item.getLoadDataScript();
                            }
                        }
                    }
                }
            }
            this.endWriteFile();
            FileInputStream is = new FileInputStream(new File("D://temp.db"));
            // 执行批量导入操作
            this.persistentService.filebatchSave(is, loadDataScript);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}