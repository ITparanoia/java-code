package me.arthinking.excel.parser;

import me.arthinking.excel.item.Item;

import org.apache.poi.hssf.usermodel.HSSFRow;

public interface ParseStrategy {

    Item parseItem(HSSFRow row);
}
