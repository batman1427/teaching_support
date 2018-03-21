package dao;

public interface ProjectDao {

	public String getmembers(int gid);
	//查看哪些人交了相同的文档
	public String[] sameDocument(String sid,int gid);
	public String getdiscussion();
	public void adddiss(String sid, String time, String theme);
	public String getcommit(String content);
	public void addcommit(String sid, String time, String content, String did);
}
