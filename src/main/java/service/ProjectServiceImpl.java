package service;

import git.GitAdmin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Commit;
import bean.CommitDiff;
import dao.GroupDao;
import dao.ProjectDao;


@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	GroupDao groupDao;
	@Autowired
	ProjectDao projectDao;

	public String recent_commit(int gid) {
		int gitlab_pid = groupDao.getProjectId(gid);
		return GitAdmin.recentCommit(gitlab_pid);
	}

	public int commit_total(int gid) {
		int gitlab_pid = groupDao.getProjectId(gid);
		return GitAdmin.commit_total(gitlab_pid);
	}

	public int branch(int gid) {
		int gitlab_pid = groupDao.getProjectId(gid);
		return GitAdmin.branch_total(gitlab_pid);
	}

	public HashMap<String, Integer> commit_self(int gid,String branch) {
		int gitlab_pid = groupDao.getProjectId(gid);
		return GitAdmin.commit_self(gitlab_pid,branch);
	}

	public ArrayList<String> branches(int gid) {
		int gitlab_pid = groupDao.getProjectId(gid);
		return GitAdmin.getBranches(gitlab_pid);
	}

	public ArrayList<String> struct(int gid, String branch, String path) {
		int gitlab_pid = groupDao.getProjectId(gid);
		return GitAdmin.getStruct(gitlab_pid,branch,path);
	}

	public String FileInfo(int gid, String branch, String path) {
		int gitlab_pid = groupDao.getProjectId(gid);
		return GitAdmin.getFileInfo(gitlab_pid,branch,path);
	}

	public void code_record(int gid) {
		// TODO Auto-generated method stub

	}

	public void lan_analysis(int gid) {
		// TODO Auto-generated method stub

	}

	public HashMap<String, Integer> commit_self(int gid) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Commit> code_record(int gid, String branch) {
		int gitlab_pid = groupDao.getProjectId(gid);
		return GitAdmin.getCommits(gitlab_pid,branch);
	}
	
	public CommitDiff versionDiff(int gid, String hash) {
		int gitlab_pid = groupDao.getProjectId(gid);
		return GitAdmin.getVersionDiff(gitlab_pid,hash);
	}

	public String getmembers(int gid) {
		// TODO Auto-generated method stub
		String result=projectDao.getmembers(gid);
		return result;
	}

	public HashMap<String, Integer> commit_each(int gid) {
		// TODO Auto-generated method stub
		return null;
	}
    
	//查看哪些人交了相同的文档
	public String[] sameDocument(String sid,int gid) {
		// TODO Auto-generated method stub
		String[] result=projectDao.sameDocument(sid,gid);
		result[0]=result[0]+"提交了迭代一的用例文档;";
		result[1]=result[1]+"提交了迭代一的需求规格说明文档;"   ;
		result[2]=result[2]+"提交了迭代一的体系结构描述文档;";
		result[3]=result[3]+"提交了迭代一的详细设计描述文档;";
		result[4]=result[4]+"提交了迭代一的测试用例;";
		result[5]=result[5]+"提交了迭代二的用例文档;";
		result[6]=result[6]+"提交了迭代二的需求规格说明文档;";
		result[7]=result[7]+"提交了迭代二的体系结构描述文档;";
		result[8]=result[8]+"提交了迭代二的详细设计描述文档;";
		result[9]=result[9]+"提交了迭代二的测试用例;";
		result[10]=result[10]+"提交了迭代三的用例文档;";
		result[11]=result[11]+"提交了迭代三的需求规格说明文档;";
		result[12]=result[12]+"提交了迭代三的体系结构描述文档;";
		result[13]=result[13]+"提交了迭代三的详细设计描述文档;";
		result[14]=result[14]+"提交了迭代三的测试用例;";
		return result;
	}
	//交流讨论
	public String getdiscussion(){
		String result=projectDao.getdiscussion();
		return result;
		
	}

	public void adddiss(String sid, String time, String theme) {
		// TODO Auto-generated method stub
		projectDao.adddiss(sid,time,theme);
	}

	public String getcommit(String content) {
		// TODO Auto-generated method stub
		String result=projectDao.getcommit(content);
		return result;
	}

	public void addcommit(String sid, String time, String content, String did) {
		// TODO Auto-generated method stub
		projectDao.addcommit(sid,time,content,did);
	}
	//++
	public HashMap<String, String> commitRecord(int gid, String branch) {
		int gitlab_pid = groupDao.getProjectId(gid);
		int gitlab_gid = groupDao.getGroupId(gid);
		try {
			return GitAdmin.getCommitRecord(gitlab_pid,gitlab_gid,branch);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<String> getMember(int gid) {
		int gitlab_gid = groupDao.getGroupId(gid);
		return GitAdmin.getMember(gitlab_gid);
	}


}