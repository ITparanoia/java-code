package me.arthinking.spring.ioc;

import me.arthinking.spring.ioc.bean.Car;
import me.arthinking.spring.ioc.processor.MyBeanPostProcessor;
import me.arthinking.spring.ioc.processor.MyInstantiationAwareBeanPostProcessor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BeanLifeCycleTest {

    private static void LifeCycleInBeanFactory(){
        // 下面两句装载配置文件并启动容器
        Resource res = new ClassPathResource("me/arthinking/spring/ioc/beans.xml");
        BeanFactory bf = new XmlBeanFactory(res);

        // 向容器中注册MyBeanPostProcessor后处理器
        ((ConfigurableBeanFactory)bf).addBeanPostProcessor(new MyBeanPostProcessor());
        
        // 向容器中注册MyInstantiationAwareBeanPostProcessor后处理器
        ((ConfigurableBeanFactory)bf).addBeanPostProcessor(
                new MyInstantiationAwareBeanPostProcessor());
        
        // 第一次从容器中获取car，将触发容器实例化该Bean，这将引发Bean生命周期方法的调用
        Car car1 = (Car)bf.getBean("car");
        car1.introduce();car1.setColor("red");
        
        // 第二次从容器中获取car，直接从缓存池中获取
        Car car2 = (Car)bf.getBean("car");
        
        // 查看car1和car2是否指向同一引用
        System.out.println("car1 == car2" + (car1 == car2));
        
        // 关闭容器
        ((XmlBeanFactory)bf).destroySingletons();
    }
    
    public static void main(String[] args) {
        LifeCycleInBeanFactory();
        /*
InstantiationAware BeanPostProcessor.postProcessBeforeInstantiation
call Car() constructor...
InstantiationAware BeanPostprocessor.postProcessAfterInstantiation
Instantiation AwareBeanPostProcessor.postProcessPropertyValues
call setBrand() to set propertity
调用BeanNameAware.setBeanName()
调用BeanFactoryAware.setBeanFactory()
调用BeanPostProcessor.postProcessBeforeInitialization()
调用InitializingBean.afterPropertiesSet()
调用init-method所指定的myInit()，将maxSpeed设置为240。
调用BeanPostProcessor.postProcessAfterInitialization()
brand:法拉利; color:black; maxSpeed:200
car1 == car2true
调用DisposableBean.destroy()
调用destroy-method所指定的myDestroy()
         */
        
        /**
         * 除非编写一个基于Spring之上的扩展插件或者子项目，否则用户完全额可以抛开以上4个Bean生命周期 的接口，
         * 使用更好的方案替代。
         * 
         * 但BeanPostProcessor接口却不一样，它不要求Bean去继承它，可以像插件一样注册到Spring容器中，
         * 为容器提供额外的功能。
         */
    }
}
