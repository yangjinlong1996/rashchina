package cn.edu.hsu.wanbeibookcitymanagementsystem.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: yangjinlong
 * @date: 2018/12/26 14:47
 * @description:
 */
@Data
@Accessors(chain = true)
@Table(name = "t_cooperate")
public class CooperateDO {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "data")
    private String data;

    public static final String FINAL_USERNAME = "username";
    @Column(name = "username")
    private String username;

    @Column(name = "information")
    private String information;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_way")
    private String contactWay;

    @Column(name = "type")
    private Integer type;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "delete_time")
    private Date deleteTime;
}
