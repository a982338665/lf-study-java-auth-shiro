package pers.li.common;

/**
 * create by lishengbo on 2018-06-01 14:56
 */
public class AuthResult {

    private Integer code;

    private String message;



    public AuthResult(AuthCode auth){
        code=auth.getCode();
        message=auth.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
