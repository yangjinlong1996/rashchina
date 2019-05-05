package cn.edu.hsu.wanbeibookcitymanagementsystem.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @author: yangJinLong
 * @date: 2019/4/22 14:34
 * @annotation:
 **/
@Data
@Accessors(chain = true)
public class BuyBookDTO {

    @ApiModelProperty("书籍名")
    private String bookName;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("书籍id")
    private Integer bookId;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("0未知状态 1待支付 2待商家确认 3待发货 4待收货 5交易成功 6待评价 7评价完成")
    private Integer status;

    @ApiModelProperty("快递名")
    private String courierName;

    @ApiModelProperty("快递单号")
    private String courierNumber;

    @ApiModelProperty("书籍价格")
    private String price;
}
