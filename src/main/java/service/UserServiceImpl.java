package service;

import java.io.File;
import java.io.IOException;

import git.GitAdmin;

import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GroupDao;
import dao.UserDao;
import bean.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private GroupDao groupDao;
    
	//验证用户信息
	public boolean checkUserInfo(User user) {
		return userDao.checkUserInfo(user);
	}
    //注册新用户
	public String addUser(User user) {
		boolean isGroupExist = groupDao.isGroupExist(user.getGid());
		if (!isGroupExist) {
			if (!groupDao.createGroup(user.getGid(), "")) {
				return "创建组失败";
			}
		}
		if (!userDao.addUser(user)) {
			return "username_sid_failed";
		}
		if (!GitAdmin.createUser(user.getSid())) {
			return "gitlab_user_failed";
		}
		if (!isGroupExist) {
			int[] array = GitAdmin.createGroup(user.getSid(), user.getGid());
			if (array==null) {
				return "gitlab_group_failed";
			}
			String address = utility.Server.GIT_ADDRESS
					+ "/" + utility.Server.PROJECT_PREFIX + user.getGid() + "/"
					+ utility.Server.PROJECT_PREFIX + user.getGid() + ".git";
			groupDao.updateAddress(user.getGid(), address);
			groupDao.updateIds(array[0],array[1], user.getGid());

				
			GitAdmin.createRepository(user, address);
		}
		else{
			GitAdmin.addGroupMember(utility.Server.PROJECT_PREFIX+user.getGid(),user.getSid());
		}
		return "ok";
	}

	public String findPassword(String username, int sid) {
		return userDao.findPassword(username, sid);

	}

	public boolean modifyUserInfo(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
    //登录成功，根据用户名获取用户信息
	public User getUserInfo(String username) {
		return userDao.getUserInfo(username);
	}

}
