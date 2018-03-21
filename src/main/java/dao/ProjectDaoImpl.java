package dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDaoImpl implements ProjectDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
    //获取小组所有成员
	public String getmembers(int gid) {
		// TODO Auto-generated method stub
		String temp="";
		List<Map<String,Object>> result = jdbcTemplate.queryForList(utility.SqlQuery.MEMBERS, gid);
		for(int i=0;i<result.size();i++){
			temp=temp+(String) result.get(i).get("sid")+"_"+(String) result.get(i).get("name")+";";
		}
		return temp;
	}
    //查看哪些人交了相同的文档
	public String[] sameDocument(String sid,int gid) {
		// TODO Auto-generated method stub
        String sid1="";
        String sid2="";
        String sid3="";
        String sid4="";
        String[]  result={"_","_","_","_","_","_","_","_","_","_","_","_","_","_","_"};
		List<Map<String,Object>> re = jdbcTemplate.queryForList(utility.SqlQuery.MEMBERS, gid);
		sid1=(String) re.get(0).get("sid");
		sid2=(String) re.get(1).get("sid");
		sid3=(String) re.get(2).get("sid");
		sid4=(String) re.get(3).get("sid");
		String temp="";
		if(sid1.equals(sid)){
			
		}else if(sid2.equals(sid)){
			temp=sid1;
			sid1=sid2;
			sid2=temp;
		}else if(sid3.equals(sid)){
			temp=sid1;
			sid1=sid3;
			sid3=temp;
		}else if(sid4.equals(sid)){
			temp=sid1;
			sid1=sid4;
			sid4=temp;
		}
		Object arg1[] = new Object[]{sid1,"用例文档","迭代一"};
		List<Map<String,Object>> r0 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg1);
		if(r0.size()>0){
			result[0]=result[0]+sid1+"_";
		}
		Object arg2[] = new Object[]{sid1,"需求规格说明文档","迭代一"};
		List<Map<String,Object>> r1 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg2);
		if(r1.size()>0){
			result[1]=result[1]+sid1+"_";
		}
		Object arg3[] = new Object[]{sid1,"体系结构描述文档","迭代一"};
		List<Map<String,Object>> r2 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg3);
		if(r2.size()>0){
			result[2]=result[2]+sid1+"_";
		}
		Object arg4[] = new Object[]{sid1,"详细设计描述文档","迭代一"};
		List<Map<String,Object>> r3 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg4);
		if(r3.size()>0){
			result[3]=result[3]+sid1+"_";
		}
		Object arg5[] = new Object[]{sid1,"测试用例","迭代一"};
		List<Map<String,Object>> r4 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg5);
		if(r4.size()>0){
			result[4]=result[4]+sid1+"_";
		}
		Object arg6[] = new Object[]{sid1,"用例文档","迭代二"};
		List<Map<String,Object>> r5 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg6);
		if(r5.size()>0){
			result[5]=result[5]+sid1+"_";
		}
		Object arg7[] = new Object[]{sid1,"需求规格说明文档","迭代二"};
		List<Map<String,Object>> r6 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg7);
		if(r6.size()>0){
			result[6]=result[6]+sid1+"_";
		}
		Object arg8[] = new Object[]{sid1,"体系结构描述文档","迭代二"};
		List<Map<String,Object>> r7 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg8);
		if(r7.size()>0){
			result[7]=result[7]+sid1+"_";
		}
		Object arg9[] = new Object[]{sid1,"详细设计描述文档","迭代二"};
		List<Map<String,Object>> r8 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg9);
		if(r8.size()>0){
			result[8]=result[8]+sid1+"_";
		}
		Object arg10[] = new Object[]{sid1,"测试用例","迭代二"};
		List<Map<String,Object>> r9 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg10);
		if(r9.size()>0){
			result[9]=result[9]+sid1+"_";
		}
		Object arg11[] = new Object[]{sid1,"用例文档","迭代三"};
		List<Map<String,Object>> r10 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg11);
		if(r10.size()>0){
			result[10]=result[10]+sid1+"_";
		}
		Object arg12[] = new Object[]{sid1,"体系结构描述文档","迭代三"};
		List<Map<String,Object>> r11 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg12);
		if(r11.size()>0){
			result[11]=result[11]+sid1+"_";
		}
		Object arg13[] = new Object[]{sid1,"需求规格说明文档","迭代三"};
		List<Map<String,Object>> r12 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg13);
		if(r12.size()>0){
			result[12]=result[12]+sid1+"_";
		}
		Object arg14[] = new Object[]{sid1,"详细设计描述文档","迭代三"};
		List<Map<String,Object>> r13 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg14);
		if(r13.size()>0){
			result[13]=result[13]+sid1+"_";
		}
		Object arg15[] = new Object[]{sid1,"测试用例","迭代三"};
		List<Map<String,Object>> r14 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg15);
		if(r14.size()>0){
			result[14]=result[14]+sid1+"_";
		}
		
		
		
		Object arg16[] = new Object[]{sid2,"用例文档","迭代一"};
		List<Map<String,Object>> r15 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg16);
		if(r15.size()>0){
			result[0]=result[0]+sid2+"_";
		}
		Object arg17[] = new Object[]{sid2,"需求规格说明文档","迭代一"};
		List<Map<String,Object>> r16 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg17);
		if(r16.size()>0){
			result[1]=result[1]+sid2+"_";
		}
		Object arg18[] = new Object[]{sid2,"体系结构描述文档","迭代一"};
		List<Map<String,Object>> r17 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg18);
		if(r17.size()>0){
			result[2]=result[2]+sid2+"_";
		}
		Object arg19[] = new Object[]{sid2,"详细设计描述文档","迭代一"};
		List<Map<String,Object>> r18 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg19);
		if(r18.size()>0){
			result[3]=result[3]+sid2+"_";
		}
		Object arg20[] = new Object[]{sid2,"测试用例","迭代一"};
		List<Map<String,Object>> r19 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg20);
		if(r19.size()>0){
			result[4]=result[4]+sid2+"_";
		}
		Object arg21[] = new Object[]{sid2,"用例文档","迭代二"};
		List<Map<String,Object>> r20 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg21);
		if(r20.size()>0){
			result[5]=result[5]+sid2+"_";
		}
		Object arg22[] = new Object[]{sid2,"需求规格说明文档","迭代二"};
		List<Map<String,Object>> r21 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg22);
		if(r21.size()>0){
			result[6]=result[6]+sid2+"_";
		}
		Object arg23[] = new Object[]{sid2,"体系结构描述文档","迭代二"};
		List<Map<String,Object>> r22 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg23);
		if(r22.size()>0){
			result[7]=result[7]+sid2+"_";
		}
		Object arg24[] = new Object[]{sid2,"详细设计描述文档","迭代二"};
		List<Map<String,Object>> r23 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg24);
		if(r23.size()>0){
			result[8]=result[8]+sid2+"_";
		}
		Object arg25[] = new Object[]{sid2,"测试用例","迭代二"};
		List<Map<String,Object>> r24 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg25);
		if(r24.size()>0){
			result[9]=result[9]+sid2+"_";
		}
		Object arg26[] = new Object[]{sid2,"用例文档","迭代三"};
		List<Map<String,Object>> r25 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg26);
		if(r25.size()>0){
			result[10]=result[10]+sid2+"_";
		}
		Object arg27[] = new Object[]{sid2,"体系结构描述文档","迭代三"};
		List<Map<String,Object>> r26 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg27);
		if(r26.size()>0){
			result[11]=result[11]+sid2+"_";
		}
		Object arg28[] = new Object[]{sid2,"需求规格说明文档","迭代三"};
		List<Map<String,Object>> r27 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg28);
		if(r27.size()>0){
			result[12]=result[12]+sid2+"_";
		}
		Object arg29[] = new Object[]{sid2,"详细设计描述文档","迭代三"};
		List<Map<String,Object>> r28 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg29);
		if(r28.size()>0){
			result[13]=result[13]+sid2+"_";
		}
		Object arg30[] = new Object[]{sid2,"测试用例","迭代三"};
		List<Map<String,Object>> r29 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg30);
		if(r29.size()>0){
			result[14]=result[14]+sid2+"_";
		}
		
	
		Object arg31[] = new Object[]{sid3,"用例文档","迭代一"};
		List<Map<String,Object>> r30 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg31);
		if(r30.size()>0){
			result[0]=result[0]+sid3+"_";
		}
		Object arg32[] = new Object[]{sid3,"需求规格说明文档","迭代一"};
		List<Map<String,Object>> r31 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg32);
		if(r31.size()>0){
			result[1]=result[1]+sid3+"_";
		}
		Object arg33[] = new Object[]{sid3,"体系结构描述文档","迭代一"};
		List<Map<String,Object>> r32 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg33);
		if(r32.size()>0){
			result[2]=result[2]+sid3+"_";
		}
		Object arg34[] = new Object[]{sid3,"详细设计描述文档","迭代一"};
		List<Map<String,Object>> r33 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg34);
		if(r33.size()>0){
			result[3]=result[3]+sid3+"_";
		}
		Object arg35[] = new Object[]{sid3,"测试用例","迭代一"};
		List<Map<String,Object>> r34 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg35);
		if(r34.size()>0){
			result[4]=result[4]+sid3+"_";
		}
		Object arg36[] = new Object[]{sid3,"用例文档","迭代二"};
		List<Map<String,Object>> r35 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg36);
		if(r35.size()>0){
			result[5]=result[5]+sid3+"_";
		}
		Object arg37[] = new Object[]{sid3,"需求规格说明文档","迭代二"};
		List<Map<String,Object>> r36 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg37);
		if(r36.size()>0){
			result[6]=result[6]+sid3+"_";
		}
		Object arg38[] = new Object[]{sid3,"体系结构描述文档","迭代二"};
		List<Map<String,Object>> r37 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg38);
		if(r37.size()>0){
			result[7]=result[7]+sid3+"_";
		}
		Object arg39[] = new Object[]{sid3,"详细设计描述文档","迭代二"};
		List<Map<String,Object>> r38 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg39);
		if(r38.size()>0){
			result[8]=result[8]+sid3+"_";
		}
		Object arg40[] = new Object[]{sid3,"测试用例","迭代二"};
		List<Map<String,Object>> r39 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg40);
		if(r39.size()>0){
			result[9]=result[9]+sid3+"_";
		}
		Object arg41[] = new Object[]{sid3,"用例文档","迭代三"};
		List<Map<String,Object>> r40 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg41);
		if(r40.size()>0){
			result[10]=result[10]+sid3+"_";
		}
		Object arg42[] = new Object[]{sid3,"体系结构描述文档","迭代三"};
		List<Map<String,Object>> r41 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg42);
		if(r41.size()>0){
			result[11]=result[11]+sid3+"_";
		}
		Object arg43[] = new Object[]{sid3,"需求规格说明文档","迭代三"};
		List<Map<String,Object>> r42 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg43);
		if(r42.size()>0){
			result[12]=result[12]+sid3+"_";
		}
		Object arg44[] = new Object[]{sid3,"详细设计描述文档","迭代三"};
		List<Map<String,Object>> r43 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg44);
		if(r43.size()>0){
			result[13]=result[13]+sid3+"_";
		}
		Object arg45[] = new Object[]{sid3,"测试用例","迭代三"};
		List<Map<String,Object>> r44 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg45);
		if(r44.size()>0){
			result[14]=result[14]+sid3+"_";
		}
		
		
	
		Object arg46[] = new Object[]{sid4,"用例文档","迭代一"};
		List<Map<String,Object>> r45 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg46);
		if(r45.size()>0){
			result[0]=result[0]+sid4+"_";
		}
		Object arg47[] = new Object[]{sid4,"需求规格说明文档","迭代一"};
		List<Map<String,Object>> r46 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg47);
		if(r46.size()>0){
			result[1]=result[1]+sid4+"_";
		}
		Object arg48[] = new Object[]{sid4,"体系结构描述文档","迭代一"};
		List<Map<String,Object>> r47 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg48);
		if(r47.size()>0){
			result[2]=result[2]+sid4+"_";
		}
		Object arg49[] = new Object[]{sid4,"详细设计描述文档","迭代一"};
		List<Map<String,Object>> r48 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg49);
		if(r48.size()>0){
			result[3]=result[3]+sid4+"_";
		}
		Object arg50[] = new Object[]{sid4,"测试用例","迭代一"};
		List<Map<String,Object>> r49 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg50);
		if(r49.size()>0){
			result[4]=result[4]+sid4+"_";
		}
		Object arg51[] = new Object[]{sid4,"用例文档","迭代二"};
		List<Map<String,Object>> r50 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg51);
		if(r50.size()>0){
			result[5]=result[5]+sid4+"_";
		}
		Object arg52[] = new Object[]{sid4,"需求规格说明文档","迭代二"};
		List<Map<String,Object>> r51 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg52);
		if(r51.size()>0){
			result[6]=result[6]+sid4+"_";
		}
		Object arg53[] = new Object[]{sid4,"体系结构描述文档","迭代二"};
		List<Map<String,Object>> r52 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg53);
		if(r52.size()>0){
			result[7]=result[7]+sid4+"_";
		}
		Object arg54[] = new Object[]{sid4,"详细设计描述文档","迭代二"};
		List<Map<String,Object>> r53 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg54);
		if(r53.size()>0){
			result[8]=result[8]+sid4+"_";
		}
		Object arg55[] = new Object[]{sid4,"测试用例","迭代二"};
		List<Map<String,Object>> r54 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg55);
		if(r54.size()>0){
			result[9]=result[9]+sid4+"_";
		}
		Object arg56[] = new Object[]{sid4,"用例文档","迭代三"};
		List<Map<String,Object>> r55 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg56);
		if(r55.size()>0){
			result[10]=result[10]+sid4+"_";
		}
		Object arg57[] = new Object[]{sid4,"体系结构描述文档","迭代三"};
		List<Map<String,Object>> r56 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg57);
		if(r56.size()>0){
			result[11]=result[11]+sid4+"_";
		}
		Object arg58[] = new Object[]{sid4,"需求规格说明文档","迭代三"};
		List<Map<String,Object>> r57 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg58);
		if(r57.size()>0){
			result[12]=result[12]+sid4+"_";
		}
		Object arg59[] = new Object[]{sid4,"详细设计描述文档","迭代三"};
		List<Map<String,Object>> r58 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg59);
		if(r58.size()>0){
			result[13]=result[13]+sid4+"_";
		}
		Object arg60[] = new Object[]{sid4,"测试用例","迭代三"};
		List<Map<String,Object>> r59 = jdbcTemplate.queryForList(utility.SqlQuery.EACH_RECORD, arg60);
		if(r59.size()>0){
			result[14]=result[14]+sid4+"_";
		}
		return result;
	}
	public String getdiscussion(){
		String result="";
		List<Map<String,Object>> re = jdbcTemplate.queryForList(utility.SqlQuery.DISCUSSION);
		for(int i=0;i<re.size();i++){
			result=result+";"+re.get(i).get("theme");
			result=result+";"+re.get(i).get("sid")+"_"+re.get(i).get("time");
			result=result+";"+re.get(i).get("did");
		}
		return result;
		
	}
	public void adddiss(String sid, String time, String theme) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> re = jdbcTemplate.queryForList(utility.SqlQuery.COUNT_DISS);
		String did=String.valueOf(re.size()+1);
		Object arg[] = new Object[]{theme,time,sid,did};
		jdbcTemplate.update(utility.SqlQuery.ADD_DISS,arg);
	}
	public String getcommit(String content) {
		// TODO Auto-generated method stub
		String result="";
		List<Map<String,Object>> r = jdbcTemplate.queryForList(utility.SqlQuery.ONEDISCUSSION,content);
		result=result+";"+r.get(0).get("theme");
		result=result+";"+r.get(0).get("sid")+"_"+r.get(0).get("time");
		List<Map<String,Object>> re = jdbcTemplate.queryForList(utility.SqlQuery.COMMIT,content);
		for(int i=0;i<re.size();i++){
			result=result+";"+re.get(i).get("content");
			result=result+";"+re.get(i).get("sid")+"_"+re.get(i).get("time");
		}
		
		return result;
	}
	public void addcommit(String sid, String time, String content, String did) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<Map<String,Object>> re = jdbcTemplate.queryForList(utility.SqlQuery.COUNT_COMMIT,did);
		String cid=String.valueOf(re.size()+1);
		Object arg[] = new Object[]{did,cid,content,time,sid};
		jdbcTemplate.update(utility.SqlQuery.ADD_COMMIT,arg);
	}
}