package cn.edu.hsu.wanbeibookcitymanagementsystem.controller;

import cn.edu.hsu.wanbeibookcitymanagementsystem.service.BookService;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.PageUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
     * 用户管理端管理书籍
     */
    @RequestMapping("/showAdBooks")
    @ResponseBody
    public PageUtil showAdBooks(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageNum",defaultValue = "10") Integer pageSize,
                                @RequestParam(value = "pageSize",required = false) String search){
        log.info("开始统计书籍");
        if(StringUtil.isNotEmpty(search)){
           return bookService.showAdBooks(pageNum,pageSize,"");
        }else{
          return bookService.showAdBooks(pageNum,pageSize,search);
        }
    }


}
