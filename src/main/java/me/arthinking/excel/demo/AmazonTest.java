package me.arthinking.excel.demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import me.arthinking.excel.parser.ExcelParser;
import me.arthinking.excel.parser.FileImportExcelParser;
import me.arthinking.excel.reader.ExcelFileReader;

public class AmazonTest {

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        long start = System.currentTimeMillis();
        // 创建数据表解析类
        // ExcelParser excelParser = new BatchExcelParser(new AmazonParseStrategy());  // 5050 
        ExcelParser excelParser = new FileImportExcelParser(new AmazonParseStrategy());  // 3688
        // 使用文件读取器解析文件
        new ExcelFileReader(excelParser)
            .persistentFile("D://document//amazon.xls");
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));
    }
}