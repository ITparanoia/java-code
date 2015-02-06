package me.arthinking.app.datatransform.json;

import java.util.HashMap;
import java.util.Map;

import me.arthinking.app.datatransform.bean.Favorite;
import me.arthinking.app.datatransform.bean.User;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

/**
 * 实体属性过滤配置
 * @author  Jason Peng
 * @create date 2015年2月5日
 */
public class JsonConverter {
    
    private static Map<String,String[]> config = new HashMap<String, String[]>();
    
    static {
        config.put("activity", new String[]{"activityId","cover","appCover","url","title","description",
                "seq","isTop","isHaitao","isMall","createAt",
                "createById","updateAt","updateById","cover80x40"});
    }
    
    
    // User 过滤配置
    static String[] userPropertities = new String[]{"username", "favorite"};
    static SerializeFilter[] userFilters = null; 
   
    // Favorite 过滤配置
    static String[] favoritePropertities = new String[]{"sportName", "user"};
    static SerializeFilter[] favoriteFilters = null;
    
    static {
        // 创建过滤规则
        SimplePropertyPreFilter userFilter = 
                new SimplePropertyPreFilter(User.class, userPropertities);
        
        SimplePropertyPreFilter favoriteFilter = 
                new SimplePropertyPreFilter(Favorite.class, favoritePropertities);
        
        // 根据实际过滤情况，组装过滤器
        userFilters = new SerializeFilter[]{userFilter, favoriteFilter};
        favoriteFilters = new SerializeFilter[]{userFilter, favoriteFilter};
        
    }
    
    /**
     * 根据类型获取对应的过滤配置数组
     * @param clazz
     * @return
     * SerializeFilter[]
     * @author Jason Peng
     * @update date 2015年2月5日
     */
    public static <T> SerializeFilter[] getFilter(T t){
        if(t instanceof User)
            return userFilters;
        else if(t instanceof Favorite)
            return favoriteFilters;
        return null;
    }
    
    /**
     * 生成JSON字符串
     * @param t
     * @return
     * String
     * @author Jason Peng
     * @update date 2015年2月5日
     */
    public static <T> String toJSONString(T t){
        SerializeFilter[] serializeFilters = getFilter(t);
        String str = JSON.toJSONString(t, serializeFilters, 
                SerializerFeature.WriteMapNullValue);
                // SerializerFeature.DisableCircularReferenceDetect);
        return str;
    }
}