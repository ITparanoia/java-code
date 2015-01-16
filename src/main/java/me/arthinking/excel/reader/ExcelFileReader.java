package me.arthinking.excel.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import me.arthinking.excel.parser.ExcelParser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Usage: new ExcelFileReader(new AmazonExcelParser()).persistentFile(path);
 * @author  Jason Peng
 * @create date 2015年1月14日
 */
public class ExcelFileReader implements FileReader{

    private ExcelParser parser;
    
    private InputStream inputStream = null;
    
    public ExcelFileReader(ExcelParser parser){
        this.parser = parser;
    }
    
    /**
     * 解析Excel文件，并把解析到的数据存入数据库中
     * 模板方法，确保解析完成正确的关闭了文件流
     * @param filePath
     * @throws FileNotFoundException
     * @throws IOException
     * void
     * @author Jason Peng
     * @throws SQLException 
     * @update date 2015年1月14日
     */
    @Override
    public final void persistentFile(String filePath) {
        HSSFWorkbook hssfWorkbook;
        try {
            hssfWorkbook = readExcelFile(filePath);
            this.parseExcel(hssfWorkbook);
            this.closeInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @author Jason Peng
     * @throws SQLException 
     * @throws IOException 
     * @update date 2015年1月14日
     */
    private void parseExcel(HSSFWorkbook hssfWorkbook) throws SQLException, IOException{
        parser.parseFile(hssfWorkbook);
    }
    
    private HSSFWorkbook readExcelFile(String filePath) throws FileNotFoundException, IOException {
        inputStream = new FileInputStream(filePath);
        HSSFWorkbook workBook = new HSSFWorkbook(inputStream);
        return workBook;
    }
    
    private void closeInputStream(){
        try {
            if(inputStream != null){
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}