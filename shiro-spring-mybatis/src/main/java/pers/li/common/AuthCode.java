package pers.li.common;

/**
 * create by lishengbo on 2018-06-01 14:37
 */
public enum AuthCode {

    Auth_Fail_Login(1000,"登录异常"),
    Auth_Fail_UserName(1001,"用户名有误"),
    Auth_Fail_PassWord(1002,"密码有误"),
    Auth_Successs(200,"登陆成功");

    AuthCode(int code, String message){
        this.code=code;
        this.message=message;
    }

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
