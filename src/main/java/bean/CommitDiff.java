package bean;

import java.util.ArrayList;

public class CommitDiff {
	private int changeFiles;
	private int add;
	private int delete;
	private ArrayList<String> fileNames;
	private ArrayList<String> modifyContents;
	public int getChangeFiles() {
		return changeFiles;
	}
	public void setChangeFiles(int changeFiles) {
		this.changeFiles = changeFiles;
	}
	public int getAdd() {
		return add;
	}
	public void setAdd(int add) {
		this.add = add;
	}
	public int getDelete() {
		return delete;
	}
	public void setDelete(int delete) {
		this.delete = delete;
	}
	public ArrayList<String> getFileNames() {
		return fileNames;
	}
	public void setFileNames(ArrayList<String> fileNames) {
		this.fileNames = fileNames;
	}
	public ArrayList<String> getModifyContents() {
		return modifyContents;
	}
	public void setModifyContents(ArrayList<String> modifyContents) {
		this.modifyContents = modifyContents;
	}
}
