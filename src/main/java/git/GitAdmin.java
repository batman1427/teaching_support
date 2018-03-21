package git;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabAccessLevel;
import org.gitlab.api.models.GitlabBranch;
import org.gitlab.api.models.GitlabCommit;
import org.gitlab.api.models.GitlabCommitDiff;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabGroupMember;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabRepositoryTree;
import org.gitlab.api.models.GitlabSession;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabAccessLevel;
import org.gitlab.api.models.GitlabBranch;
import org.gitlab.api.models.GitlabCommit;
import org.gitlab.api.models.GitlabCommitDiff;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabRepositoryTree;
import org.gitlab.api.models.GitlabSession;

import bean.Commit;
import bean.CommitDiff;
import bean.User;

public class GitAdmin {
	private static GitlabAPI api = GitlabAPI.connect(
			utility.Server.GIT_ADDRESS, utility.Server.ADMIN_TOKEN);
	private static SimpleDateFormat format = new SimpleDateFormat(
			"MMMMM dd, yyyy", Locale.US);


	public static boolean createUser(String id) {
		try {
			api.createUser(id + utility.Server.EMAIL_SUFFIX, id + "", id + "",
					id + "", "", "", "", "", 10, "", "", "", false, true, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static int[] createGroup(String sid, int gid) {
		GitlabAPI memberApi = getConnection(sid);
		if (memberApi != null) {
			try {
				// 创建小组
				GitlabGroup group = memberApi.createGroup(
						utility.Server.PROJECT_PREFIX + gid,
						utility.Server.PROJECT_PREFIX + gid);
				// 创建小组项目
				GitlabProject project = memberApi.createProject(
						utility.Server.PROJECT_PREFIX + gid, group.getId(), "",
						true, true, true, true, true, true, 0, "");
				return new int[] { group.getId(), project.getId() };
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	public static boolean addGroupMember(String path, String sid) {
		GitlabAPI memberApi = getConnection(sid);
		if (memberApi != null) {
			try {
				int gid = api.getGroup(path).getId();
				api.addGroupMember(gid, memberApi.getUser().getId(),
						GitlabAccessLevel.Developer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	private static GitlabAPI getConnection(String sid) {
		GitlabSession api;
		GitlabAPI gitlabAPI = null;
		try {
			api = GitlabAPI.connect(utility.Server.GIT_ADDRESS, sid + "", sid
					+ "");
			gitlabAPI = GitlabAPI.connect(utility.Server.GIT_ADDRESS,
					api.getPrivateToken());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gitlabAPI;
	}

	public static boolean createRepository(User user, String address) {
		Git git;
		try {
			git = Git
					.cloneRepository()
					.setURI(address)
					.setDirectory(
							new File(utility.Server.REPOSITORY_PREFIX
									+ user.getGid()))
					.setCredentialsProvider(
							new UsernamePasswordCredentialsProvider(user
									.getSid() + "", user.getSid() + "")).call();
			String message = "initial";
			upload(git, user, message);
			CreateBranchCommand command = git.branchCreate();
			command.setName("documents");
			command.call();
			message = "create branch";
			createNewFolder(user);
			upload(git, user, message);
		} catch (InvalidRemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	private static void createNewFolder(User user) {
		new File(utility.Server.REPOSITORY_PREFIX + user.getGid()
				+ "/iteration_1").mkdirs();
		new File(utility.Server.REPOSITORY_PREFIX + user.getGid()
				+ "/iteration_2").mkdirs();
		new File(utility.Server.REPOSITORY_PREFIX + user.getGid()
				+ "/iteration_3").mkdirs();
	}

	private static void upload(Git git, User user, String message)
			throws NoFilepatternException, GitAPIException {
		AddCommand addCommand = git.add();
		addCommand.addFilepattern(".");
		addCommand.call();
		CommitCommand commitCommand = git.commit();
		commitCommand.setMessage(message);
		commitCommand.setAllowEmpty(true);
		commitCommand.call();
		PushCommand pushCommand = git.push();
		CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(
				user.getSid() + "", user.getSid() + "");
		pushCommand.setCredentialsProvider(credentialsProvider);
		pushCommand.setForce(true).setPushAll();
		pushCommand.call();
	}

	public static void upload(User user_info) throws NoFilepatternException,
			GitAPIException, IOException {
		Git git = Git.open(new File(utility.Server.REPOSITORY_PREFIX
				+ user_info.getGid()));
		String message = "documents commit";
		CheckoutCommand checkoutCommand = git.checkout();
		checkoutCommand.setName("documents");
		checkoutCommand.call();
		upload(git, user_info, message);
	}

	public static int commit_total(int pid) {
		List<GitlabCommit> commits;
		try {
			commits = api.getAllCommits(pid);
			return commits.size();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static int branch_total(int gitlab_pid) {
		List<GitlabBranch> branchs;
		try {
			branchs = api.getBranches(gitlab_pid);
			return branchs.size();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static HashMap<String, Integer> commit_self(int gitlab_pid,
			String branch) {
		List<GitlabCommit> commits;
		try {
			commits = api.getAllCommits(gitlab_pid, branch);
			HashMap<String, Integer> graph = new HashMap<String, Integer>();
			for (GitlabCommit gitlabCommit : commits) {
				String user = gitlabCommit.getAuthorName();
				if (graph.containsKey(user)) {
					graph.put(user, graph.get(user) + 1);
				} else {
					graph.put(user, 1);
				}
			}
			return graph;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String recentCommit(int gitlab_pid) {
		List<GitlabCommit> commits;
		try {
			commits = api.getLastCommits(gitlab_pid);
			return commits.get(0).getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public static ArrayList<String> getStruct(int gitlab_pid, String branch,
			String path) {
		ArrayList<String> result = new ArrayList<String>();
		GitlabProject p;
		try {
			p = api.getProject(gitlab_pid);
			List<GitlabRepositoryTree> t = api.getRepositoryTree(p, path,
					branch);
			for (GitlabRepositoryTree gitlabRepositoryTree : t) {
				result.add(gitlabRepositoryTree.getName());
			}
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static ArrayList<String> getBranches(int gitlab_pid) {
		ArrayList<String> result = new ArrayList<String>();
		List<GitlabBranch> branchs;
		try {
			branchs = api.getBranches(gitlab_pid);
			for (GitlabBranch gitlabBranch : branchs) {
				result.add(gitlabBranch.getName());
			}
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static String getFileInfo(int gitlab_pid, String branch, String path) {
		try {
			return new String(api.getRawFileContent(gitlab_pid,branch,path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

	public static CommitDiff getVersionDiff(int gitlab_pid, String hash) {
		CommitDiff commitDiff = new CommitDiff();
		List<GitlabCommitDiff> diffs;
		try {
			diffs = api.getCommitDiffs(gitlab_pid, hash);
			commitDiff.setChangeFiles(diffs.size());
			int add = 0;
			int delete = 0;
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> changes = new ArrayList<String>();
			for (GitlabCommitDiff gitlabCommitDiff : diffs) {
				String diff = gitlabCommitDiff.getDiff();
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(new ByteArrayInputStream(
								diff.getBytes())));
				String read = "";
				StringBuffer buffer = new StringBuffer();
				read = bufferedReader.readLine();

				if (read.startsWith("Binary")) {
					if (gitlabCommitDiff.getDeletedFile()) {
						names.add(read.split(" ")[2].substring(2));
					}
					else {
						names.add(read.split(" ")[4].substring(2));
					}
				} else {
					if (gitlabCommitDiff.getDeletedFile()) {
						names.add(read.split(" ")[1].substring(2));
					} else {
						read = bufferedReader.readLine();
						names.add(read.split(" ")[1].substring(2));
					}
				}

				while ((read = bufferedReader.readLine()) != null) {
					buffer.append(read + "\n");
					if (read.startsWith("+")) {
						add += 1;
					}
					if (read.startsWith("-")) {
						delete += 1;
					}
				}
				changes.add(buffer.toString());
			}
			commitDiff.setAdd(add);
			commitDiff.setDelete(delete);
			commitDiff.setFileNames(names);
			commitDiff.setModifyContents(changes);
			return commitDiff;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static ArrayList<Commit> getCommits(int gitlab_pid, String branch) {
		ArrayList<Commit> list = new ArrayList<Commit>();
		List<GitlabCommit> commits;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			commits = api.getAllCommits(gitlab_pid);
			
			for (GitlabCommit gitlabCommit : commits) {
				Commit commit = new Commit();
				commit.setMessage(gitlabCommit.getMessage());
				commit.setCommitAuthor(gitlabCommit.getAuthorName());
				commit.setHash(gitlabCommit.getShortId());
				commit.setCommitTime(format.format(gitlabCommit.getCreatedAt()));
				list.add(commit);
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	//++
	
	public static HashMap<String, String> getCommitRecord(int gitlab_pid,
			int gitlab_gid, String branch) throws ParseException {

		HashMap<String, String> result = new HashMap<String, String>();
		List<GitlabCommit> commits;
		try {
			commits = api.getAllCommits(gitlab_pid, branch);
			List<GitlabGroupMember> members = api.getGroupMembers(gitlab_gid);
			TreeMap<Date, Integer> total = new TreeMap<Date, Integer>();
			for (GitlabCommit gitlabCommit : commits) {
				String time = format.format(gitlabCommit.getCreatedAt());
				if (total.containsKey(format.parse(time))) {
					total.put(format.parse(time),
							total.get(format.parse(time)) + 1);
				} else {
					total.put(format.parse(time), 1);
				}
			}
			result.put("total_" + branch, mapToString(total));
			result.put("total_" + branch + "_data", getTotalData(total));
			
			for (int i = 0; i < members.size(); i++) {
				TreeMap<Date, Integer> temp = new TreeMap<Date, Integer>();
				for (GitlabCommit gitlabCommit : commits) {
					if (gitlabCommit.getAuthorName().equals(members.get(i).getName())) {
						String time = format
								.format(gitlabCommit.getCreatedAt());
						if (temp.containsKey(format.parse(time))) {
							temp.put(format.parse(time),
									temp.get(format.parse(time)) + 1);
						} else {
							temp.put(format.parse(time), 1);
						}
					}
				}
				result.put(members.get(i).getName() + "_" + branch, mapToString(temp));
				result.put(members.get(i).getName() + "_" + branch + "_data",
						getTotalData(temp));
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	private static String getTotalData(TreeMap<Date, Integer> total) {
		int i = 0;
		String string = "[";
		for (Iterator it = total.entrySet().iterator(); it.hasNext();) {
			Entry e = (Entry) it.next();
			string = string + "[" + i + ",";
			string += e.getValue() + "]";
			if (it.hasNext())
				string += ",";
			i++;
		}
		string += "]";
		return string;
	}

	public static String mapToString(TreeMap<Date, Integer> total) {
		String string = "[";
		for (Iterator it = total.entrySet().iterator(); it.hasNext();) {
			Entry e = (Entry) it.next();
			string += "{date:";
			string += "'" + format.format(e.getKey()) + "',";
			string += "commit:";
			string += e.getValue() + "}";
			if (it.hasNext())
				string += ",";
		}
		string += "]";
		return string;
	}

	public static ArrayList<String> getMember(int gitlab_gid) {
		ArrayList<String> result = new ArrayList<String>();
		GitlabGroup g;
		try {
			List<GitlabGroupMember> members = api.getGroupMembers(gitlab_gid);
			for (GitlabGroupMember gitlabGroupMember : members) {
				result.add(gitlabGroupMember.getName());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	
	
}
