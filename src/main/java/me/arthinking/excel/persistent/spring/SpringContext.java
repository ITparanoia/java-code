package me.arthinking.excel.persistent.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class SpringContext {

    private static BeanFactory bf;
    
    /** 初始化Spring配置文件 **/
    static {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("classpath:me/arthinking/spring/ioc/beans.xml");
        BeanFactory bf = new XmlBeanFactory(res);
    }
    
    /**
     * 读取特定的bean
     * @param beanName
     * void
     * @author Jason Peng
     * @update date 2015年1月16日
     */
    public static <T> T getBean(String beanName, Class<T> beanClass){
        return (T)bf.getBean(beanName, beanClass);
    }
    
}