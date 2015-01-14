package me.arthinking.excel.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileReader {

    /**
     * 读取文件
     * 
     * void
     * @author Jason Peng
     * @update date 2015年1月14日
     */
    void readFile(String filePath) throws FileNotFoundException, IOException;
}
