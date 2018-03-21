package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import service.GroupService;
import service.ProjectService;
import service.UserService;
import bean.User;

@org.springframework.stereotype.Controller
public class UserController {
	private static final Log logger = LogFactory.getLog(UserController.class);
	@Autowired
	private UserService service;
	@Autowired
	GroupService groupService;
	@Autowired
	ProjectService projectService;

	@RequestMapping(value = {"","sign_in"})
	public String signIn(Model model) {
		logger.info("sign in called");
		model.addAttribute("user", new User());
		return "login";
	}

	// 登录
	@RequestMapping("/login")
	public String login(User user, HttpServletResponse response,
			HttpServletRequest request,HttpSession session, Model model) throws IOException {
		//验证用户信息
		String temp_username=new String(user.getUsername().getBytes("ISO-8859-1"),"utf-8");
		user.setUsername(temp_username);
		boolean result = service.checkUserInfo(user);
		//用户不存在
		if (result == false) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.flush();
		    out.println("<script>");
		    out.println("alert('用户名或密码错误!');");
		    out.println("history.back();");
		    out.println("</script>");
			return null;
		} else {
			//用户存在
			//获取用户信息
			if(!user.getUsername().equals("admin")){
			User user_info = service.getUserInfo(user.getUsername());
			//获取gitlab下载地址
			String address = groupService.getGitURL(user_info.getGid());
			session.setAttribute("username", user_info.getUsername());
			session.setAttribute("password", user_info.getPassword());
			session.setAttribute("name", user_info.getName());
			session.setAttribute("studentid", user_info.getSid());
			session.setAttribute("groupid", user_info.getGid());
			session.setAttribute("gitlab", address);
			
			
			//总提交次数	最近一次代码提交	项目分支数        	团队成员
			int commit_total=projectService.commit_total((Integer)session.getAttribute("groupid"));
			String recent_commit=projectService.recent_commit((Integer)session.getAttribute("groupid"));
			int branch=projectService.branch((Integer)session.getAttribute("groupid"));
			session.setAttribute("commit_total", commit_total);
			session.setAttribute("recent_commit", recent_commit);
			session.setAttribute("br", branch);
			String getmembers=projectService.getmembers((Integer)session.getAttribute("groupid"));
			
			session.setAttribute("members", getmembers);
			return "main_project";}
			else{
				User user_info = service.getUserInfo(user.getUsername());
				session.setAttribute("username", user_info.getUsername());
				session.setAttribute("password", user_info.getPassword());
				session.setAttribute("name", user_info.getName());
				session.setAttribute("studentid", user_info.getSid());
				session.setAttribute("groupid", user_info.getGid());
			return "admin_group";
			}
		}
	}

	// 登录页面转注册
	@RequestMapping("/register-turn")
	public String register_turn(Model model) {
		model.addAttribute("user_register", new User());
		return "register";

	}
    //注册
	@RequestMapping("/register")
	public String register(User user_register, HttpServletResponse response, Model model)
			throws IOException {
		String temp_name=new String(user_register.getName().getBytes("ISO-8859-1"),"utf-8"); 
		String temp_username=new String(user_register.getUsername().getBytes("ISO-8859-1"),"utf-8");
		user_register.setName(temp_name);
		user_register.setUsername(temp_username);
		String result = service.addUser(user_register);
		if (result.equals("username_sid_failed")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.flush();
		    out.println("<script>");
		    out.println("alert('用户名或学号已被注册,请重新注册!');");
		    out.println("history.back();");
		    out.println("</script>");
			return null;
		} else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.flush();
		    out.println("<script>");
		    out.println("alert('注册成功,请登录!');");
		    out.println("</script>");
		    model.addAttribute("user", new User());
			return "login";
		}
	}

	@RequestMapping("/login-turn")
	public String login_turn(HttpServletResponse response) throws IOException {
		return "login";
	}
}
