package me.arthinking.excel.demo;

import me.arthinking.excel.parser.ExcelParser;
import me.arthinking.excel.parser.FileImportExcelParser;
import me.arthinking.excel.persistent.spring.SpringPersistentService;
import me.arthinking.excel.reader.ExcelFileReader;

public class AmazonTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 创建数据表解析类
        //ExcelParser excelParser = new BatchExcelParser(new AmazonParseStrategy());  // 5050 
        ExcelParser excelParser = new FileImportExcelParser(new AmazonParseStrategy());  // 3688
        excelParser.setPersistentService(new SpringPersistentService());
        // 使用文件读取器解析文件
        new ExcelFileReader(excelParser)
            .persistentFile("D://document//amazon.xls");
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));
    }
}