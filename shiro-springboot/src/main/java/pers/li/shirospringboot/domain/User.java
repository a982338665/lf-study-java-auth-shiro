package pers.li.shirospringboot.domain;

/**
 * create by lishengbo on 2018-07-06 17:37
 */
public class User {

    private Integer id;

    private String name;

    private String pass;
    private String perm;

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
