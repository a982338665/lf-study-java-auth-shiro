package pers.li.boot.dao.common;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.li.boot.entities.User;
import pers.li.boot.entities.UserMP;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/29 9:54
 * @Description :
 */
@Mapper
public interface UserMPDao extends BaseMapper<UserMP> {

}
