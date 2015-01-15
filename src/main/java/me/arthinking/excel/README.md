#Excel解析器

##项目说明
该项目通过把Excel文件中的数据导入到数据库中，方便使用SQL对数据进行统计分析。
    
局限：只适合插入简单格式的数据，一行对应一个对象，不适用于复杂的业务。    

##使用说明
###1、创建行数据实体
根据Excel每行数据的格式定义一个实体对象，继承Item，例如，有这样的一个数据表格：

对应的Item实现如下：    
```
public class AmazonItem implements Item{

    /**
     * 主键自动生成，无需写入到插入sql语句中
     */
    private long id;
    private String asin;
    private String binding;
    private int category;
    private String title;
    
    @Override
    public String getInsertSqlPreparedStatement() {
        return "INSERT amazon_item(asin,binding,category,title) VALUES(?,?,?,?)";
    }
    
    @Override
    public String getInsertSqlPre() {
        return "INSERT amazon_item(asin,binding,category,title) VALUES";
    }

    @Override
    public void appendInsertValue(StringBuilder sb) {
        sb.append("('"+ StringEscapeUtils.escapeSql(this.getAsin()) + "','" + StringEscapeUtils.escapeSql(this.getBinding()) + 
                "'," + this.getCategory() + ",'" + StringEscapeUtils.escapeSql(this.getTitle()) +"')");
    }

    @Override
    public void setInsertSqlParameter(PreparedStatement statement) throws SQLException{
        statement.setString(1, this.getAsin());
        statement.setString(2, this.getBinding());
        statement.setInt(3, this.getCategory());
        statement.setString(4, this.getTitle());
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getAsin() {
        return asin;
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
另外需要根据数据行实体创建对应的数据表

###3、编写解析业务
然后继承 AbstractExcelParser 实现自定义的Excel解析器，实现一行数据解析的业务代码，从而生成一个对象，对应一条记录：     
```
public class AmazonExcelParser extends AbstractExcelParser{

    protected static Logger logger = Logger.getLogger("access");
    
    public AmazonExcelParser(PersistentService persistentService) {
        super(persistentService);
    }

    @Override
    Item parseItem(HSSFRow row) {
        // ASIN
        HSSFCell cell = row.getCell(0);
        String asin = cell.getStringCellValue();
        // Binding
        cell = row.getCell(1);
        String binding = cell.getStringCellValue();
        // Category
        cell = row.getCell(2);
        int category = (int)cell.getNumericCellValue();
        // title
        cell = row.getCell(9);
        String title = cell.getStringCellValue();
        
        AmazonItem item = new AmazonItem();
        item.setAsin(asin);
        item.setBinding(binding);
        item.setCategory(category);
        item.setTitle(title);
        logger.info(title);
        return item;
    }
}
```

###4、调用
```
new ExcelFileReader(new AmazonExcelParser(new MySqlPersistentService()))
            .persistentFile("D://document//amazon.xls");
```