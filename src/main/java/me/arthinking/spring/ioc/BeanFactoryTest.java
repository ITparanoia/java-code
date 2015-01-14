package me.arthinking.spring.ioc;

import me.arthinking.spring.ioc.bean.User;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class BeanFactoryTest {

    // 获取默认的Log
    // private static Log logger = LogFactory.getLog(BeanFactoryTest.class);
    // private static Log logger = LogFactory.getLog("access");
    private static Logger logger = Logger.getLogger("access");
    
    public static void main(String[] args) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("classpath:me/arthinking/spring/ioc/beans.xml");
        // XmlBeanFactory通过Resource装载Spring配置信息并启动IoC容器，
        // 然后就可以通过BeanFactory#getBean(beanName)方法从IoC容器获取Bean了。
        BeanFactory bf = new XmlBeanFactory(res);
        logger.info("init BeanFactory.");
        // 通过BeanFactory启动IoC容器时，并不会初始化配置文件中定义的Bean，
        // 初始化动作发生在第一个调用时。
        // 对于单实例的Bean来说，BeanFactory会缓存Bean实例，所以第二次使用getBean()
        // 获取Bean时将直接从IoC容器的缓存中获取Bean实例
        User user = bf.getBean("user", User.class);
        logger.info("User bean is ready for use! " + user.getUsername());
    }
}