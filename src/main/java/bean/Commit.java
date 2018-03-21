package bean;

import java.util.Date;

public class Commit {
	private String commitAuthor;
	private String commitTime;
	private String hash;
	private String message;
	public String getCommitAuthor() {
		return commitAuthor;
	}
	public void setCommitAuthor(String commitAuthor) {
		this.commitAuthor = commitAuthor;
	}
	public String getCommitTime() {
		return commitTime;
	}
	public void setCommitTime(String commitTime) {
		this.commitTime = commitTime;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
