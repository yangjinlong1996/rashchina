package cn.edu.hsu.wanbeibookcitymanagementsystem.mapper;

import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.UserDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: yangjinlong
 * @date: 2018/12/12 14:16
 * @description:
 */
@Repository
public interface UserMapper extends Mapper<UserDO> {
}
