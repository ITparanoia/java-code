#Excel解析器

##项目说明
该项目通过把Excel文件中的数据选择性（选择具体的行和符合规则的数据）导入到已有的数据库中，方便使用SQL对数据进行统计分析。

如果是想直接把整个Excel导入到数据库的新建表中，直接使用csv的导入功能就可以了。参考：
> [如何将excel表格的数据导入到mysql数据中去](http://jingyan.baidu.com/article/fc07f9891cb56412ffe5199a.html "如何将excel表格的数据导入到mysql数据中去")    
> [从phpMyAdmin批量导入Excel内容到MySQL](http://jingyan.baidu.com/album/5225f26b12d968e6fb090857.html "从phpMyAdmin批量导入Excel内容到MySQL")    

以上导入的原理都是一样的，该项目亦可通过load data导入数据到库，方便自定义解析Excel文件，适合Excel文件过大不方便编辑和需要根据特定业务导入的场景。
    
局限：只适合插入简单格式的数据，一行对应一个对象，不适用于复杂的业务。    

##使用说明
###1、创建行数据实体
根据Excel每行数据的格式定义一个实体对象，继承Item，例如，有这样的一个数据表格：    

![](
https://raw.githubusercontent.com/arthinking/arthinking.github.io/master/images/2015/01/20150115-excel-parser01.png)    

对应的Item实体如下，对于现有的数据库实体，可以直接实现Item接口：    
```java
public class AmazonItem implements Item{

    /**
     * 主键自动生成，无需写入到插入sql语句中
     */
    private long id;
    private String binding;
    private int category;
    private String title;
    
    @Override
    public String getInsertSqlPreparedStatement() {
        return "INSERT amazon_item(binding,category,title) VALUES(?,?,?)";
    }
    
    @Override
    public String getInsertSqlPre() {
        return "INSERT amazon_item(binding,category,title) VALUES";
    }

    @Override
    public void appendInsertValue(StringBuilder sb) {
        sb.append("('" + StringEscapeUtils.escapeSql(this.getBinding()) + 
                "'," + this.getCategory() + ",'" + StringEscapeUtils.escapeSql(this.getTitle()) +"')");
    }

    @Override
    public void setInsertSqlParameter(PreparedStatement statement) throws SQLException{
        statement.setString(1, this.getBinding());
        statement.setInt(2, this.getCategory());
        statement.setString(3, this.getTitle());
    }
    

    @Override
    public String getItemScript() {
        String script = "'" + StringEscapeUtils.escapeSql(this.binding) + "'," 
                            + this.category + ",'" 
                            + StringEscapeUtils.escapeSql(this.title) + "'\r\n";
        return script;
    }
    
    /**
     * LOAD DATA INFILE 'D:\\temp.db' IGNORE INTO TABLE amazon_item CHARACTER SET utf8 FIELDS TERMINATED BY ',' ENCLOSED BY '\'' LINES TERMINATED BY '\r\n' (`binding`,`category`,`title`)
     */
    @Override
    public String getLoadDataScript() {
        String script = "load data infile 'D:\\\\temp.db' ignore into table amazon_item character set utf8 fields terminated by ',' enclosed by '''' lines terminated by '\\r\\n' (`binding`,`category`,`title`)";
        return script;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
   
}
```

###2、配置数据库，配置数据表
数据库的配置到MySql类里面进行    
另外确保数据库存在于实体对应的表

###3、编写解析业务
然后实现ParseStrategy实现自定义的解析策略，实现一行数据解析的业务代码，从而生成一个对象，对应一条记录：     
```java
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
        
        AmazonItem item = null;
        if(StringUtils.isNotBlank(title)){
            item = new AmazonItem();
            item.setBinding(binding);
            item.setCategory(category);
            item.setTitle(title);
            logger.info(title);
        }
        return item;
    }

}
```

###4、调用
```java
// 创建数据表解析类
long start = System.currentTimeMillis();
// ExcelParser excelParser = new BatchExcelParser(new AmazonParseStrategy());  // 5050 
ExcelParser excelParser = new FileImportExcelParser(new AmazonParseStrategy());  // 3688
// 使用文件读取器解析文件
new ExcelFileReader(excelParser)
    .persistentFile("D://document//amazon.xls");
long end = System.currentTimeMillis();
System.out.println("执行时间：" + (end - start));
```