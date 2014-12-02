package me.arthinking.code4jvmnote;

/**
 * 
 * ����Ӧ�����ӣ���ͬ���͵ĳ��������ʼ����Ӱ��
 * @author  Jason Peng
 * @create date 2014��12��2��
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
         * ��ȡָ����ľ�̬�򣬲�����ѹ��ջ���� 
         *
         * bipush
         * ��һ��byte�ͳ���ֵ������ջ��
         * 
         * ldc     #21;
         * Ҫִ��ldcָ�JVM���Ȳ���index��ָ���ĳ�������ڣ���indexָ��ĳ�������ڣ�JVM�������CONSTANT_Integer_info��CONSTANT_Float_info��CONSTANT_String_info��ڡ������û����Щ��ڣ�JVM��������ǡ������������hahaJVM���ҵ�CONSTANT_String_info��ڣ�ͬʱ������ָ�򱻾���String�����ɽ�������ڵĽ��̲�����������ѹ�������ջ��
         * 
         * ���Է��֣����鳣��������ڵ������Ԫ�أ����ַ��������͵ĳ�����ֱ���ڳ������б���һ�����ݣ�������ǳ���������ĳ�ʼ������C7_4_RoleEnum�����ʵ������Ҳ�������ڵ������Ԫ�ء�
         * 
         */
    }
}
