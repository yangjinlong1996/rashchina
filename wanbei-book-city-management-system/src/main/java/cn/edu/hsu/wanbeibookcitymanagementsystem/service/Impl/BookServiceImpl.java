package cn.edu.hsu.wanbeibookcitymanagementsystem.service.Impl;

import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.BookDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.BookDTO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.UpdateBookModel;
import cn.edu.hsu.wanbeibookcitymanagementsystem.mapper.BookMapper;
import cn.edu.hsu.wanbeibookcitymanagementsystem.service.BookService;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.ConvertUtils;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.PageUtil;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;

@Log
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
            log.info("按书名查询");
            criteria.andLike("bookName","%".concat(str).concat("%"));
        }
        List<BookDO> bookDOS = bookMapper.selectByExample(e);
        return new PageUtil<>(ConvertUtils.convert(bookDOS,BookDTO.class),pageNum,pageSize);
    }

    @Override
    public Integer update(UpdateBookModel updateBookModel) {
        if(updateBookModel.getEnable() == 1){
            Example e = new Example(BookDO.class);
            e.createCriteria().andEqualTo("id",updateBookModel.getBookId());
            BookDO bookDO = bookMapper.selectOne(new BookDO().setIsDelete(0)
                    .setId(Integer.parseInt(updateBookModel.getBookId())));
            if(bookDO == null){
                return null;
            }
            if(StringUtils.isNotBlank(updateBookModel.getBookName())){
                bookDO.setBookName(updateBookModel.getBookName());
            }
            if(StringUtils.isNotBlank(updateBookModel.getBookAuthor())){
                bookDO.setAuthor(updateBookModel.getBookAuthor());
            }
            if(StringUtils.isNotBlank(updateBookModel.getBookPrice())){
                bookDO.setPrice(updateBookModel.getBookPrice());
            }
            if(StringUtils.isNotBlank(updateBookModel.getBookMessage())){
                bookDO.setMessage(updateBookModel.getBookMessage());
            }
            bookDO.setUpdateTime(new Date());
            return bookMapper.updateByExampleSelective(bookDO,e);
        }
        return bookMapper.insertSelective(new BookDO()
                .setIsDelete(0).setCreateTime(new Date())
                .setBookName(updateBookModel.getBookName())
                .setAuthor(updateBookModel.getBookAuthor())
                .setMessage(updateBookModel.getBookMessage())
                .setPrice(updateBookModel.getBookPrice())
                .setMouthSales(updateBookModel.getMouthSales())
                .setUsername(updateBookModel.getUsername())
                .setAllSales(updateBookModel.getAllSales()));
    }
}
