package cn.edu.hsu.wanbeibookcitymanagementsystem.dao;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: yangjinlong
 * @date: 2019/1/21 13:23
 * @description:
 */
@Data
@Accessors(chain = true)
@Table(name = "t_like_book")
public class LikeBookDO {
    @Id
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "username")
    private String username;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "delete_time")
    private Date deleteTime;
}
