package com.springcloud.mian;

import com.springcloud.comm.Cfg;
import com.springcloud.listener.GenerateAllActionListener;
import com.springcloud.listener.GenerateDaoActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8165660216478003663L;
	static MainFrame frame = null;
	public JTextArea logText = new JTextArea();
	public JTextField  dbUrlText = new JTextField();
	public JTextField  projectRooPathText = new JTextField();
	public JTextField  projectNameText = new JTextField();
	public JTextField  tableNameText = new JTextField();
	public JTextField  moduleEnNameText = new JTextField();
	public JTextField  moduleCnNameText = new JTextField();
	public JTextField  columnSelectObjText = new JTextField();

	/**
	 * 创建Frame
	 */
	public void createFrame() {
		
		
		JLabel rooPathLable = new JLabel();
		rooPathLable.setText("工程路径：");
		JLabel dbUrlLable = new JLabel();
		dbUrlLable.setText("DB URL：   ");
		Box box1 = Box.createHorizontalBox();
		box1.add(rooPathLable);
		box1.add(Box.createHorizontalStrut(22));
		box1.add(projectRooPathText);
		box1.add(Box.createHorizontalStrut(10));
		box1.add(dbUrlLable);
		box1.add(Box.createHorizontalStrut(10));
		box1.add(dbUrlText);

		JLabel tableNameLable = new JLabel();
		tableNameLable.setText("表名：");
		JLabel projectNameLable = new JLabel();
		projectNameLable.setText("工程名：    ");
		Box box3 = Box.createHorizontalBox();
		box3.add(tableNameLable);
		box3.add(Box.createHorizontalStrut(50));
		box3.add(tableNameText);
		box3.add(Box.createHorizontalStrut(10));
		box3.add(projectNameLable);
		box3.add(Box.createHorizontalStrut(10));
		box3.add(projectNameText);
		
		JLabel moduleEnNameLable = new JLabel();
		moduleEnNameLable.setText("模块英文名：");
		JLabel moduleCnNameLable = new JLabel();
		moduleCnNameLable.setText("模块中文名：");
		Box box4 = Box.createHorizontalBox();
		box4.add(moduleEnNameLable);
		box4.add(Box.createHorizontalStrut(10));
		box4.add(moduleEnNameText);
		box4.add(Box.createHorizontalStrut(10));
		box4.add(moduleCnNameLable);;
		box4.add(Box.createHorizontalStrut(10));
		box4.add(moduleCnNameText);
		
		JLabel columnSelectObjLable = new JLabel();
		columnSelectObjLable.setText("列查询对象：");
		Box box6 = Box.createHorizontalBox();
		box6.add(columnSelectObjLable);
		box6.add(Box.createHorizontalStrut(10));
		box6.add(columnSelectObjText);

		JButton generateAllBtn = new JButton("生成全部");
		generateAllBtn.addActionListener(new GenerateAllActionListener(Cfg.init()));

		JButton generateDaoBtn = new JButton("生成DAO层");
		generateDaoBtn.addActionListener(new GenerateDaoActionListener(Cfg.init()));
		Box box5 = Box.createHorizontalBox();
		box5.add(generateAllBtn);
		box5.add(Box.createHorizontalStrut(50));
		box5.add(generateDaoBtn);
		box5.add(Box.createHorizontalStrut(750));
		
		Box boxAll = Box.createVerticalBox();
		boxAll.add(box1);
//		boxAll.add(Box.createVerticalStrut(5));
//		boxAll.add(box2);
		boxAll.add(Box.createVerticalStrut(5));
		boxAll.add(box3);
		boxAll.add(Box.createVerticalStrut(5));
		boxAll.add(box4);
		boxAll.add(Box.createVerticalStrut(5));
		boxAll.add(box6);
		boxAll.add(Box.createVerticalStrut(5));
		boxAll.add(box5);

		JPanel panel=new JPanel();    //创建面板
	    //指定面板的布局为GridLayout，2行1列，间隙为5
	    panel.setLayout(new GridLayout(2,1,5,5));
	    panel.add(boxAll);
	    panel.add(logText);
	    
		frame.add(panel, BorderLayout.CENTER);
		frame.setLocation(500, 200);
		frame.setSize(1000, 400);
		frame.setTitle("代码生成工具");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//初始化数据
		Properties pro = new Properties();
		try {
			InputStream in = Thread.currentThread().getContextClassLoader().getResource("application.properties").openStream();
			InputStreamReader reader = new InputStreamReader(in,"utf-8");
			pro.load(reader);
			in.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		dbUrlText.setText(pro.get("DB_URL").toString());
//		projectRooPathText.setText(pro.get("PROJECT_ROOT_PATH").toString());
//		projectNameText.setText(pro.get("PROJECT_NAME").toString());
//		tableNameText.setText(pro.get("TABLE_NAME").toString());
//		moduleEnNameText.setText(pro.get("MODULE_EN_NAME").toString());
//		moduleCnNameText.setText(pro.get("MODULE_CN_NAME").toString());
//		columnSelectObjText.setText(pro.get("COLUMN_SELECT_OBJ").toString());
	}
	

	public static void main(String[] args) {

		frame = new MainFrame();
		frame.createFrame();
	}
}
