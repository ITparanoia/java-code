package me.arthinking.excel;


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

class Product {
    
    public Product(){
        
    }
    private int count;
    private String title;
    private int category;
    private String binding;
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getCategory() {
        return category;
    }
    public void setCategory(int category) {
        this.category = category;
    }
    public String getBinding() {
        return binding;
    }
    public void setBinding(String binding) {
        this.binding = binding;
    }
    
}
