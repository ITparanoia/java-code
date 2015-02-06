package me.arthinking.app.datatransform.vo;

import me.arthinking.app.datatransform.bean.Favorite;
import me.arthinking.app.datatransform.bean.User;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class UserVo {

	private static String[] formatterAttribute = 
		{"enName", "username", "id", "fav"};
	
	private String username;
	
	private String enName;
	
	private Favorite favorite;
	
	private long id;
	
	private UserVo transform(User user){
	    this.setEnName(user.getNickname());
	    this.setUsername(user.getUsername());
	    this.setId(user.getId());
	    this.setFavorite(user.getFavorite());
	    return this;
	}
	
	public Favorite getFavorite() {
        return favorite;
    }
	
    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }

    /**
	 * 把实体转换为Vo对象
	 * @param user
	 * @return
	 * UserVo
	 * @author Jason Peng
	 * @update date 2015年2月5日
	 */
	public static UserVo fromEntity(User user){
	    UserVo userVo = new UserVo();
	    return userVo.transform(user);
	}
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getEnName() {
		return username;
	}
	
	public void setEnName(String enName) {
		this.enName = enName;
	}


	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String toJsonString(){
		return this.toJsonString();
	}
	
	public String toJsonString(String[] attr){		
	    SimplePropertyPreFilter preFilter = 
				new SimplePropertyPreFilter(this.getClass(), attr);
		return JSON.toJSONString(preFilter, SerializerFeature.WriteMapNullValue);
	}
	
}