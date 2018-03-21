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
                "文档提交统计", // 图表标题  
                "类型", // 目录轴的显示标签--横轴  
                "次数", // 数值轴的显示标签--纵轴  
                dataset, // 数据集  
                PlotOrientation.VERTICAL, // 图表方向：水平、  
                true, // 是否显示图例(对于简单的柱状图必须  
                false, // 是否生成工具  
                false // 是否生成URL链接  
                ); 
		CategoryPlot plot = chart.getCategoryPlot(); 
		chart.setBackgroundPaint(java.awt.Color.white);
        CategoryAxis domainAxis = plot.getDomainAxis();  
        ValueAxis rAxis = plot.getRangeAxis();  
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,  
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);  
        TextTitle textTitle = chart.getTitle();  
        textTitle.setFont(new Font("宋体", 0, 20));  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
        rAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        rAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));  
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
                "团队文档提交统计图", // 图表标题  
                "团队成员", // 目录轴的显示标签--横轴  
                "次数", // 数值轴的显示标签--纵轴  
                dataset, // 数据集  
                PlotOrientation.VERTICAL, // 图表方向：水平、  
                true, // 是否显示图例(对于简单的柱状图必须  
                false, // 是否生成工具  
                false // 是否生成URL链接  
                ); 
		CategoryPlot plot = chart.getCategoryPlot();   
        CategoryAxis domainAxis = plot.getDomainAxis();  
        ValueAxis rAxis = plot.getRangeAxis();  
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,  
                RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);  
        TextTitle textTitle = chart.getTitle();  
        textTitle.setFont(new Font("宋体", 0, 20));  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
        rAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        rAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));  
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
		JFreeChart chart = ChartFactory.createPieChart("团队文档贡献分析", dataset,
				true, true, true);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("宋体", 0, 12));
		chart.getTitle().setFont(new Font("黑体", 0, 20));
		chart.getLegend().setItemFont(new Font("宋体", 0, 12));
		// 设置开始角度
		plot.setStartAngle(150D);
		// 设置方向为"顺时针方向"
		plot.setDirection(Rotation.CLOCKWISE);
		// 设置透明度，0.5F为半透明，1为不透明，0为全透明
		plot.setForegroundAlpha(1F);
		// 没有数据的时候显示的内容
		plot.setNoDataMessage("无数据显示");
		// 背景色设置
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
        JFreeChart chart = ChartFactory.createLineChart("各迭代成绩折线图 ", "周期", "成绩",  
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
        textTitle.setFont(new Font("宋体", 0, 20));  
        domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
        rAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
        rAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
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
