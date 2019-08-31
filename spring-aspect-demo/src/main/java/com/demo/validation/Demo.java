package com.demo.validation;

import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.ValidationException;

/**
 * @author zhanglong
 * @description: 描述
 * @date 2019-08-3115:17
 */
public class Demo {
    @NotNull(message = "字段值不能为空")
    private String name;
    @NotNull
    private String sex;
    @Max(value = 20,message = "最大长度为20")
    private String address;
    @NotNull
    @Size(max=10,min=5,message = "字段长度要在5-10之间")
    private String fileName;
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$",message = "不满足邮箱正则表达式")
    private String email;
    @AssertTrue(message = "字段为true才能通过")
    private boolean isSave;
    @Future(message = "时间在当前时间之后才可以通过")
    private Date date;


    public static void main( String[] args ) throws ValidationException {
        ValidationEntityResult<Demo> validate = ValidationManager.context()
            .validationHandler(ValidationHandler.DEFULTVALIDATIONHANDLER).validateEntity(new Demo());
        validate.isErrorThrowExp();
        System.out.println(validate.getData());
        System.out.println(validate.getErrorMsgs());
    }
}
