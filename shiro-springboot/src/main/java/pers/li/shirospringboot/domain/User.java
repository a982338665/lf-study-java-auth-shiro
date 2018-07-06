package pers.li.shirospringboot.domain;

/**
 * create by lishengbo on 2018-07-06 17:37
 */
public class User {

    private Integer id;

    private String name;

    private String pass;

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
