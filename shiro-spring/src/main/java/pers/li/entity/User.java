package pers.li.entity;

/**
 * create by lishengbo on 2018-05-31 11:38
 *
 * 接收认证信息
 */
public class User {

    private String username;

    private String password;

    private boolean rememberMe;

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
