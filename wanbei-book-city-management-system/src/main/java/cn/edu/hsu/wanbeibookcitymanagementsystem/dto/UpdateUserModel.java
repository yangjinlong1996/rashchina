package cn.edu.hsu.wanbeibookcitymanagementsystem.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class UpdateUserModel {
    /**
     * 用户id
     */
    private String id;

    /**
     * 管理员账户
     */
    private String username;

    /**
     * 管理员秘钥
     */
    private String pwdKey;

    /**
     * 1编辑 2删除
     */
    private Integer enable;
}
