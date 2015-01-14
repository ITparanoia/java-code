package me.arthinking.excel.persistent.mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {
    
    private static Connection connection = null;
    
	public static String driver = "com.mysql.jdbc.Driver";

	public static String url = "jdbc:mysql://127.0.0.1:3306/test";

	public static String user = "root"; 

	private static Object syncObj = new Object();
	
	public static String password = "123456";
    
    public static Connection getConnection (){
      try {
          synchronized(syncObj){
              if(connection == null){
                  connection = DriverManager.getConnection(url, user, password);
              }
              return connection;
          }
          
	} catch (SQLException e) {
		e.printStackTrace();
	}	
      return null;
    }
    
    public static void closeConnection(){
        try {
            if(connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}