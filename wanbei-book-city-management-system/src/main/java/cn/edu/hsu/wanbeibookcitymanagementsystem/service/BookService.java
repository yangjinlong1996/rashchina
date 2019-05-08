package cn.edu.hsu.wanbeibookcitymanagementsystem.service;

import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.BookDTO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.PageUtil;

public interface BookService {

    /**
     * 管理端查看书籍
     * @param pageNum 页数
     * @param pageSize 条数
     * @param str 搜索条件
     * @return
     */
     PageUtil<BookDTO> showAdBooks(Integer pageNum,Integer pageSize,String str);
}
