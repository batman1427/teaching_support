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
public class CommitController {
	private static final Log logger = LogFactory.getLog(CommitController.class);
	@Autowired
	UserService service;
	@Autowired
	GroupService groupService;
	@Autowired
	RecordService recordService;
	@Autowired
	ProjectService projectService;

	// 项目信息
	@RequestMapping("/main_project")
	public String main_project(HttpServletRequest request, HttpSession session,
			Model model) throws IOException {
		return "main_project";

	}

	// 文档提交
	@RequestMapping("/main_document")
	public String main_document(HttpServletResponse response, Model model,
			HttpSession session) throws IOException {
		return "main_document";

	}

	@RequestMapping("/upload")
	public String fileUpload(@RequestParam("file") CommonsMultipartFile file,
			@RequestParam("type") String type,
			@RequestParam("time") String iteration,
			@RequestParam("userdefined") String userdefined,
			HttpServletResponse response, Model model, HttpSession session)
			throws IOException {
		// 本地上传、gitlab上传、新增上传记录
		Date date = new Date();
		String temp_type = type;
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String temp_date = format.format(date);
		String temp_date2 = format2.format(date);
		if (type.equals("1")) {
			type = userdefined;
		}
		String path = "E:/" + session.getAttribute("studentid") + "/"
				+ temp_date + "_" + type + iteration + "_"
				+ file.getOriginalFilename();
		File newFile = new File(path);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);
		// 新增上传记录
		recordService.add((String) session.getAttribute("studentid"), type,
				iteration, temp_date2, file.getOriginalFilename());
		User user_info = service.getUserInfo((String) session
				.getAttribute("username"));
		groupService
				.uploadFiles(file, newFile, user_info, temp_type, iteration);
		response.setContentType("textml;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.flush();
		out.println("<script>");
		out.println("alert('success!');");
		out.println("history.back();");
		out.println("</script>");
		return "main_document";
	}

	// 项目结构
	@RequestMapping("/main_file")
	public String main_file(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, Model model)
			throws IOException {
		String path = request.getParameter("path");
		String branch = request.getParameter("branch");
		ArrayList<String> bl = projectService.branches((Integer) session
				.getAttribute("groupid"));
		String re = "";
		for (int i = 0; i < bl.size(); i++) {
			re = re + ";" + bl.get(i);
		}
		re = re.substring(1);
		session.setAttribute("branches", re);
		session.setAttribute("branch", branch);
		path = path.replace("*", "+");
		if (path.contains("/.")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.flush();
			out.println("<script>");
			out.println("alert('该文件无法访问!');");
			out.println("history.back();");
			out.println("</script>");
		} else if (!path.contains(".")) {
			ArrayList<String> al = projectService.struct(
					(Integer) session.getAttribute("groupid"), branch, path);
			String result = "";
			if (al.size() == 0) {
				session.setAttribute("dir", path);
				session.setAttribute("path", result);
			} else {
				for (int i = 0; i < al.size(); i++) {
					result = result + ";" + al.get(i);
				}
				result = result.substring(1);
				session.setAttribute("dir", path);
				session.setAttribute("path", result);
			}
		} else {
			session.setAttribute("dir", path);
			int len = path.length();
			String realpath = path.substring(0, len - 1);
			String content = projectService.FileInfo(
					(Integer) session.getAttribute("groupid"), branch, path);
			session.setAttribute("content", content);
		}
		return "main_file";

	}

	// 代码记录
	@RequestMapping("/main_codeRecord")
	public String main_codeRecord(HttpServletRequest request,
			HttpSession session, Model model) throws IOException {
		/*
		 * public ArrayList<Commit> code_record(int gid, String branch); private
		 * String commitAuthor; private String commitTime; private String hash;
		 * private String message; // 代码版本差异 gid为组号，hash为提交后对应的hash值 public
		 * CommitDiff versionDiff(int gid, String hash); private int
		 * changeFiles; private int add; private int delete; private
		 * ArrayList<String> fileNames; private ArrayList<String>
		 * modifyContents;
		 */
		String branch = request.getParameter("branch");
		String hash = request.getParameter("hash");
		ArrayList<String> bl = projectService.branches((Integer) session
				.getAttribute("groupid"));
		String re = "";
		for (int i = 0; i < bl.size(); i++) {
			re = re + ";" + bl.get(i);
		}
		re = re.substring(1);
		session.setAttribute("codebranches", re);
		session.setAttribute("codebranch", branch);
		if (hash.equals("0")) {
			ArrayList<Commit> al = projectService.code_record(
					(Integer) session.getAttribute("groupid"), branch);
			String result = "";
			for (int i = 0; i < al.size(); i++) {
				result = result + ";" + al.get(i).getMessage() + "&"
						+ al.get(i).getCommitAuthor() + "&"
						+ al.get(i).getCommitTime() + "&" + al.get(i).getHash();
			}
			result = result.substring(1);
			session.setAttribute("coderecord", result);
			session.setAttribute("hash", "0");
		} else {

			session.setAttribute("hash", hash);
			CommitDiff cd = projectService.versionDiff(
					(Integer) session.getAttribute("groupid"), hash);
			int changefiles = cd.getChangeFiles();
			int add = cd.getAdd();
			int delete = cd.getDelete();
			ArrayList<String> fileNames = cd.getFileNames();
			ArrayList<String> modifyContents = cd.getModifyContents();
			String detail = String.valueOf(changefiles) + ";"
					+ String.valueOf(add) + ";" + String.valueOf(delete);
			session.setAttribute("detail", detail);
			String filenames = "";
			String modify = "";
			for (int i = 0; i < fileNames.size(); i++) {
				filenames = filenames + ";" + fileNames.get(i);
			}
			for (int i = 0; i < modifyContents.size(); i++) {
				modify = modify + ";;;" + modifyContents.get(i);
			}
			filenames = filenames.substring(1);
			modify = modify.substring(3);
			session.setAttribute("filenames", filenames);
			session.setAttribute("modify", modify);
		}
		return "main_codeRecord";

	}

	// 文档记录
	@RequestMapping("/main_docuRecord")
	public String main_docuRecord(HttpServletRequest request,
			HttpSession session, Model model) throws IOException {
		if ((Integer) session.getAttribute("groupid") == 1) {
			recordService.createBarChart((String) session
					.getAttribute("studentid"));
			// 查看哪些人交了相同的文档
			String[] result = projectService.sameDocument(
					(String) session.getAttribute("studentid"),
					(Integer) session.getAttribute("groupid"));
			session.setAttribute("line0", result[0].substring(1));
			session.setAttribute("line1", result[1].substring(1));
			session.setAttribute("line2", result[2].substring(1));
			session.setAttribute("line3", result[3].substring(1));
			session.setAttribute("line4", result[4].substring(1));
			session.setAttribute("line5", result[5].substring(1));
			session.setAttribute("line6", result[6].substring(1));
			session.setAttribute("line7", result[7].substring(1));
			session.setAttribute("line8", result[8].substring(1));
			session.setAttribute("line9", result[9].substring(1));
			session.setAttribute("line10", result[10].substring(1));
			session.setAttribute("line11", result[11].substring(1));
			session.setAttribute("line12", result[12].substring(1));
			session.setAttribute("line13", result[13].substring(1));
			session.setAttribute("line14", result[14].substring(1));
		}
		return "main_docuRecord";

	}

	// 相关图表
	@RequestMapping("/main_graphs")
	public String main_graphs(HttpServletRequest request, HttpSession session,
			Model model) throws IOException {
		HashMap<String, String> object = projectService.commitRecord(1,
				"master");
		model.addAttribute("name", object.get("mx14_master"));
		model.addAttribute("data", object.get("mx14_master_data"));
		return "main_graphs";

	}

	// 相关图表测试
	@RequestMapping("/test")
	public String test(HttpServletRequest request, HttpSession session,
			Model model) throws IOException {
		HashMap<String, String> object = projectService.commitRecord(1,
				"master");
		model.addAttribute("name", object.get("mx14_master"));
		model.addAttribute("data", object.get("mx14_master_data"));
		System.out.println(1);
		System.out.println(object.get("mx14_master"));
		System.out.println(2);
		System.out.println(object.get("mx14_master_data"));
		System.out.println(3);
		System.out.println(object.get("total_master"));
		System.out.println(4);
		System.out.println(object.get("total_master_data"));
		return "test";

	}

	// 团队分析
	@RequestMapping("/main_teamAnalysis")
	public String main_teamAnalysis(HttpServletRequest request,
			HttpSession session, Model model) throws IOException {
		recordService.createBarChart_team((Integer) session
				.getAttribute("groupid"));
		recordService.createPieChart(
				(String) session.getAttribute("studentid"),
				(Integer) session.getAttribute("groupid"));
		// 团队代码提交分析
		return "main_teamAnalysis";

	}

	// 成绩查询
	@RequestMapping("/main_score")
	public String main_score(HttpServletRequest request, HttpSession session,
			Model model) throws IOException {
		recordService.createLineChart((String) session
				.getAttribute("studentid"));
		return "main_score";

	}

	// 交流讨论
	@RequestMapping("/main_discussion")
	public String main_discussion(HttpServletRequest request,
			HttpSession session, Model model) throws IOException {
		String show = request.getParameter("show");
		// System.out.println(show);
		String content = request.getParameter("content");
		if (show.equals("1")) {
			String result = projectService.getdiscussion();
			result = result.substring(1);
			session.setAttribute("discussion", result);
			session.setAttribute("show", show);
		} else {
			session.setAttribute("did", content);
			String result = projectService.getcommit(content);
			result = result.substring(1);
			session.setAttribute("commit", result);
			session.setAttribute("show", show);
		}
		return "main_discussion";

	}

	@RequestMapping("/create")
	public String create(@RequestParam("theme") String theme,
			HttpServletRequest request, HttpSession session, Model model)
			throws IOException {
		theme = new String(theme.getBytes("ISO-8859-1"), "utf-8");
		String sid = (String) session.getAttribute("studentid");
		Date date = new Date();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format2.format(date);
		projectService.adddiss(sid, time, theme);
		String result = projectService.getdiscussion();
		result = result.substring(1);
		session.setAttribute("discussion", result);
		return "main_discussion";

	}

	@RequestMapping("/commit")
	public String commit(@RequestParam("commit") String commit,
			HttpServletRequest request, HttpSession session, Model model)
			throws IOException {
		String content = new String(commit.getBytes("ISO-8859-1"), "utf-8");
		String sid = (String) session.getAttribute("studentid");
		String did = (String) session.getAttribute("did");
		Date date = new Date();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format2.format(date);
		projectService.addcommit(sid, time, content, did);
		String result = projectService.getcommit(did);
		result = result.substring(1);
		session.setAttribute("commit", result);
		return "main_discussion";

	}

	/*
	 * // 提交作业
	 * 
	 * @RequestMapping("/main") public String main(HttpServletRequest request,
	 * HttpSession session,Model model) throws IOException { return "main";
	 * 
	 * } // 个人信息
	 * 
	 * @RequestMapping("/person") public String person(HttpServletRequest
	 * request,HttpSession session, Model model) throws IOException { String
	 * uname = "用户名:" + session.getAttribute("username"); String password =
	 * "密码:" + session.getAttribute("password"); String name = "姓名:" +
	 * session.getAttribute("name"); String student = "学号:" +
	 * session.getAttribute("studentid"); String group = "分组:" +
	 * session.getAttribute("groupid"); String gitlab = "项目地址:" +
	 * session.getAttribute("gitlab"); model.addAttribute("username", uname);
	 * model.addAttribute("password", password); model.addAttribute("name",
	 * name); model.addAttribute("studentid", student);
	 * model.addAttribute("group", group); model.addAttribute("gitlab", gitlab);
	 * return "person";
	 * 
	 * } // 个人统计
	 * 
	 * @RequestMapping("/selfstat") public String selfstat(HttpServletRequest
	 * request, HttpSession session,Model model) throws IOException {
	 * recordService.createBarChart((String)session.getAttribute("studentid"));
	 * return "selfstat";
	 * 
	 * } // 团队统计
	 * 
	 * @RequestMapping("/teamstat") public String teamstat(HttpServletRequest
	 * request, HttpSession session,Model model) throws IOException {
	 * recordService.createBarChart_team((Integer)
	 * session.getAttribute("groupid"));
	 * recordService.createPieChart((String)session
	 * .getAttribute("studentid"),(Integer) session.getAttribute("groupid"));
	 * return "teamstat";
	 * 
	 * } // 成绩查询
	 * 
	 * @RequestMapping("/score") public String score(HttpServletRequest request,
	 * HttpSession session,Model model) throws IOException {
	 * recordService.createLineChart((String)session.getAttribute("studentid"));
	 * return "score";
	 * 
	 * } // 上传文件
	 * 
	 * @RequestMapping("/upload") public String fileUpload(@RequestParam("file")
	 * CommonsMultipartFile file,
	 * 
	 * @RequestParam("type") String type,@RequestParam("time") String
	 * iteration,@RequestParam("userdefined") String userdefined,
	 * HttpServletResponse response, Model model, HttpSession session) throws
	 * IOException { //本地上传、gitlab上传、新增上传记录 Date date=new Date(); String
	 * temp_type = type; DateFormat format=new
	 * SimpleDateFormat("yyyyMMddHHmmss"); DateFormat format2=new
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String
	 * temp_date=format.format(date); String temp_date2=format2.format(date);
	 * if(type.equals("1")){ type=userdefined; } String
	 * path="E:/"+session.getAttribute
	 * ("studentid")+"/"+temp_date+"_"+type+iteration
	 * +"_"+file.getOriginalFilename(); File newFile=new File(path);
	 * //通过CommonsMultipartFile的方法直接写文件（注意这个时候） file.transferTo(newFile);
	 * //新增上传记录 recordService.add((String)session.getAttribute("studentid"),
	 * type,iteration ,temp_date2, file.getOriginalFilename()); User user_info =
	 * service.getUserInfo((String)session.getAttribute("username"));
	 * groupService.uploadFiles(file, user_info,temp_type,iteration);
	 * response.setContentType("text/html;charset=utf-8"); PrintWriter out =
	 * response.getWriter(); out.flush(); out.println("<script>");
	 * out.println("alert('上传成功!');"); //out.println("history.back();");
	 * out.println("</script>"); return "main"; }
	 */

}
