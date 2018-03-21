package utility;

public class SqlQuery {
	public static final String FIND_USER = "select * from user where username = ?";
	public static final String FIND_PSW = "select psw from user where username = ? and sid = ?";
	public static final String ADD_USER = "insert into user values(?,?,?,?,?)";
	public static final String FIND_GROUP = "select * from group_info where gid = ?";
	public static final String ADD_GROUP = "insert into group_info values(?,?,?,?)";
	public static final String CHECK_SID = "select * from user where sid = ?";
	public static final String UPDATE_ADDRESS = "update group_info set git_address = ? where gid = ? ";
	public static final String UPDATE_GID_PID = "update group_info set gitlab_gid = ?,gitlab_pid = ? where gid = ? ";
	//新增
	//增加记录
	public static final String ADD_RECORD = "insert into record values(?,?,?,?,?,?)";
	public static final String COUNT_RECORD = "select * from record where sid = ?";
	//获取柱形图数据
	public static final String EACH_RECORD = "select * from record where sid = ? and type = ? and iteration = ?";
	//获取小组成员
	public static final String TEAM = "select * from user where gid = ?";
	//获取学生的每次迭代总提交次数
	public static final String COUNT_TEAM = "select * from record where sid = ? and iteration = ? ";
	//获取每次迭代的成绩
	public static final String SCORE = "select * from score where sid = ? and iteration = ? ";
	//新增
	public static final String MEMBERS = "select * from user where gid = ?";
	//读取所有discussion
	public static final String DISCUSSION = "select * from discussion order by did desc";
	public static final String COUNT_DISS = "select * from discussion";
	//增加讨论
	public static final String ADD_DISS = "insert into discussion values(?,?,?,?)";
	//找出主题和对应的评论
	public static final String ONEDISCUSSION = "select * from discussion where did= ? ";
	public static final String COMMIT = "select * from commit where did= ? order by cid desc";
	//增加评论
	public static final String COUNT_COMMIT = "select * from commit where did= ?";
	public static final String ADD_COMMIT = "insert into commit values(?,?,?,?,?)";
}
