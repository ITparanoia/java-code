package me.arthinking.question;

import sun.reflect.Reflection;

/**
 * 获得调用者的类名和方法名的例子
 * 
 * 问题：为什么不建议调用sun包，如何通过其他方法确定调用者呢？
 * 
 * 说明：此功能多用于日志记录框架，通过获取堆栈信息得到调用者的类名，方法名，行号。
 * 
 * @author  Jason Peng
 * @create date 2015年3月9日
 */
public class Q_07_stack_trace {

    public static void main(String[] args) {
        TestB b = new TestB();
        b.execute();
    }
    
}

class TestA{
    public void execute(){

        // 通过堆栈信息获取调用当前方法的类名和方法名
        String className = "";
        String methodName = "";
        Class clazz = null;
        StackTraceElement[] elements = new Throwable().getStackTrace();
        for (int i = 0; i < elements.length; i++){
           if (this.getClass().getName().equals(elements[i].getClassName())){
              // 获取堆栈的下一个元素，就是调用者元素
              // 如果想要获取当前方法所在类的信息，直接读取elements[i]就可以了
              className = elements[i + 1].getClassName();
              methodName = elements[i + 1].getMethodName();
              break;
           }
        }
        System.out.println(className + "." + methodName + "\n");
        
        // 该方法也可以获取调用代码所在的行号的类所在的文件，
        // 但是这种方法不能获得调用者的类型，因为不知道调用者的的类装载器，
        // 所以也就不能使用Class.forName(String)得到类型
        
        // 可以尝试使用以下方法获得调用者的类型（但该方法不建议使用，后面有说明）
        clazz = Reflection.getCallerClass(2);
        System.out.println("invoker's class loader: " + clazz.getClassLoader() + "\nclass name: " + clazz.getName());
        // 该方法比使用堆栈的效率高
        // 如果想要获取当前方法所在类的信息，调用 Reflection.getCallerClass(1) 就可以了，Reflection.getCallerClass(0) 是Reflection对象
        
        /**
         * 
         * 为什么不建议调用sun包？
         * 
         * 这篇文章中提到了JDK 8弃用 sun.reflect.Reflection.getCallerClass：  
         *     http://www.infoq.com/cn/news/2013/07/Oracle-Removes-getCallerClass
         *     
         * 但是JDK 8 实际上新特性如下：http://www.iteye.com/news/28870-java-8-release
         *     其中 JEP 176：自动检测识别Caller-Sensitive方法：http://openjdk.java.net/jeps/176
         * 
         *     JDK 8中在getCallerClass方法加了 @sun.reflect.CallerSensitive 注解，该注解是提供给JVM底层读取处理的，详细参考：
         *         http://stackoverflow.com/questions/22626808/what-does-the-sun-reflect-callersensitive-annotation-mean
         *     
         *     查看getCallerClass的底层实现（底层实现：http://cr.openjdk.java.net/~mchung/jdk8/webrevs/8025799/hotspot.00/src/share/vm/prims/jvm.cpp.html #668）
         *     可以发现，当传入了depth的时候，调用了net.reflect.Reflection类的这个方法：
         *     
         *     @java.lang.Deprecated
         *     public static native java.lang.Class getCallerClass(int arg0);
         *     
         *     这个方法在底层实现中还是通过兼容旧版本的代码去执行的，所以这个方法加了一个 @Deprecated 注解，这也是为什么不推荐上面那种方法获取调用者类型的原因。
         *     
         * 类似的， JDK8 中的 @java.lang.Class 的 forName(className) 方法也加了这个注解，因为forName方法里面也调用给到了Reflection.getCallerClass()
         * 
         * 在spring-loaded的源码中：https://github.com/spring-projects/spring-loaded/blob/master/springloaded/src/main/java/org/springsource/loaded/ri/ReflectiveInterceptor.java#L213
         * 这里也调用了getCallerClass()方法，作为临时的方案去兼容JDK 7u25，需要添加系统属性：jdk.reflect.allowGetCallerClass（参考这里：http://permalink.gmane.org/gmane.comp.java.openjdk.jdk7u.devel/6573）
         * 
         * 开发者不应该调用sun包，Oracle一直在提醒开发者，调用sun.*包里面的方法是危险的。上面用到的Reflection.getCallerClass()也是在这个包里面的，
         * 因为sun包并不包含在Java平台的标准中，它与操作系统相关，在不同的操作系统如Solaris，Windows，Linux，Mac等中的实现也各不相同，并且可能随着JDK版本而变化。
         * 详细说明: http://www.oracle.com/technetwork/java/faq-sun-packages-142232.html
         *     
         */
    }
}

class TestB{
    public void execute(){
        TestA a = new TestA();
        a.execute();
    }
}