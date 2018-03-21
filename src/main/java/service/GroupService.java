package service;

import java.io.File;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import bean.User;

public interface GroupService {
	public String getGitURL(int group);

	public boolean uploadFiles(CommonsMultipartFile file, File newFile, User user_info, String type, String iteration);
}
