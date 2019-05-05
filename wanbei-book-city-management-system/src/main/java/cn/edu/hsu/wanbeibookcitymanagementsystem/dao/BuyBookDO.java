package cn.edu.hsu.wanbeibookcitymanagementsystem.dao;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 * @date 2019/04/18 20:03:46
 * @description 
 */

@Data
@Accessors(chain = true)
@Table(name="buy_book")
public class BuyBookDO {

	@ApiModelProperty("自增主键")
	@Column(name = "id")
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	@ApiModelProperty("书籍id")
	@Column(name = "book_id")
	private Integer bookId;

	@ApiModelProperty("用户id")
	@Column(name = "user_id")
	private String userId;

	@ApiModelProperty("0未知状态 1待支付 2待商家确认 3待发货 4待收货 5交易成功 6待评价 7评价完成")
	@Column(name = "status")
	private Integer status;

	@ApiModelProperty("0未删除 1已删除")
	@Column(name = "is_delete")
	private Integer isDelete;

	@ApiModelProperty("快递名")
	@Column(name = "courier_name")
	private String courierName;

	@ApiModelProperty("快递单号")
	@Column(name = "courier_number")
	private String courierNumber;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;

	@Column(name = "delete_time")
	private Date deleteTime;

}
