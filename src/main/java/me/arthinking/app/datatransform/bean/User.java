package me.arthinking.app.datatransform.bean;

public class User {

	private long id;
	
	private String username;
	
	private String nickname;
	
	private Favorite favorite;
	
	public Favorite getFavorite() {
        return favorite;
    }
    public void setFavorite(Favorite favorite) {
        this.favorite = favorite;
    }
    public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}