package cn.edu.hsu.wanbeibookcitymanagementsystem.controller;

import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.UpdateBookModel;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.UpdateUserModel;
import cn.edu.hsu.wanbeibookcitymanagementsystem.service.BookService;
import cn.edu.hsu.wanbeibookcitymanagementsystem.service.UserService;
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

    @Autowired
    UserService userService;


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
     * 跳转到用户管理页
     */
    @RequestMapping("/ad_user")
    public String adUser() {
        log.info("管理员开始登录");
        return "admin/ad_user";
    }

    /**
     * 跳转到合作信息管理页
     */
    @RequestMapping("/ad_info")
    public String adInfo() {
        return "admin/ad_info";
    }

    /**
     * 跳转到订单管理页
     */
    @RequestMapping("/ad_pay")
    public String adPay() {
        return "admin/ad_pay";
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
     * 用户管理端展示合作信息
     */
    @RequestMapping("/showAdinfo")
    @ResponseBody
    public PageUtil showAdinfo(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                @RequestParam(value = "search",required = false) String search){
        log.info("开始统计用户合作信息--->search = "+search+" 页数="+pageNum);
        if(StringUtil.isNotEmpty(search)){
            return userService.showAdinfo(pageNum,pageSize,search);
        }else{
            return userService.showAdinfo(pageNum,pageSize,"");
        }
    }

    /**
     * 用户管理端展示用户信息
     */
    @RequestMapping("/showAdPay")
    @ResponseBody
    public PageUtil showAdPay(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                               @RequestParam(value = "search",required = false) String search){
        log.info("开始统计用户订单--->search = "+search+" 页数="+pageNum);
        if(StringUtil.isNotEmpty(search)){
            return userService.showAdPay(pageNum,pageSize,search);
        }else{
            return userService.showAdPay(pageNum,pageSize,null);
        }
    }

    /**
     * 用户管理端展示用户信息
     */
    @RequestMapping("/showAdUser")
    @ResponseBody
    public PageUtil showAdUser(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                               @RequestParam(value = "search",required = false) String search){
        log.info("开始统计用户信息--->search = "+search+" 页数="+pageNum);
        if(StringUtil.isNotEmpty(search)){
            return userService.showAdUser(pageNum,pageSize,search);
        }else{
            return userService.showAdUser(pageNum,pageSize,"");
        }
    }

    /**
     * 用户管理端编辑用户信息
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(@RequestBody UpdateUserModel updateUserModel){
        log.info("入参打印---->"+ JSON.toJSON(updateUserModel));
        if(updateUserModel.getEnable() == 1){
            if(StringUtils.isBlank(updateUserModel.getId())){
                return "编号不允许为空,请输入后重新提交！";
            }
            Integer rows = userService.updateUser(updateUserModel);
            if(rows == null){
                return "该编号无法找到,请重新输入！";
            }
            return "编辑成功！";
        }
        if(StringUtils.isBlank(updateUserModel.getId())){
            return "编号不允许为空,请输入后重新提交！";
        }
        Integer rows = userService.updateUser(updateUserModel);
        if(rows == null){
            return "该编号无法找到,请重新输入！";
        }
        return "禁用成功,如需恢复,请联系开发人员！";
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
