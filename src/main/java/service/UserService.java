package service;

import bean.User;

public interface UserService {
	public boolean checkUserInfo(User user);
	public String addUser(User user);
	public String findPassword(String username,int sid);
	public boolean modifyUserInfo(User user);
	public User getUserInfo(String username);
}
