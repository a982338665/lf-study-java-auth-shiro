package pers.li.boot.entities;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @author : Mr huangye
 * @URL : CSDN 皇夜_
 * @createTime : 2020/7/29 10:18
 * @Description :
 */
@Data
@TableName("user")
public class UserMP {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String username;
    private String sex;
}
