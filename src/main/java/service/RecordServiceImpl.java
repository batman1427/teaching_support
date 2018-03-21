package service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import git.GitAdmin;

import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.RecordDao;


@Service
public class RecordServiceImpl implements RecordService {
	@Autowired
	private RecordDao recordDao;
	public void add(String sid, String type, String iteration, String time,
			String filename) {
		recordDao.add(sid, type, iteration, time, filename);
		
	}
	public void createBarChart(String sid){
		CategoryDataset dataset = recordDao.getDataSet(sid);
		JFreeChart chart = ChartFactory.createBarChart3D(  
                "�ĵ��ύͳ��", // ͼ�����  
                "����", // Ŀ¼�����ʾ��ǩ--����  
                "����", // ��ֵ�����ʾ��ǩ--����  
                dataset, // ���ݼ�  
                PlotOrientation.VERTICAL, // ͼ����ˮƽ��  
                true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ����  
                false, // �Ƿ����ɹ���  
                false // �Ƿ�����URL����  
                ); 
		CategoryPlot plot = chart.getCategoryPlot(); 
		chart.setBackgroundPaint(java.awt.Color.white);
        CategoryAxis domainAxis = plot.getDomainAxis();  
        ValueAxis rAxis = plot.getRangeAxis();  
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,  
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);  
        TextTitle textTitle = chart.getTitle();  
        textTitle.setFont(new Font("����", 0, 20));  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
        rAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        rAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));  
        FileOutputStream fos_jpg = null;  
        try {  
            fos_jpg = new FileOutputStream("G:\\git\\cseiii\\src\\main\\webapp\\images\\"+sid+"selfbar.jpg");  
            ChartUtilities.writeChartAsJPEG(fos_jpg, 1, chart, 500, 400, null);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                fos_jpg.close();  
            } catch (Exception e) {  
            }  
        }  
	}
	public void createBarChart_team(int gid){
		CategoryDataset dataset = recordDao.getDataSet_team(gid);
		JFreeChart chart = ChartFactory.createBarChart3D(  
                "�Ŷ��ĵ��ύͳ��ͼ", // ͼ�����  
                "�Ŷӳ�Ա", // Ŀ¼�����ʾ��ǩ--����  
                "����", // ��ֵ�����ʾ��ǩ--����  
                dataset, // ���ݼ�  
                PlotOrientation.VERTICAL, // ͼ����ˮƽ��  
                true, // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ����  
                false, // �Ƿ����ɹ���  
                false // �Ƿ�����URL����  
                ); 
		CategoryPlot plot = chart.getCategoryPlot();   
        CategoryAxis domainAxis = plot.getDomainAxis();  
        ValueAxis rAxis = plot.getRangeAxis();  
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,  
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);  
        TextTitle textTitle = chart.getTitle();  
        textTitle.setFont(new Font("����", 0, 20));  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
        rAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        rAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));  
        FileOutputStream fos_jpg = null;  
        try {  
            fos_jpg = new FileOutputStream("G:\\git\\cseiii\\src\\main\\webapp\\images\\"+gid+"teambar.jpg");  
            ChartUtilities.writeChartAsJPEG(fos_jpg, 1, chart, 500, 400, null);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                fos_jpg.close();  
            } catch (Exception e) {  
            }  
        }  
	}
	public void createPieChart(String sid,int gid){
		DefaultPieDataset dataset = recordDao.piedata(sid,gid);
		JFreeChart chart = ChartFactory.createPieChart("�Ŷ��ĵ����׷���", dataset,
				true, true, true);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("����", 0, 12));
		chart.getTitle().setFont(new Font("����", 0, 20));
		chart.getLegend().setItemFont(new Font("����", 0, 12));
		// ���ÿ�ʼ�Ƕ�
		plot.setStartAngle(150D);
		// ���÷���Ϊ"˳ʱ�뷽��"
		plot.setDirection(Rotation.CLOCKWISE);
		// ����͸���ȣ�0.5FΪ��͸����1Ϊ��͸����0Ϊȫ͸��
		plot.setForegroundAlpha(1F);
		// û�����ݵ�ʱ����ʾ������
		plot.setNoDataMessage("��������ʾ");
		// ����ɫ����
		plot.setBackgroundPaint(ChartColor.WHITE);
		plot.setLabelLinkStyle(PieLabelLinkStyle.STANDARD);
		try {
			ChartUtilities.saveChartAsPNG(new File("G:\\git\\cseiii\\src\\main\\webapp\\images\\"+gid+"teampie.jpg"),
					chart, 500,400);
		} catch (IOException e) {
			e.printStackTrace();
		}
		plot.setExplodePercent(sid, 0.2);
		try {
			ChartUtilities.saveChartAsPNG(new File("G:\\git\\cseiii\\src\\main\\webapp\\images\\"+sid+"teampie.jpg"),
					chart, 500,400);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    public void createLineChart(String sid){
    	DefaultCategoryDataset dataset = recordDao.linedata(sid);  
        JFreeChart chart = ChartFactory.createLineChart("�������ɼ�����ͼ ", "����", "�ɼ�",  
                dataset, PlotOrientation.VERTICAL, true, true, true);  
  
        CategoryPlot plot = chart.getCategoryPlot(); 
        LineAndShapeRenderer lasp = (LineAndShapeRenderer) plot.getRenderer();
        lasp.setSeriesStroke(0, new BasicStroke(3F));
        lasp.setSeriesStroke(1, new BasicStroke(3F));
        lasp.setSeriesStroke(2, new BasicStroke(3F));
        lasp.setSeriesStroke(3, new BasicStroke(3F));
        plot.setBackgroundAlpha(0.5f);
		chart.setBackgroundPaint(java.awt.Color.white);
        CategoryAxis domainAxis = plot.getDomainAxis();  
        ValueAxis rAxis = plot.getRangeAxis();  
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,  
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);  
        TextTitle textTitle = chart.getTitle();  
        textTitle.setFont(new Font("����", 0, 20));  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        domainAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
        rAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        rAxis.setLabelFont(new Font("����", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));
        rAxis.setUpperBound(10.0);
        rAxis.setLowerBound(8.0);
  
        try {  
            ChartUtilities.saveChartAsPNG(new File("G:\\git\\cseiii\\src\\main\\webapp\\images\\"+sid+"score.jpg"),  
                    chart, 1000, 400);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
    }

}
