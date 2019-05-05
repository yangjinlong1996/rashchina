package cn.edu.hsu.wanbeibookcitymanagementsystem.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: yangjinlong
 * @date: 2018/12/12 14:13
 * @description:
 */
@Data
@Accessors(chain = true)
public class UserDTO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 管理员账户
     */
    private String username;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 管理员秘钥
     */
    private String pwdKey;

    /**
     * 说明内容
     */
    private String data;

    /**
     * 登录身份
     */
    private Integer people;
}
