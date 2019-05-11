package cn.edu.hsu.wanbeibookcitymanagementsystem.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class UpdateBookModel {
    /**
     * 书号id
     */
    private String bookId;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 作者
     */
    private String bookAuthor;

    /**
     * 书籍简要概要
     */
    private String bookMessage;

    /**
     * 价格
     */
    private String bookPrice;

    /**
     * 月销量
     */
    private String mouthSales;

    /**
     * 总销量
     */
    private String allSales;

    /**
     * 具体到某个用户
     */
    private String username;

    /**
     * 1编辑 2新增
     */
    private Integer enable;

}
