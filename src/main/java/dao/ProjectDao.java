package dao;

public interface ProjectDao {

	public String getmembers(int gid);
	//�鿴��Щ�˽�����ͬ���ĵ�
	public String[] sameDocument(String sid,int gid);
	public String getdiscussion();
	public void adddiss(String sid, String time, String theme);
	public String getcommit(String content);
	public void addcommit(String sid, String time, String content, String did);
}
