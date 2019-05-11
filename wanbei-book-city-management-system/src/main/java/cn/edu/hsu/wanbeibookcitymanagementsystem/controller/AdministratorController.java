package cn.edu.hsu.wanbeibookcitymanagementsystem.controller;

import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.UpdateBookModel;
import cn.edu.hsu.wanbeibookcitymanagementsystem.service.BookService;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.PageUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.util.StringUtil;

/**
 * @author: yangjinlong
 * @date: 2018/12/20 14:48
 * @description:管理员模块
 */
@Log
@Controller
@RequestMapping(value = "/administrator")
public class AdministratorController {

    @Autowired
    BookService bookService;


    /**
     * 跳转到管理员端首页
     * @return
     */
    @RequestMapping("/ad")
    public String welcome() {
        log.info("管理员开始登录");
        return "admin/welcome";
    }

    /**
     * 用户管理端展示书籍
     */
    @RequestMapping("/showAdBooks")
    @ResponseBody
    public PageUtil showAdBooks(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                @RequestParam(value = "search",required = false) String search){
        log.info("开始统计书籍--->search = "+search+" 页数="+pageNum);
        if(StringUtil.isNotEmpty(search)){
           return bookService.showAdBooks(pageNum,pageSize,search);
        }else{
          return bookService.showAdBooks(pageNum,pageSize,"");
        }
    }

    /**
     * 用户管理端编辑,新增书籍
     */
    @RequestMapping("/updateBook")
    @ResponseBody
    public String update(@RequestBody UpdateBookModel updateBookModel){
        log.info("入参打印---->"+ JSON.toJSON(updateBookModel));
        if(updateBookModel.getEnable() == 1){
            if(StringUtils.isBlank(updateBookModel.getBookId())){
                return "书号不允许为空,请输入后重新提交！";
            }
            Integer rows = bookService.update(updateBookModel);
            if(rows == null){
                return "该书号无法找到,请重新输入！";
            }
            return "编辑成功！";
        }
        if(StringUtils.isBlank(updateBookModel.getBookName()) ||StringUtils.isBlank(updateBookModel.getBookAuthor())){
            return "您有必填项为空,请重新输入！";
        }
        bookService.update(updateBookModel);
        return "新增成功！";
    }


}
