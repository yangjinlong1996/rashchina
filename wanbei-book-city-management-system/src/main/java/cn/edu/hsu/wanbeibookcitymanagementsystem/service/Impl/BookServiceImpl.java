package cn.edu.hsu.wanbeibookcitymanagementsystem.service.Impl;

import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.BookDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.BookDTO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.mapper.BookMapper;
import cn.edu.hsu.wanbeibookcitymanagementsystem.service.BookService;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.ConvertUtils;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.PageUtil;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;


    @Override
    public PageUtil<BookDTO> showAdBooks(Integer pageNum, Integer pageSize, String str) {
        Example e = new Example(BookDO.class);
        Example.Criteria criteria = e.createCriteria();
        criteria.andEqualTo("isDelete",0);
        if(StringUtil.isNotEmpty(str)){
            criteria.andLike("bookName","%".concat(str).concat("%"));
        }
        List<BookDO> bookDOS = bookMapper.selectByExample(e);
        return new PageUtil<>(ConvertUtils.convert(bookDOS,BookDTO.class),pageNum,pageSize);
    }
}
