package me.arthinking.spring.ioc.processpr;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class MyInstantiationAwareBeanPostProcessor extends
        InstantiationAwareBeanPostProcessorAdapter {

    // 接口方法：在实例化Bean前进行调用
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass,
            String beanName) throws BeansException {
        // 仅对容器中car Bean进行处理
        if("car".equals(beanName)){
            System.out.println("InstantiationAware BeanPostProcessor.postProcessBeforeInstantiation");
        }
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    // 接口方法：在实例化Bean后调用
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName)
            throws BeansException {
        // 仅对容器中car Bean进行处理
        if("car".equals(beanName)){
            System.out.println("InstantiationAware BeanPostprocessor.postProcessAfterInstantiation");
        }
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    // 接口方法：在设置某个属性时调用
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs,
            PropertyDescriptor[] pds, Object bean, String beanName)
            throws BeansException {
        // 仅对容器中car Bean进行处理，还可以通过pdst入参进行过滤
        if("car".equals(beanName)){
            System.out.println("Instantiation AwareBeanPostProcessor.postProcessPropertyValues");
        }
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }
}