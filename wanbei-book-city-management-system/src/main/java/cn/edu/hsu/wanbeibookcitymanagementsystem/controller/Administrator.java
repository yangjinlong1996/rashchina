package cn.edu.hsu.wanbeibookcitymanagementsystem.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yangjinlong
 * @date: 2018/12/20 14:48
 * @description:管理员模块
 */
@Log
@Controller
@RequestMapping(value = "/administrator")
public class Administrator {


    /**
     * 跳转到管理员端首页
     * @return
     */
    @RequestMapping("/ad")
    public String welcome() {
        log.info("管理员开始登录");
        return "admin/welcome";
    }

}
