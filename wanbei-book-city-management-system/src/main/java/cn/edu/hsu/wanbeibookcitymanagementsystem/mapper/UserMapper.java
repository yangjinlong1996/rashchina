package cn.edu.hsu.wanbeibookcitymanagementsystem.mapper;

import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.UserDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.PayModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author: yangjinlong
 * @date: 2018/12/12 14:16
 * @description:
 */
@Repository
public interface UserMapper extends Mapper<UserDO> {

    @Select({
            "<script>",
            "SELECT" ,
            "b.book_id,t.username,b.`status`,b.courier_name,b.courier_number,b.is_delete" ,
            "FROM" ,
            "buy_book b" ,
            "LEFT JOIN t_user t ON b.user_id = t.user_id" ,
            "LEFT JOIN t_book tb ON b.book_id = tb.id" ,
            "where b.is_delete = 0 and tb.is_delete = 0 and t.is_delete = 0",
            "<if test = 'search != null'>" ,
            "AND t.username like '%${search}%'" ,
            "</if>",
            "</script>"
    })
    @Results({
            @Result(column = "book_id", property = "bookId"),
            @Result(column = "username", property = "username"),
            @Result(column = "status", property = "status"),
            @Result(column = "courier_name", property = "courierName"),
            @Result(column = "courier_number", property = "courier_number"),
    })
    List<PayModel> showAdPay(@Param("search") String search);
}
