package me.arthinking.excel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import me.arthinking.excel.parser.AmazonExcelParser;
import me.arthinking.excel.persistent.MySqlPersistentService;
import me.arthinking.excel.reader.ExcelFileReader;

public class AmazonTest {

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        new ExcelFileReader(new AmazonExcelParser(new MySqlPersistentService()))
            .persistentFile("D://document//amazon.xls");
    }
}