package cn.edu.hsu.wanbeibookcitymanagementsystem.mapper;

import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.BookDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: yangjinlong
 * @date: 2018/12/27 09:42
 * @description:
 */
@Repository
public interface BookMapper extends Mapper<BookDO> {
}
