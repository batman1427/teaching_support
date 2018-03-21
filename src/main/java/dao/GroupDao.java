package dao;

public interface GroupDao {
	public boolean isGroupExist(int gid);
	public boolean createGroup(int id,String address);
	public boolean updateAddress(int gid,String address);
	public String getURL(int group);
	public boolean updateIds(int gitlab_id,int pid,int gid);
	public int getProjectId(int gid);
	//++
	public int getGroupId(int gid);
}