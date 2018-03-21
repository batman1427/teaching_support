package service;

import java.util.ArrayList;
import java.util.HashMap;

import bean.Commit;
import bean.CommitDiff;

public interface ProjectService {
	// ����ύ�������ύ��������֧����ÿ���˵��ύ����
	public String recent_commit(int gid);

	public int commit_total(int gid);

	public int branch(int gid);

	public HashMap<String, Integer> commit_each(int gid);

	// ��ȡ��֧��
	public ArrayList<String> branches(int gid);

	// ��Ŀ�ṹ branch ��֧��,path ���·��
	public ArrayList<String> struct(int gid, String branch, String path);

	// ��ȡ�ļ���Ϣ branch ��֧��,path ���·��
	public String FileInfo(int gid, String branch, String path);

	// �����¼ gidΪ��ţ�branchΪ��֧��
	public ArrayList<Commit> code_record(int gid, String branch);

	// ����汾���� gidΪ��ţ�hashΪ�ύ���Ӧ��hashֵ
	public CommitDiff versionDiff(int gid, String hash);

	// ���Է���
	public void lan_analysis(int gid);

	// ��ȡ�Ŷ����г�Ա
	public String getmembers(int gid);

	// �鿴��Щ�˽�����ͬ���ĵ�
	public String[] sameDocument(String sid, int gid);

	public String getdiscussion();

	public void adddiss(String sid, String time, String theme);

	public String getcommit(String content);

	public void addcommit(String sid, String time, String content, String did);
	//++
	public HashMap<String, String> commitRecord(int gid, String branch);
	
	//��ȡС���Ա
	public ArrayList<String> getMember(int gid);

}
