package pers.li.boot.dao.test2;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.li.boot.entities.User;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/29 9:54
 * @Description :
 */
@Mapper
public interface User2Dao {
    @Select("select * from user where id = #{id}")
    User selectUserById(@Param("id") Integer id);

    @Insert("INSERT INTO user (`id`, `username`, `sex`) VALUES (NULL, #{name}, #{sex});")
    int insert(@Param("name") String name, @Param("sex") String sex);


}
