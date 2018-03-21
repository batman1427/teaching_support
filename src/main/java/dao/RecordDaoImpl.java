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
		Object arg1[] = new Object[]{sid,"�����ĵ�","����һ"};
		Object arg2[] = new Object[]{sid,"�����ĵ�","������"};
		Object arg3[] = new Object[]{sid,"�����ĵ�","������"};
		Object arg4[] = new Object[]{sid,"������˵���ĵ�","����һ"};
		Object arg5[] = new Object[]{sid,"������˵���ĵ�","������"};
		Object arg6[] = new Object[]{sid,"������˵���ĵ�","������"};
		Object arg7[] = new Object[]{sid,"��ϵ�ṹ�����ĵ�","����һ"};
		Object arg8[] = new Object[]{sid,"��ϵ�ṹ�����ĵ�","������"};
		Object arg9[] = new Object[]{sid,"��ϵ�ṹ�����ĵ�","������"};
		Object arg10[] = new Object[]{sid,"��ϸ��������ĵ�","����һ"};
		Object arg11[] = new Object[]{sid,"��ϸ��������ĵ�","������"};
		Object arg12[] = new Object[]{sid,"��ϸ��������ĵ�","������"};
		Object arg13[] = new Object[]{sid,"��������","����һ"};
		Object arg14[] = new Object[]{sid,"��������","������"};
		Object arg15[] = new Object[]{sid,"��������","������"};
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
		dataset.addValue(result1.size(), "����һ", "����");  
        dataset.addValue(result2.size(), "������", "����");  
        dataset.addValue(result3.size(), "������", "����");  
        dataset.addValue(result4.size(), "����һ", "����");  
        dataset.addValue(result5.size(), "������", "����");  
        dataset.addValue(result6.size(), "������", "����");  
        dataset.addValue(result7.size(), "����һ", "�ṹ");  
        dataset.addValue(result8.size(), "������", "�ṹ");  
        dataset.addValue(result9.size(), "������", "�ṹ");  
        dataset.addValue(result10.size(), "����һ", "���");  
        dataset.addValue(result11.size(), "������", "���");  
        dataset.addValue(result12.size(), "������", "���"); 
        dataset.addValue(result13.size(), "����һ", "����");  
        dataset.addValue(result14.size(), "������", "����");  
        dataset.addValue(result15.size(), "������", "����");  
		return dataset;
	}
	public CategoryDataset getDataSet_team(int gid) {
		// TODO Auto-generated method stub
		DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		//��ȡȫ��С���Ա
		List<Map<String,Object>> result = jdbcTemplate.queryForList(utility.SqlQuery.TEAM, gid);
		for(int i=0;i<result.size();i++){
			String sid=(String) result.get(i).get("sid");
			Object arg1[] = new Object[]{sid,"����һ"};
			Object arg2[] = new Object[]{sid,"������"};
			Object arg3[] = new Object[]{sid,"������"};
			List<Map<String,Object>> result1 = jdbcTemplate.queryForList(utility.SqlQuery.COUNT_TEAM, arg1);
			List<Map<String,Object>> result2 = jdbcTemplate.queryForList(utility.SqlQuery.COUNT_TEAM, arg2);
			List<Map<String,Object>> result3= jdbcTemplate.queryForList(utility.SqlQuery.COUNT_TEAM, arg3);
			dataset.addValue(result1.size(), "����һ", sid);  
	        dataset.addValue(result2.size(), "������", sid);  
	        dataset.addValue(result3.size(), "������", sid);  
		}
		return dataset;
	}
	public DefaultPieDataset piedata(String sid, int gid) {
		// TODO Auto-generated method stub
		DefaultPieDataset dataset = new DefaultPieDataset();
		//��ȡȫ��С���Ա     �ĵ�������*ÿ���ĵ�����������*[(�޸Ĵ���+20)/20]*�����ĵ��ܴ�С*��(���ύ����)
		List<Map<String,Object>> result = jdbcTemplate.queryForList(utility.SqlQuery.TEAM, gid);
		//����ÿ���˵Ĺ�����
		for(int i=0;i<result.size();i++){
			//�ύ�ܴ���
			int count=getcount((String)result.get(i).get("sid"));
			//�����ĵ��ܴ�С
			int size=(int) cal((String)result.get(i).get("sid"));
			//�ĵ�������
			int type=cal_type((String)result.get(i).get("sid"));
			//�޸Ĵ���
			int modify=count-type;
			//����ɼ�
			double score=1*((size/1000)*type*(modify+100)/100*Math.sqrt(count));
			dataset.setValue((String)result.get(i).get("sid"), score);
            
		}
		return dataset;
	}
	//�ύ����
	public int getcount(String sid) {
        String path = "e:/"+sid; // ·��
        File f = new File(path);
        File fa[] = f.listFiles();
        int a=fa.length;
		return a;
        
    }
	//�ĵ���С
	   // �ݹ鷽ʽ �����ļ��Ĵ�С
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
    //����������
    public int cal_type(String sid) {
		// TODO Auto-generated method stub
		int count=0;
		String[] result=getFileName(sid);
		if(Arrays.asList(result).contains("�����ĵ�����һ")){
			count++;
		};
		if(Arrays.asList(result).contains("�����ĵ�������")){
			count++;
		};
		if(Arrays.asList(result).contains("�����ĵ�������")){
			count++;
		};
		if(Arrays.asList(result).contains("������˵���ĵ�����һ")){
			count++;
		};
		if(Arrays.asList(result).contains("������˵���ĵ�������")){
			count++;
		};
		if(Arrays.asList(result).contains("������˵���ĵ�������")){
			count++;
		};
		if(Arrays.asList(result).contains("��ϵ�ṹ�����ĵ�����һ")){
			count++;
		};
		if(Arrays.asList(result).contains("��ϵ�ṹ�����ĵ�������")){
			count++;
		};
		if(Arrays.asList(result).contains("��ϵ�ṹ�����ĵ�������")){
			count++;
		};
		if(Arrays.asList(result).contains("��ϸ��������ĵ�����һ")){
			count++;
		};
		if(Arrays.asList(result).contains("��ϸ��������ĵ�������")){
			count++;
		};
		if(Arrays.asList(result).contains("��ϸ��������ĵ�������")){
			count++;
		};
		if(Arrays.asList(result).contains("������������һ")){
			count++;
		};
		if(Arrays.asList(result).contains("��������������")){
			count++;
		};
		if(Arrays.asList(result).contains("��������������")){
			count++;
		};
		return count;
	}

	public String[]  getFileName(String sid) {
	        String path = "e:/"+sid; // ·��
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
		Object arg1[] = new Object[]{sid,"����һ"};
		Object arg2[] = new Object[]{sid,"������"};
		Object arg3[] = new Object[]{sid,"������"};
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
        dataset.addValue(a, "�ĵ�", "����һ");  
        dataset.addValue(b, "����", "����һ");  
        dataset.addValue(c, "�Ŷ�Э��", "����һ");  
        dataset.addValue(d, "�ܳɼ�", "����һ");  
        dataset.addValue(a1, "�ĵ�", "������");  
        dataset.addValue(b1, "����", "������");  
        dataset.addValue(c1, "�Ŷ�Э��", "������");
        dataset.addValue(d1, "�ܳɼ�", "������");
        dataset.addValue(a2, "�ĵ�", "������");  
        dataset.addValue(b2, "����", "������");  
        dataset.addValue(c2, "�Ŷ�Э��", "������");  
        dataset.addValue(d2, "�ܳɼ�", "������");  
		return dataset;
	}
}
