package pers.li.dao.impl;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pers.li.dao.UserDao;
import pers.li.entity.User;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * create by lishengbo on 2018-05-31 13:29
 */
@Component
public class UserDaoImpl implements UserDao {


    @Resource
    private JdbcTemplate jdbcTemplate;

    public User getPassBYUserName(String userName) {

        String sql="select username,password from users where username=?";
        List<User> list = jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                return user;
            }
        });

        if(CollectionUtils.isEmpty(list)){
            return null;
        }

        return list.get(0);
    }

    public List<String> getRolesBYUserName(String userName) {
        String sql="select role_name from user_roles where username = ?";
        return jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {

                return resultSet.getString("role_name");
            }
        });
    }

    public List<String> getPermissionBYUserName(String userName) {
        String sql="select permission from roles_permissions where role_name = ?";
        return jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {

                return resultSet.getString("permission");
            }
        });
    }
}
