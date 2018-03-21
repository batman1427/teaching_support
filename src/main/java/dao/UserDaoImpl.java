package dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import bean.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean addUser(User u) {
		if(checkInfo(u.getUsername())||checkInfo(Integer.valueOf(u.getSid()))){
			return false;
		}
		int i = jdbcTemplate.update(utility.SqlQuery.ADD_USER,
				new Object[] { u.getUsername(), u.getPassword() ,u.getName(),u.getSid(),u.getGid()});
		if(i>0){
			return true;
		}
		return false;
	}
	
	private boolean checkInfo(String name){
		Object args[] = new Object[]{name};
		List<Map<String,Object>> result = jdbcTemplate.queryForList(utility.SqlQuery.FIND_USER, args);
		int size = result.size();
		if(size==1){
			return true;
		}
		return false;
	}
	
	private boolean checkInfo(int id){
		Object args[] = new Object[]{id};
		List<Map<String,Object>> result = jdbcTemplate.queryForList(utility.SqlQuery.CHECK_SID, args);
		int size = result.size();
		System.out.println(size);
		if(size==1){
			return true;
		}
		return false;
	}
    
	//验证用户信息
	public boolean checkUserInfo(User user) {
		Object args[] = new Object[]{user.getUsername()};
		List<Map<String,Object>> result = jdbcTemplate.queryForList(utility.SqlQuery.FIND_USER, args);
		int size = result.size();
		if(size==1){
			if(result.get(0).get("psw").equals(user.getPassword())){
				return true;
			}
		}
		return false;
	}

	public String findPassword(String username, int sid) {
		Object args[] = new Object[]{username,sid};
		List<Map<String,Object>> result = jdbcTemplate.queryForList(utility.SqlQuery.FIND_PSW, args);
		int size = result.size();
		if(size==1){
			return result.get(0).get("psw").toString();
		}
		return "";
	}

	public boolean modifyUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
    
	//根据用户名获取用户信息
	public User getUserInfo(String username) {
		Object args[] = new Object[]{username};
		List<Map<String,Object>> result = jdbcTemplate.queryForList(utility.SqlQuery.FIND_USER, args);
		User u = new User();
		u.setUsername(result.get(0).get("username")+"");
		u.setPassword(result.get(0).get("psw")+"");
		u.setName(result.get(0).get("name")+"");
		u.setSid(result.get(0).get("sid")+"");
		int gid = Integer.parseInt(result.get(0).get("gid")+"");
		u.setGid(gid);
		return u;
	}
}
