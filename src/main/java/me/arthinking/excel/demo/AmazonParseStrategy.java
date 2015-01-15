package me.arthinking.excel.demo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import me.arthinking.excel.item.Item;
import me.arthinking.excel.parser.ParseStrategy;

public class AmazonParseStrategy implements ParseStrategy{

    protected static Logger logger = Logger.getLogger("access");
    
    @Override
    public Item parseItem(HSSFRow row) {
        // ASIN
        HSSFCell cell = row.getCell(1);
        String binding = cell.getStringCellValue();
        // Category
        cell = row.getCell(2);
        int category = (int)cell.getNumericCellValue();
        // title
        cell = row.getCell(9);
        String title = cell.getStringCellValue();
        
        AmazonItem item = new AmazonItem();
        if(StringUtils.isNotBlank(title)){
            item.setBinding(binding);
            item.setCategory(category);
            item.setTitle(title);
            logger.info(title);
        }
        return item;
    }

}
