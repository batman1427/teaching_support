package dao;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public interface RecordDao {
	public void add(String sid,String type,String iteration,String time,String filename);
	public CategoryDataset getDataSet(String sid);
	public CategoryDataset getDataSet_team(int gid);
	public DefaultPieDataset piedata(String sid,int gid);
	public DefaultCategoryDataset linedata(String sid);
}
