package cn.edu.hsu.wanbeibookcitymanagementsystem.dao;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author: yangjinlong
 * @date: 2018/12/27 09:35
 * @description:
 */
@Data
@Accessors(chain = true)
@Table(name = "t_book")
public class BookDO {
    @Id
    private Integer id;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "username")
    private String username;

    @Column(name = "data")
    private String data;

    @Column(name = "author")
    private String author;

    @Column(name = "message")
    private String message;

    @Column(name = "price")
    private String price;

    @Column(name = "mouth_sales")
    private String mouthSales;

    @Column(name = "all_sales")
    private String allSales;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "delete_time")
    private Date deleteTime;

}
