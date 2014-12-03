package me.arthinking.code4jvmnote;

/**
 * 
 * 被动引用例子，不同类型的常量对类初始化的影响
 * javap -verbose C7_4_RoleEnum
 * @author  Jason Peng
 * @create date 2014年12月2日
 */
public class C7_4_RoleEnum {
    
    private String name = "";
    
    public static final String[] USERS = {"Jason", "arthinking"};
    
    public static final String MSG = "good job!";
    
    public static final int COUNT = 10;
    
    public static final C7_4_RoleEnum TEST = new C7_4_RoleEnum();
    
    // use enum
    public enum Roles{
        Admin(0),
        Customer(1);
        
        private int id;
        
        Roles(int id){
            this.id = id;
        }
        
        public int getId(){
            return this.id;
        }
    }
    
    // or set as private
    private static final String REQ_URL = "http://www.google.com/";

    // outer method call this to get REQ_URL to avoid compile problem.
    public String getReqUrl(){
        return REQ_URL + "/" + this.name;
    } 
    
    public static void main(String[] args){
        System.out.println(Roles.Admin.getId());
    }
}

class TestArrayConstant{
    public void test(){
        System.out.println(C7_4_RoleEnum.USERS[0]);  //  getstatic       #21; //Field me/arthinking/code4jvmnote/C7_4_RoleEnum.USERS:[Ljava/lang/String;
        System.out.println(C7_4_RoleEnum.COUNT);  //  bipush  10
        System.out.println(C7_4_RoleEnum.MSG);  //  ldc     #21; //String good job!
        System.out.println(C7_4_RoleEnum.TEST);  //  getstatic       #21; //Field me/arthinking/code4jvmnote/C7_4_RoleEnum.TEST:Lme/arthinking/code4jvmnote/C7_4_RoleEnum;
        /**
         * getstatic  
         * 获取指定类的静态域，并将其压入栈顶。 
         *
         * bipush
         * 将一个byte型常量值推送至栈顶
         * 
         * ldc     #21;
         * 要执行ldc指令，JVM首先查找index所指定的常量池入口，在index指向的常量池入口，JVM将会查找CONSTANT_Integer_info，CONSTANT_Float_info和CONSTANT_String_info入口。如果还没有这些入口，JVM会解析它们。而对于上面的hahaJVM会找到CONSTANT_String_info入口，同时，将把指向被拘留String对象（由解析该入口的进程产生）的引用压入操作数栈。
         * 
         * 可以发现，数组常量会从所在的类加载元素，而字符串和整型的常量会直接在常量池中保存一份数据，不会除非常量所在类的初始化。而C7_4_RoleEnum对象的实例常量也会在所在的类加载元素。
         * 
         */
    }
}
