package controller;

import java.awt.Font;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jcraft.jsch.Logger;

import bean.Commit;
import bean.CommitDiff;
import bean.User;
import service.GroupService;
import service.ProjectService;
import service.RecordService;
import service.UserService;

@Controller
public class AdminController {
	private static final Log logger = LogFactory.getLog(AdminController.class);
	@Autowired
	UserService service;
	@Autowired
	GroupService groupService;
	@Autowired
	RecordService recordService;
	@Autowired
	ProjectService projectService;

	
	//分组信息
	@RequestMapping("/admin_group")
	public String admin_group(HttpServletRequest request, HttpSession session,Model model)
			throws IOException {
		return "admin_group";

	}
	//项目结构
	@RequestMapping("/admin_file")
	public String admin_file(HttpServletRequest request,HttpServletResponse response, HttpSession session,Model model)
			throws IOException {
		String path=request.getParameter("path");
		String branch=request.getParameter("branch");
		int gid=Integer.valueOf(request.getParameter("gid"));
		session.setAttribute("groupid",gid );
		ArrayList<String>  bl=projectService.branches((Integer)session.getAttribute("groupid"));
		String re="";
		for(int i=0;i<bl.size();i++){
			re=re+";"+bl.get(i);
		}
		re=re.substring(1);
		session.setAttribute("branches", re);
		session.setAttribute("branch", branch);
		path=path.replace("*", "+");
		if(path.contains("/.")){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.flush();
			   out.println("<script>");
			   out.println("alert('该文件无法访问!');");
			   out.println("history.back();");
			   out.println("</script>");
		}else if(!path.contains(".")){
		ArrayList<String> al=projectService.struct((Integer)session.getAttribute("groupid"), branch, path);
		String result="";
		for(int i=0;i<al.size();i++){
			result=result+";"+al.get(i);
		}
		result=result.substring(1);
		session.setAttribute("dir", path);
		session.setAttribute("path", result);
			}else{
				session.setAttribute("dir", path);
				int len=path.length();
				String realpath=path.substring(0,len-1);
				String content=projectService.FileInfo((Integer)session.getAttribute("groupid"), branch, path);
				session.setAttribute("content",content);
			}
			return "admin_file";

		}
	
	//代码记录
	@RequestMapping("/admin_codeRecord")
	public String admin_codeRecord(HttpServletRequest request, HttpSession session,Model model)
				throws IOException {
			String branch=request.getParameter("branch");
			String hash=request.getParameter("hash");
			int gid=Integer.valueOf(request.getParameter("gid"));
			session.setAttribute("groupid",gid );
			ArrayList<String>  bl=projectService.branches((Integer)session.getAttribute("groupid"));
			String re="";
			for(int i=0;i<bl.size();i++){
				re=re+";"+bl.get(i);
			}
			re=re.substring(1);
			session.setAttribute("codebranches", re);
			session.setAttribute("codebranch", branch);
			if(hash.equals("0")){
			ArrayList<Commit> al=projectService.code_record((Integer)session.getAttribute("groupid"), branch);
			String result="";
			for(int i=0;i<al.size();i++){
				result=result+";"+al.get(i).getMessage()+"&"+al.get(i).getCommitAuthor()+"&"+al.get(i).getCommitTime()+"&"+al.get(i).getHash();
			}
			result=result.substring(1);
			session.setAttribute("coderecord",result);
			session.setAttribute("hash", "0");
			}else{
				session.setAttribute("hash", hash);
				CommitDiff cd=projectService.versionDiff((Integer)session.getAttribute("groupid"), hash);
				int changefiles=cd.getChangeFiles();
				int add=cd.getAdd();
				int delete=cd.getDelete();
				ArrayList<String> fileNames=cd.getFileNames();
				ArrayList<String> modifyContents=cd.getModifyContents();
				String detail=String.valueOf(changefiles)+";"+String.valueOf(add)+";"+String.valueOf(delete);
				session.setAttribute("detail", detail);
				String filenames="";
				String modify="";
				for(int i=0;i<fileNames.size();i++){
					filenames=filenames+";"+fileNames.get(i);
				}
				for(int i=0;i<modifyContents.size();i++){
					modify=modify+";;;"+modifyContents.get(i);
				}
				filenames=filenames.substring(1);
				modify=modify.substring(3);
				session.setAttribute("filenames", filenames);
				session.setAttribute("modify", modify);
			}
			return "admin_codeRecord";

		}
	
	//相关图表
	@RequestMapping("/admin_graphs")
	public String admin_graphs(HttpServletRequest request, HttpSession session,Model model)
				throws IOException {
			int gid=Integer.valueOf(request.getParameter("gid"));
			session.setAttribute("groupid",gid );
			HashMap<String,String> object=projectService.commitRecord((Integer)session.getAttribute("groupid"), "master");
			model.addAttribute("name",object.get("total_master"));
			model.addAttribute("data",object.get("total_master_data"));
			return "admin_graphs";

		}
	
	//团队分析
	@RequestMapping("/admin_teamAnalysis")
	public String admin_teamAnalysis(HttpServletRequest request, HttpSession session,Model model)
				throws IOException {
		 	//团队代码提交分析
			int gid=Integer.valueOf(request.getParameter("gid"));
			session.setAttribute("groupid",gid );
			return "admin_teamAnalysis";

		}
	
	//交流讨论
	//交流讨论
		@RequestMapping("/admin_discussion")
		public String admin_discussion(HttpServletRequest request, HttpSession session,Model model)
				throws IOException {
			String show=request.getParameter("show");
			//System.out.println(show);
			String content=request.getParameter("content");
			if(show.equals("1")){
				String result=projectService.getdiscussion();
				result=result.substring(1);
				session.setAttribute("discussion", result);
				session.setAttribute("show", show);
			}else{
				session.setAttribute("did", content);
				String result=projectService.getcommit(content);
				result=result.substring(1);
				session.setAttribute("commit", result);
				session.setAttribute("show", show);
			}
			return "admin_discussion";

		}
		@RequestMapping("/admincreate")
		public String admincreate(@RequestParam("theme") String theme,HttpServletRequest request, HttpSession session,Model model)
				throws IOException {
			   theme=new String(theme.getBytes("ISO-8859-1"),"utf-8");
			   String sid=(String)session.getAttribute("studentid");
			   Date date=new Date();
		       DateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		       String time=format2.format(date);
			   projectService.adddiss(sid,time,theme);
			   String result=projectService.getdiscussion();
			   result=result.substring(1);
			   session.setAttribute("discussion", result);
			   return "admin_discussion";

		}
		@RequestMapping("/admincommit")
		public String admincommit(@RequestParam("commit") String commit,HttpServletRequest request, HttpSession session,Model model)
				throws IOException {
			   String content=new String(commit.getBytes("ISO-8859-1"),"utf-8");
			   String sid=(String)session.getAttribute("studentid");
			   String did=(String)session.getAttribute("did");
			   Date date=new Date();
		       DateFormat format2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		       String time=format2.format(date);
			   projectService.addcommit(sid,time,content,did);
			   String result=projectService.getcommit(did);
			   result=result.substring(1);
			   session.setAttribute("commit", result);
			   return "admin_discussion";

		}
}