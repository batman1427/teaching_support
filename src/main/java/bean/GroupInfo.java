package bean;

import java.util.List;

public class GroupInfo {
	private int groupId;
	private String gitAddress;
	private List<String> users;
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGitAddress() {
		return gitAddress;
	}
	public void setGitAddress(String gitAddress) {
		this.gitAddress = gitAddress;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
}
