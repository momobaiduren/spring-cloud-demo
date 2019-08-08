package com.springcloud.listener;

import com.springcloud.comm.Cfg;
import com.springcloud.dao.Mapper;
import com.springcloud.dao.Repository;
import com.springcloud.pojo.Pojo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.util.StringUtils;


public class GenerateDaoActionListener implements ActionListener{

	Cfg cfg = null;
	
	public GenerateDaoActionListener(Cfg cfg){
		this.cfg = cfg;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
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

	}

}
