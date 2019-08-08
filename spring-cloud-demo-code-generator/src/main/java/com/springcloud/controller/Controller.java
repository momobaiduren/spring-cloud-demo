package com.springcloud.controller;

import com.springcloud.comm.Cfg;
import com.springcloud.utils.FileUtils;
import com.springcloud.utils.Utils;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    /**
     * 生成Controller
     * @param tableName
     */
    public static List<String> generateController(String tableName) {
        List<String> retList = new ArrayList<String>();
        retList.add("package " + Cfg.controllerPackage + ";");
        retList.add("\r");
        retList.add("import com.yh.csx.bsf.core.base.model.Keyvalue;");
        retList.add("import com.yh.csx.bsf.core.base.model.PageResponse;");
        retList.add("import com.yh.csx.bsf.core.base.wrapper.WrapMapper;");
        retList.add("import com.yh.csx.bsf.core.base.wrapper.Wrapper;");
        retList.add("import io.swagger.annotations.Api;");
        retList.add("import io.swagger.annotations.ApiOperation;");
        retList.add("import org.springframework.beans.factory.annotation.Autowired;");
        retList.add("import org.springframework.http.MediaType;");
        retList.add("import org.springframework.web.bind.annotation.*;");
        retList.add("import java.util.List;");
        retList.add("import java.util.ArrayList;");
        retList.add(
            "import " + Cfg.corePackage + ".service.I" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
                + "Service;");
        retList.add(
            "import " + Cfg.corePackage + ".service.response." + Cfg.MODULE_EN_NAME + "." + Utils
                .getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Response;");
        retList.add(
            "import " + Cfg.corePackage + ".service.request." + Cfg.MODULE_EN_NAME + "." + Utils
                .getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Request;");
        //引入枚举类型包
        List<com.springcloud.service.Enum> enumList = Utils.enumList;
        for (com.springcloud.service.Enum enum1 : enumList) {
            retList.add("import " + Cfg.corePackage + ".service.enums." + Utils
                .getFirstUpperCase(enum1.getEnName()) + "Enum;");
        }
        retList.add("\r");
        retList.add("/**" + Cfg.MODULE_CN_NAME + "DO");
        retList.add("  * @author " + Cfg.author);
        retList.add("  * @version " + Cfg.version);
        retList.add("  * @description ");
        retList.add("  * @date " + LocalDateTime.now());
        retList.add("  */");
        retList.add("\r");

        retList.add("@Api(\"" + Cfg.MODULE_CN_NAME + "\")");
        retList.add("@RestController");
        retList.add("@RequestMapping(value = \"/" + Cfg.corePackage.replace(".", "/")
            + "\", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)");
        retList.add("public class " + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Controller {");
        retList.add("\r");
        retList.add("	@Autowired");
        retList.add("	private I" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Service "
            + Cfg.MODULE_EN_NAME + "Service;");
        retList.add("\r");
        retList.add("	@ApiOperation(\"创建" + Cfg.MODULE_CN_NAME + "\")");
        retList.add("	@PostMapping(value = \"/" + Cfg.MODULE_EN_NAME + "\")");
        retList.add(
            "	public Wrapper<" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Response> create"
                + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "(@RequestBody " + Utils
                .getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Request request){");
        retList.add(
            "		" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Response response = "
                + Cfg.MODULE_EN_NAME + "Service.create" + Utils
                .getFirstUpperCase(Cfg.MODULE_EN_NAME) + "(request);");
        retList.add("		return WrapMapper.ok(response);");
        retList.add("	}");
        retList.add("\r");
        retList.add("	@ApiOperation(\"" + Cfg.MODULE_CN_NAME + "列表\")");
        retList.add("	@GetMapping(value = \"/" + Cfg.MODULE_EN_NAME + "\")");
        retList.add("	public Wrapper<PageResponse<" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
            + "Response>> loadPage(" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
            + "Request request){");
        retList.add("		PageResponse<" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
            + "Response> response = " + Cfg.MODULE_EN_NAME + "Service.loadPage(request);");
        retList.add("		return WrapMapper.ok(response);");
        retList.add("	}");
        retList.add("\r");
        retList.add("	@ApiOperation(\"更改" + Cfg.MODULE_CN_NAME + "\")");
        retList.add("	@PutMapping(value = \"/" + Cfg.MODULE_EN_NAME + "/{id}\")");
        retList.add(
            "	public Wrapper<" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Response> update"
                + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "(@RequestBody " + Utils
                .getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Request request){");
        retList.add(
            "		" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Response response = "
                + Cfg.MODULE_EN_NAME + "Service.update" + Utils
                .getFirstUpperCase(Cfg.MODULE_EN_NAME) + "(request);");
        retList.add("		return WrapMapper.ok(response);");
        retList.add("	}");
        retList.add("\r");
        retList.add("	@ApiOperation(\"删除" + Cfg.MODULE_CN_NAME + "\")");
        retList.add("	@DeleteMapping(value = \"/" + Cfg.MODULE_EN_NAME + "/{id}\")");
        retList.add("	public Wrapper delete" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
            + "(@PathVariable(\"id\") String id){");
        retList.add("		" + Cfg.MODULE_EN_NAME + "Service.delete" + Utils
            .getFirstUpperCase(Cfg.MODULE_EN_NAME) + "(id);");
        retList.add("		return WrapMapper.ok();");
        retList.add("	}");
        retList.add("\r");
        retList.add("	@ApiOperation(\"根据ID获取" + Cfg.MODULE_CN_NAME + "信息\")");
        retList.add("	@GetMapping(value = \"/" + Cfg.MODULE_EN_NAME + "/{id}\")");
        retList.add(
            "	public Wrapper<" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Response> "
                + Cfg.MODULE_EN_NAME + "Info(@PathVariable(\"id\") String id){");
        retList.add("		" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Response "
            + Cfg.MODULE_EN_NAME + " = " + Cfg.MODULE_EN_NAME + "Service." + Cfg.MODULE_EN_NAME
            + "Info(id);");
        retList.add("		return WrapMapper.ok(" + Cfg.MODULE_EN_NAME + ");");
        retList.add("	}");
        retList.add("\r");
        retList.add("	@ApiOperation(\"获取所有" + Cfg.MODULE_CN_NAME + "\")");
        retList.add("	@GetMapping(value = \"/" + Cfg.MODULE_EN_NAME + "/all\")");
        retList.add("	public Wrapper<List<" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
            + "Response>> all(){");
        retList.add("		return WrapMapper.ok(" + Cfg.MODULE_EN_NAME + "Service.all());");
        retList.add("	}");
        //根据列查询对象
        if (!Cfg.COLUMN_SELECT_OBJ.trim().equals("")) {
            String[] arrys = Cfg.COLUMN_SELECT_OBJ.split(";");
            for (int i = 0; i < arrys.length; i++) {
                String[] arrysI = arrys[i].split(",");
                retList.add("\r");
                retList.add("	@ApiOperation(\"根据" + arrysI[1] + "获取详情\")");
                retList.add("	@GetMapping(value = \"/" + Cfg.MODULE_EN_NAME + "/get" + Utils
                    .getFirstUpperCase(Cfg.MODULE_EN_NAME) + "InfoBy" + Utils
                    .getFirstUpperCase(Utils.getTuoFeng(arrysI[0])) + "/{" + Utils
                    .getTuoFeng(arrysI[0]) + "}\")");
                retList.add("	public Wrapper<" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME)
                    + "Response> get" + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "InfoBy"
                    + Utils.getFirstUpperCase(Utils.getTuoFeng(arrysI[0])) + "(@PathVariable(\""
                    + Utils.getTuoFeng(arrysI[0]) + "\") String " + Utils.getTuoFeng(arrysI[0])
                    + "){");
                retList.add(
                    "		return WrapMapper.ok(" + Cfg.MODULE_EN_NAME + "Service.get" + Utils
                        .getFirstUpperCase(Cfg.MODULE_EN_NAME) + "InfoBy" + Utils
                        .getFirstUpperCase(Utils.getTuoFeng(arrysI[0])) + "(" + Utils
                        .getTuoFeng(arrysI[0]) + "));");
                retList.add("	}");
            }
        }
        retList.add("\r");
        //生成枚举服务
        for (com.springcloud.service.Enum enum1 : enumList) {
            retList.add("	@ApiOperation(\"获取枚举" + enum1.getCnName() + "\")");
            retList.add("	@GetMapping(value = \"/" + Cfg.MODULE_EN_NAME + "/" + enum1.getEnName()
                + "\")");
            retList.add("	public Wrapper<List<Keyvalue>> " + enum1.getEnName() + "(){");
            retList.add("		List<Keyvalue> keyvalues = new ArrayList<>();");
            retList.add(
                "		for(" + Utils.getFirstUpperCase(enum1.getEnName()) + "Enum item : " + Utils
                    .getFirstUpperCase(enum1.getEnName()) + "Enum.values()){");
            retList.add(
                "			keyvalues.add(new Keyvalue(String.valueOf(item.value()),String.valueOf(item.desc())));");
            retList.add("		}");
            retList.add("		return WrapMapper.ok(keyvalues);");
            retList.add("	}");
            retList.add("\r");
        }
        retList.add("}");

        //创建文件夹
        StringBuffer filePath = new StringBuffer(Cfg.PROJECT_ROOT_PATH).append("/")
            .append(Cfg.controllerProjectName.replace(".", "/")).append("/src/main/java/")
            .append(Cfg.controllerPackage.replace(".", "/")).append("/");
//		+ "/csx-b2b-"+Cfg.PROJECT_NAME+"-provider";
//		filePath += "/csx-b2b-"+Cfg.PROJECT_NAME+"-provider-ba/src/main/java/com/yh/csx/"+Cfg.PROJECT_NAME+"/controller/";
        File file = new File(filePath.toString());
        if (!file.exists()) {
            file.mkdir();
        }
        FileUtils
            .writeFile(filePath + Utils.getFirstUpperCase(Cfg.MODULE_EN_NAME) + "Controller.java",
                retList);

        for (String s : retList) {
            System.out.println(s);
        }
        return retList;
    }


}
