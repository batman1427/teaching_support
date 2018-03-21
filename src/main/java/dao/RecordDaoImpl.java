package dao;


import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RecordDaoImpl implements RecordDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void add(String sid, String type, String iteration, String time,
			String filename) {
		List<Map<String,Object>> result = jdbcTemplate.queryForList(utility.SqlQuery.COUNT_RECORD, sid);
		int id=result.size()+1;
		int i = jdbcTemplate.update(utility.SqlQuery.ADD_RECORD,
				new Object[] { sid, type,iteration,time,filename,id});
		
	}
	public CategoryDataset getDataSet(String sid) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		Object arg1[] = new Object[]{sid,"用例文档","迭代一"};
		Object arg2[] = new Object[]{sid,"用例文档","迭代二"};
		Object arg3[] = new Object[]{sid,"用例文档","迭代三"};
		Object arg4[] = new Object[]{sid,"需求规格说明文档","迭代一"};
		Object arg5[] = new Object[]{sid,"需求规格说明文档","迭代二"};
		Object arg6[] = new Object[]{sid,"需求规格说明文档","迭代三"};
		Object arg7[] = new Object[]{sid,"体系结构描述文档","迭代一"};
		Object arg8[] = new Object[]{sid,"体系结构描述文档","迭代二"};
		Object arg9[] = new Object[]{sid,"体系结构描述文档","迭代三"};
		Object arg10[] = new Object[]{sid,"详细设计描述文档","迭代一"};
		Object arg11[] = new Object[]{sid,"详细设计描述文档","迭代二"};
		Object arg12[] = new Object[]{sid,"详细设计描述文档","迭代三"};
		Object arg13[] = new Object[]{sid,"测试用例","迭代一"};
		Object arg14[] = new Object[]{sid,"测试用例","迭代二"};
		Object arg15[] = new Object[]{sid,"测试用例","迭代三"};
		List<Map<String,Object>> result1 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg1);
		List<Map<String,Object>> result2 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg2);
		List<Map<String,Object>> result3 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg3);
		List<Map<String,Object>> result4 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg4);
		List<Map<String,Object>> result5 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg5);
		List<Map<String,Object>> result6 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg6);
		List<Map<String,Object>> result7 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg7);
		List<Map<String,Object>> result8 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg8);
		List<Map<String,Object>> result9 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg9);
		List<Map<String,Object>> result10 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg10);
		List<Map<String,Object>> result11 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg11);
		List<Map<String,Object>> result12 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg12);
		List<Map<String,Object>> result13 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg13);
		List<Map<String,Object>> result14 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg14);
		List<Map<String,Object>> result15 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg15);
		dataset.addValue(result1.size(), "迭代一", "用例");  
        dataset.addValue(result2.size(), "迭代二", "用例");  
        dataset.addValue(result3.size(), "迭代三", "用例");  
        dataset.addValue(result4.size(), "迭代一", "需求");  
        dataset.addValue(result5.size(), "迭代二", "需求");  
        dataset.addValue(result6.size(), "迭代三", "需求");  
        dataset.addValue(result7.size(), "迭代一", "结构");  
        dataset.addValue(result8.size(), "迭代二", "结构");  
        dataset.addValue(result9.size(), "迭代三", "结构");  
        dataset.addValue(result10.size(), "迭代一", "设计");  
        dataset.addValue(result11.size(), "迭代二", "设计");  
        dataset.addValue(result12.size(), "迭代三", "设计"); 
        dataset.addValue(result13.size(), "迭代一", "测试");  
        dataset.addValue(result14.size(), "迭代二", "测试");  
        dataset.addValue(result15.size(), "迭代三", "测试");  
		return dataset;
	}
	public CategoryDataset getDataSet_team(int gid) {
		// TODO Auto-generated method stub
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		//获取全部小组成员
		List<Map<String,Object>> result = jdbcTemplate.queryForList(utility.SqlQuery.TEAM, gid);
		for(int i=0;i<result.size();i++){
			String sid=(String) result.get(i).get("sid");
			Object arg1[] = new Object[]{sid,"迭代一"};
			Object arg2[] = new Object[]{sid,"迭代二"};
			Object arg3[] = new Object[]{sid,"迭代三"};
			List<Map<String,Object>> result1 = jdbcTemplate.queryForList(utility.SqlQuery.COUNT_TEAM, arg1);
			List<Map<String,Object>> result2 = jdbcTemplate.queryForList(utility.SqlQuery.COUNT_TEAM, arg2);
			List<Map<String,Object>> result3= jdbcTemplate.queryForList(utility.SqlQuery.COUNT_TEAM, arg3);
			dataset.addValue(result1.size(), "迭代一", sid);  
	        dataset.addValue(result2.size(), "迭代二", sid);  
	        dataset.addValue(result3.size(), "迭代三", sid);  
		}
		return dataset;
	}
	public DefaultPieDataset piedata(String sid, int gid) {
		// TODO Auto-generated method stub
		DefaultPieDataset dataset = new DefaultPieDataset();
		//获取全部小组成员     文档类型数*每种文档基础工作量*[(修改次数+20)/20]*最终文档总大小*√(总提交次数)
		List<Map<String,Object>> result = jdbcTemplate.queryForList(utility.SqlQuery.TEAM, gid);
		//计算每个人的工作量
		for(int i=0;i<result.size();i++){
			//提交总次数
			int count=getcount((String)result.get(i).get("sid"));
			//最终文档总大小
			int size=(int) cal((String)result.get(i).get("sid"));
			//文档类型数
			int type=cal_type((String)result.get(i).get("sid"));
			//修改次数
			int modify=count-type;
			//计算成绩
			double score=1*((size/1000)*type*(modify+100)/100*Math.sqrt(count));
			dataset.setValue((String)result.get(i).get("sid"), score);
            
		}
		return dataset;
	}
	//提交次数
	public int getcount(String sid) {
        String path = "e:/"+sid; // 路径
        File f = new File(path);
        File fa[] = f.listFiles();
        int a=fa.length;
		return a;
        
    }
	//文档大小
	   // 递归方式 计算文件的大小
    public int getTotalSizeOfFilesInDir( File file) {
        if (file.isFile())
            return (int) file.length();
         File[] children = file.listFiles();
        int total = 0;
        if (children != null)
            for (final File child : children)
                total += getTotalSizeOfFilesInDir(child);
        return total;
    }
    public long cal(String sid) {
        int total =getTotalSizeOfFilesInDir(new File("e:\\"+sid));
        return total;

    }
    //计算类型数
    public int cal_type(String sid) {
		// TODO Auto-generated method stub
		int count=0;
		String[] result=getFileName(sid);
		if(Arrays.asList(result).contains("用例文档迭代一")){
			count++;
		};
		if(Arrays.asList(result).contains("用例文档迭代二")){
			count++;
		};
		if(Arrays.asList(result).contains("用例文档迭代三")){
			count++;
		};
		if(Arrays.asList(result).contains("需求规格说明文档迭代一")){
			count++;
		};
		if(Arrays.asList(result).contains("需求规格说明文档迭代二")){
			count++;
		};
		if(Arrays.asList(result).contains("需求规格说明文档迭代三")){
			count++;
		};
		if(Arrays.asList(result).contains("体系结构描述文档迭代一")){
			count++;
		};
		if(Arrays.asList(result).contains("体系结构描述文档迭代二")){
			count++;
		};
		if(Arrays.asList(result).contains("体系结构描述文档迭代三")){
			count++;
		};
		if(Arrays.asList(result).contains("详细设计描述文档迭代一")){
			count++;
		};
		if(Arrays.asList(result).contains("详细设计描述文档迭代二")){
			count++;
		};
		if(Arrays.asList(result).contains("详细设计描述文档迭代三")){
			count++;
		};
		if(Arrays.asList(result).contains("测试用例迭代一")){
			count++;
		};
		if(Arrays.asList(result).contains("测试用例迭代二")){
			count++;
		};
		if(Arrays.asList(result).contains("测试用例迭代三")){
			count++;
		};
		return count;
	}

	public String[]  getFileName(String sid) {
	        String path = "e:/"+sid; // 路径
	        File f = new File(path);
	        File fa[] = f.listFiles();
	        int length=fa.length;
	        String[] type=new String[length];
	        for (int i = 0; i < fa.length; i++) {
	            File fs = fa[i];
	            type[i]=fs.getName().split("_")[1];
	        }
			return type;
	    }
	public DefaultCategoryDataset linedata(String sid) {
		// TODO Auto-generated method stub
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
		Object arg1[] = new Object[]{sid,"迭代一"};
		Object arg2[] = new Object[]{sid,"迭代二"};
		Object arg3[] = new Object[]{sid,"迭代三"};
		List<Map<String,Object>> result1= jdbcTemplate.queryForList(utility.SqlQuery.SCORE, arg1);
		List<Map<String,Object>> result2= jdbcTemplate.queryForList(utility.SqlQuery.SCORE, arg2);
		List<Map<String,Object>> result3= jdbcTemplate.queryForList(utility.SqlQuery.SCORE, arg3);
	    double a=(Double) result1.get(0).get("document");
	    double b=(Double) result1.get(0).get("code");
	    double c=(Double) result1.get(0).get("teamwork");
	    double d=(a+b+c)/3;
	    double a1=(Double) result2.get(0).get("document");
	    double b1=(Double) result2.get(0).get("code");
	    double c1=(Double) result2.get(0).get("teamwork");
	    double d1=(a1+b1+c1)/3;
	    double a2=(Double) result3.get(0).get("document");
	    double b2=(Double) result3.get(0).get("code");
	    double c2=(Double) result3.get(0).get("teamwork");
	    double d2=(a2+b2+c2)/3;
        dataset.addValue(a, "文档", "迭代一");  
        dataset.addValue(b, "代码", "迭代一");  
        dataset.addValue(c, "团队协作", "迭代一");  
        dataset.addValue(d, "总成绩", "迭代一");  
        dataset.addValue(a1, "文档", "迭代二");  
        dataset.addValue(b1, "代码", "迭代二");  
        dataset.addValue(c1, "团队协作", "迭代二");
        dataset.addValue(d1, "总成绩", "迭代二");
        dataset.addValue(a2, "文档", "迭代三");  
        dataset.addValue(b2, "代码", "迭代三");  
        dataset.addValue(c2, "团队协作", "迭代三");  
        dataset.addValue(d2, "总成绩", "迭代三");  
		return dataset;
	}
}
