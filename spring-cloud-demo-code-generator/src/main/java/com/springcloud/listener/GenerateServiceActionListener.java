package com.springcloud.listener;

import com.springcloud.comm.Cfg;
import com.springcloud.dao.Mapper;
import com.springcloud.dao.Repository;
import com.springcloud.pojo.Pojo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.util.StringUtils;


public class GenerateServiceActionListener implements ActionListener{

	Cfg cfg = null;

	public GenerateServiceActionListener(Cfg cfg){
		this.cfg = cfg;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		Cfg.DB_URL = mainFrame.dbUrlText.getText();
//		Cfg.PROJECT_ROOT_PATH = mainFrame.projectRooPathText.getText();
//		Cfg.corePackage = mainFrame.projectNameText.getText();
//		Cfg.TABLE_NAME = mainFrame.tableNameText.getText();
//		Cfg.MODULE_EN_NAME = mainFrame.moduleEnNameText.getText();
//		Cfg.MODULE_CN_NAME = mainFrame.moduleCnNameText.getText();
//		Cfg.COLUMN_SELECT_OBJ = mainFrame.columnSelectObjText.getText();
//		System.out.println("数据库URL：" + Cfg.DB_URL);
//		System.out.println("工程路径：" + Cfg.PROJECT_ROOT_PATH);
//		System.out.println("公共包名称：" + Cfg.corePackage);
//		System.out.println("表名：" + Cfg.TABLE_NAME);
//		System.out.println("模块英文名称：" + Cfg.MODULE_EN_NAME);
//		System.out.println("模块中文名称：" + Cfg.MODULE_CN_NAME);
//		System.out.println("列查询对象：" + Cfg.COLUMN_SELECT_OBJ);
//
		if (!StringUtils.isEmpty(Cfg.tableNames)) {
			String[] tableNames = Cfg.tableNames.split(",");
			for (int i = 0; i < tableNames.length; i++) {
				//1、生成DO
				Pojo.generateDo(tableNames[i]);
				//2、生成mapper
				Mapper.generateMapper(tableNames[i]);
				//3、生成Repository与RepositoryImpl
				Repository.generateRepository(tableNames[i]);
				Repository.generateRepositoryImpl(tableNames[i]);
			}
		}

//		Date date = new Date();
//		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		mainFrame.logText.setText(fm.format(date) + "：生成完毕");
	}

}
