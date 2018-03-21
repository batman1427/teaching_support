package service;

import java.util.ArrayList;
import java.util.HashMap;

import bean.Commit;
import bean.CommitDiff;

public interface ProjectService {
	// 最近提交、代码提交次数、分支数、每个人的提交次数
	public String recent_commit(int gid);

	public int commit_total(int gid);

	public int branch(int gid);

	public HashMap<String, Integer> commit_each(int gid);

	// 获取分支名
	public ArrayList<String> branches(int gid);

	// 项目结构 branch 分支名,path 相对路径
	public ArrayList<String> struct(int gid, String branch, String path);

	// 获取文件信息 branch 分支名,path 相对路径
	public String FileInfo(int gid, String branch, String path);

	// 代码记录 gid为组号，branch为分支名
	public ArrayList<Commit> code_record(int gid, String branch);

	// 代码版本差异 gid为组号，hash为提交后对应的hash值
	public CommitDiff versionDiff(int gid, String hash);

	// 语言分析
	public void lan_analysis(int gid);

	// 获取团队所有成员
	public String getmembers(int gid);

	// 查看哪些人交了相同的文档
	public String[] sameDocument(String sid, int gid);

	public String getdiscussion();

	public void adddiss(String sid, String time, String theme);

	public String getcommit(String content);

	public void addcommit(String sid, String time, String content, String did);
	//++
	public HashMap<String, String> commitRecord(int gid, String branch);
	
	//获取小组成员
	public ArrayList<String> getMember(int gid);

}
