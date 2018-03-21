package service;

public interface RecordService {
		public void add(String sid,String type,String iteration,String time,String filename);
		public void createBarChart(String sid);
		public void createBarChart_team(int gid);
		public void createPieChart(String sid,int gid);
		public void createLineChart(String sid);
}
