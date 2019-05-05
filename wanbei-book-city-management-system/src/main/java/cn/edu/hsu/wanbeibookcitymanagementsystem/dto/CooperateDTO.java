package cn.edu.hsu.wanbeibookcitymanagementsystem.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author: yangjinlong
 * @date: 2018/12/26 15:13
 * @description:
 */
@Data
@Accessors(chain = true)
public class CooperateDTO {

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 注释信息
     */
    @Column(name = "data")
    private String data;

    public static final String FINAL_USERNAME = "username";
    @Column(name = "username")
    private String username;

    /**
     * 返回的message
     */
    @Column(name = "message")
    private String message;

    /**
     * 合作的详细信息
     */
    @Column(name = "information")
    private String information;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 联系方式
     */
    @Column(name = "contact_way")
    private String contactWay;

    /**
     * 合作类型
     */
    @Column(name = "type")
    private Integer type;


}
