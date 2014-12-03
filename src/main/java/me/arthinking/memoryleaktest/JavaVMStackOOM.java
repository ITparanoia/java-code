package me.arthinking.memoryleaktest;

/**
 * VM Args:-Xss2M
 * @author zzm
 */
public class JavaVMStackOOM {
    
    private void dontStop(){
        while(true){
        }
    }
    
    public void stackLeakByThread(){
        while(true){
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    dontStop();
                }
            });
            thread.start();
        }
    }
    
    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
/**
Result:
Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
    at java.lang.Thread.start0(Native Method)
    at java.lang.Thread.start(Unknown Source)
    at me.arthinking.memoryleaktest.JavaVMStackOOM.stackLeakByThread(JavaVMStackOOM.java:22)
    at me.arthinking.memoryleaktest.JavaVMStackOOM.main(JavaVMStackOOM.java:28)
*/