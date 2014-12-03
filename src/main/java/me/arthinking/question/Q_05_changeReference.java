package me.arthinking.question;

public class Q_05_changeReference {
    
    public static void main(String[] args){
        Foo f = new Foo(1);
        Foo ff = new Foo(11);
        Foo fff = new Foo(111);
        changeReference(f, ff, fff); // Has no effect outside the method. It won't change the reference!
        System.out.println(f.getCount());
        modifyReference(f); // It will modify the object that the reference variable "f" refers to!
        System.out.println(f.getCount());
   }
   public static void changeReference(Foo a, Foo aa, Foo aaa){  
        Foo b = new Foo(2);  // new    dup    iconst_2    invokespecial  astore_1  aload_1  
        a = b;  // astore_0
        aa = b;
   }
   public static void modifyReference(Foo c){
        c.setCount(3);
   }
   
}

class Foo{
    private int count;
    public Foo(int count){
        this.count = count;
    }
    
    public void setCount(int count){
        this.count = count;
    }
    
    public int getCount(){
        return this.count;
    }
    
}
