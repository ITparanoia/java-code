package me.arthinking.app.datatransform.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

/**
 * JSON转换工具性能对比：http://blog.csdn.net/zhuzeyu5211/article/details/8969658
 * @author  Jason Peng
 * @create date 2015年1月29日
 */
public class UserWrap {

    private static Logger logger = Logger.getLogger("access");
    
    private User user;
    
    private List list;
    
    public UserWrap(User user){
        this.user = user;
    }
    
    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    /**
     * 调用JSONObject.toJSON(),实体对象必定会转换为JSONObject
     * 集合则转换为JSONArray
     * @return
     * JSONObject
     * @author Jason Peng
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @update date 2015年1月29日
     */
    public String toJsonObject() throws IllegalAccessException, InvocationTargetException{
        
        String str = JSONObject.fromObject(user).toString();
        return str;
        /*
        if(obj instanceof JSONObject){
            return (String)obj; 
        } else {
            logger.warn("UserWrap.toJsonObject(): 转换实体到JSON对象出错，请确保你的实体不是集合，数组，JSON，或者枚举类型");
            return null;
        }
        */
        // 添加默认属性空属性，根据白名单过滤属性        
    }
    
    public static void main(String[] args) {
        User user = new User();
        UserWrap wrap = new UserWrap(user);
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        wrap.setList(list);
        try {
            System.out.println(wrap.toJsonObject());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}