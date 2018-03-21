package dao;

import bean.User;

public interface UserDao {
	public boolean addUser(User user);
	public boolean checkUserInfo(User user);
	public String findPassword(String username,int sid);
	public boolean modifyUser(User user);
	public User getUserInfo(String username);
}
