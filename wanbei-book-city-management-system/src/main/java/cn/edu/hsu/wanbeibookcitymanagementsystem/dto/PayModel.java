package cn.edu.hsu.wanbeibookcitymanagementsystem.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class PayModel {

    private String bookId;

    private String username;

    private Integer status;

    private String courierName;

    private String courierNumber;
}
