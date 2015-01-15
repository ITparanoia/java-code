package me.arthinking.excel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import me.arthinking.excel.parser.AmazonExcelParser;
import me.arthinking.excel.reader.ExcelFileReader;

public class AmazonTest {

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        // 创建数据表解析类
        AmazonExcelParser excelParser = new AmazonExcelParser();
        // 使用文件读取器解析文件
        new ExcelFileReader(excelParser)
            .persistentFile("D://document//amazon.xls");
    }
}