package cn.edu.hsu.wanbeibookcitymanagementsystem.dao;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: yangjinlong
 * @date: 2018/12/12 13:16
 * @description:
 */
@Data
@Accessors(chain = true)
@Table(name = "t_user")
public class UserDO {

    @Id
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    public static final String FINAL_USERNAME = "username";
    @Column(name = "username")
    private String username;

    public static final String FINAL_PASSWORD = "password";
    @Column(name = "password")
    private String password;

    public static final String FINAL_PWD_KEY = "pwd_key";
    @Column(name = "pwd_key")
    private String pwdKey;

    @Column(name = "data")
    private String data;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "delete_time")
    private Date deleteTime;

    @Column(name = "people")
    private Integer people;


}
