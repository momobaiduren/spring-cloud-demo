package com.springcloud.listener;

import com.springcloud.comm.Cfg;
import com.springcloud.controller.Controller;
import com.springcloud.dao.Mapper;
import com.springcloud.dao.Repository;
import com.springcloud.pojo.Pojo;
import com.springcloud.service.Converter;
import com.springcloud.service.Request;
import com.springcloud.service.Response;
import com.springcloud.service.Service;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.springframework.util.StringUtils;


public class GenerateAllActionListener implements ActionListener {


    public GenerateAllActionListener() {

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
                //4、生成request与response
                Request.generateRequest(tableNames[i]);
                Response.generateResponse(tableNames[i]);
                //5、生成Converter
                Converter.generateConverter(tableNames[i]);
                //6、生成service与serviceImpl
                Service.generateService(tableNames[i]);
                Service.generateServiceImpl(tableNames[i]);
                //7、生成枚举类
                com.springcloud.service.Enum.generateEnum(tableNames[i]);
                //8、生成Controller
                Controller.generateController(tableNames[i]);
            }
        }

//		//2、生成mapper
//		Mapper.generateMapper();
//		//3、生成Repository与RepositoryImpl
//		Repository.generateRepository();
//		Repository.generateRepositoryImpl();
//		//4、生成request与response
//		Request.generateRequest();
//		Response.generateResponse();
//		//5、生成Converter
//		Converter.generateConverter();
//		//6、生成service与serviceImpl
//		Service.generateService();
//		Service.generateServiceImpl();
//		//7、生成枚举类
//		com.springcloud.service.Enum.generateEnum();
//		//8、生成Controller
//		Controller.generateController();
//
//		Date date = new Date();
//		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

}
