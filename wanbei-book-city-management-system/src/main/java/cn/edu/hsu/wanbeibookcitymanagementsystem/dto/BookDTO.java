package cn.edu.hsu.wanbeibookcitymanagementsystem.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;

/**
 * @author: yangjinlong
 * @date: 2018/12/27 09:35
 * @description:
 */
@Data
@Accessors(chain = true)
public class BookDTO {

    /**
     * 书号id
     */
    private Integer id;

    /**
     * 书名
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 用户账号
     */
    @Column(name = "username")
    private String username;

    /**
     * 作者
     */
    @Column(name = "author")
    private String author;

    /**
     * 书籍简要概要
     */
    @Column(name = "message")
    private String message;

    /**
     * 价格
     */
    @Column(name = "price")
    private String price;

    /**
     * 月销量
     */
    @Column(name = "mouth_sales")
    private String mouthSales;

    /**
     * 总销量
     */
    @Column(name = "all_sales")
    private String allSales;

    /**
     * 注释信息
     */
    @Column(name = "data")
    private String data;
}
