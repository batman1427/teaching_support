package service;

import git.GitAdmin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import controller.CommitController;
import bean.User;
import dao.GroupDao;

@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupDao groupDao;
	private static final Log logger = LogFactory.getLog(GroupServiceImpl.class);

	public String getGitURL(int group) {
		return groupDao.getURL(group);
	}

	// �ϴ��ĵ�
	public boolean uploadFiles(CommonsMultipartFile file, File temp,
			User user_info, String type, String iteration) {
		int iteration_number = 0;
		for (int i = 0; i < utility.Server.iterations.length; i++) {
			if (iteration.equals(utility.Server.iterations[i]))
				iteration_number = i + 1;
		}
		String path = "iteration_" + iteration_number;
		logger.info(path);

		String tailPath = file.getOriginalFilename().substring(
				file.getOriginalFilename().indexOf("."));
		logger.info(tailPath);
		String finalPath = "";

		// �ĵ����Ͳ�������ʱ��ֻ����һ���ĵ����������Ϳ��Դ��ڶ��
		if (!type.equals("0") && !(type.equals("1"))) {
			finalPath = utility.Server.REPOSITORY_PREFIX + user_info.getGid()
					+ "/" + path + "/" + type + tailPath;
		} else {
			finalPath = utility.Server.REPOSITORY_PREFIX + user_info.getGid()
					+ "/" + path + "/" + type + "_"
					+ file.getOriginalFilename();
		}
		logger.info(finalPath);
		File newFile = new File(finalPath);
		// ͨ��CommonsMultipartFile�ķ���ֱ��д�ļ���ע�����ʱ��

		int length = 2097152;
		FileInputStream in;
		FileOutputStream out;
		try {
			in = new FileInputStream(temp);
			out = new FileOutputStream(newFile);
			FileChannel inC = in.getChannel();
			FileChannel outC = out.getChannel();
			ByteBuffer b = null;
			while (true) {
				if (inC.position() == inC.size()) {
					inC.close();
					outC.close();
					break;
				}
				if ((inC.size() - inC.position()) < length) {
					length = (int) (inC.size() - inC.position());
				} else
					length = 2097152;
				b = ByteBuffer.allocateDirect(length);
				inC.read(b);
				b.flip();
				outC.write(b);
				outC.force(false);
			}
			// file.transferTo(newFile);
			// ���õ�����api�ϴ��ĵ���GitLab������
			GitAdmin.upload(user_info);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoFilepatternException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}
