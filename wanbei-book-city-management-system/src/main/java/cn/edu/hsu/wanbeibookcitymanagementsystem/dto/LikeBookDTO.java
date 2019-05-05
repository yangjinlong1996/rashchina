package cn.edu.hsu.wanbeibookcitymanagementsystem.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;

/**
 * @author: yangjinlong
 * @date: 2019/1/21 13:24
 * @description:
 */
@Data
@Accessors(chain = true)
public class LikeBookDTO {
    /**
     * 书名
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 书籍id
     */
    private Integer bookId;

    /**
     * 用户账号
     */
    @Column(name = "username")
    private String username;
}
