package cn.edu.hsu.wanbeibookcitymanagementsystem.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class UpdatePayModel {
    /**
     * 用户id
     */
    private String id;

    /**
     * 快递名
     */
    private String courierName;

    /**
     * 快递单号
     */
    private String courierNumber;
}
