package me.arthinking.excel;

/**
 * 实现功能：
 * excel文件太大的时候打开速度很慢，各种操作也非常卡，这个时候使用程序对excel数据进行分析
 * 输入excel的目录，读取数据到数据库，并通过SQL语句按照各种条件进行统计
 * 
 * 输入路径，文件解析，生成对象，批量插入数据库
 * ExcelReader readFile 
 * AbstractExcelParser Item#parseItem(Grid)  List#parseFile(InputStream)
 * 
 * Item getSql()
 *   
 * DbService update(List<Item>);
 * DbUtils getConnection() closeConnection() 
 * 
 * @author  Jason Peng
 * @create date 2015年1月14日
 */
public class ParseExcel {

    /*
    public static void main(String[] args) {
        
        String filePath = "D://document//amazon.xls";
        
        Map<String, Product> map = new HashMap<String, Product>();
        List<String> productName = new ArrayList<String>();
        
        HSSFWorkbook workBook = null;
        // EntityService service = new EntityService();
        try {
            workBook = new HSSFWorkbook(new FileInputStream(filePath));
            int sheetNum = workBook.getNumberOfSheets();
            System.out.println("sheetNum: " + sheetNum);
            for(int k=0; k<sheetNum; k++){
                HSSFSheet sheet = workBook.getSheetAt(k);
                int totalSize = sheet.getLastRowNum();
                HSSFRow row = null;
                System.out.println("总行数： " + totalSize);
                if(totalSize <= 0){
                    continue;
                }
                for(int i = 1; i <= totalSize; i++){
                    row = sheet.getRow(i);
                    if(row != null){
                        // ASIN
                        HSSFCell cell = row.getCell(0);
                        String asin = cell.getStringCellValue();
                        // Binding
                        cell = row.getCell(1);
                        String binding = cell.getStringCellValue();
                        // Category
                        cell = row.getCell(2);
                        int category = (int)cell.getNumericCellValue();
                        // Date
                        cell = row.getCell(3);
                        String date = cell.getStringCellValue();
                        // Earnings
                        cell = row.getCell(4);
                        double earnings = cell.getNumericCellValue();
                        // price
                        cell = row.getCell(5);
                        String price = cell.getStringCellValue();
                        // Qty
                        cell = row.getCell(6);
                        int qty = (int)cell.getNumericCellValue();
                        // Rate
                        cell = row.getCell(7);
                        double rate = cell.getNumericCellValue();
                        // Revenue
                        cell = row.getCell(8);
                        String revenue = cell.getStringCellValue();
                        // Revenue
                        cell = row.getCell(9);
                        String title = cell.getStringCellValue();
                        
                        if(StringUtils.isNotBlank(title)){
                             title = title.split("-")[0];
                             Entity entity = new Entity();
                             entity.setBinding(binding);
                             entity.setCategory(category);
                             entity.setTitle(title);
                             service.insert(entity);
                            title = title.split("-")[0];
                            if(productName.contains(title)){
                                Product product = map.get(title);
                                product.setCount(product.getCount() + 1);
                            } else {
                                Product product = new Product();
                                product.setBinding(binding);
                                product.setCategory(category);
                                product.setTitle(title);
                                product.setCount(1);
                                map.put(title, product);
                                productName.add(title);
                            }
                        }
                        if(i%100 == 1){
                            System.out.println(i);
                        }
                        if(typeCell != null){
                            // 类型
                            String dataType = "";

                            dataType = typeCell.getRichStringCellValue().getString();

                            // 是否最佳回答
                            HSSFCell isBestCell = row.getCell(2);
 
                            // 内容
                            HSSFCell contentCell = row.getCell(3);

                        }
                    }
                }
                
                service.closeConnection();
            }
            
//                for(int i=0; i<productName.size(); i++){
//                    String name = productName.get(i);
//                    Product product = map.get(name);
//                    System.out.println(" 数量：" + product.getCount() + " binding: " + product.getBinding() + " category: " + product.getCategory() + " title: " + product.getTitle());
//                }
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    */
}