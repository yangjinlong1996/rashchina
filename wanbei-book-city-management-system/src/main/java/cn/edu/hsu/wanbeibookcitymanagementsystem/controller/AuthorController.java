package cn.edu.hsu.wanbeibookcitymanagementsystem.controller;

import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.UserDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.mapper.UserMapper;
import cn.edu.hsu.wanbeibookcitymanagementsystem.service.UserService;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.PageUtil;
import io.swagger.models.auth.In;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 开发人员Controller
 */
@Log
@Controller
public class AuthorController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    /**
     * 跳转到开发人员端首页
     * @return
     */
    @RequestMapping("/author")
    public String welcome() {
        log.info("管理员开始登录");
        return "admin/author";
    }

    /**
     * 开发人员端展示用户信息
     */
    @RequestMapping("/showAuUser")
    @ResponseBody
    public PageUtil showAdPay(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                              @RequestParam(value = "search",required = false) String search){
        log.info("开发人员开始统计用户信息--->search = "+search+" 页数="+pageNum);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PageUtil<UserDO> userDOList = new PageUtil<>();
        if(StringUtil.isNotEmpty(search)){
            userDOList =  userService.showAuUser(pageNum,pageSize,search);
        }else{
            userDOList = userService.showAuUser(pageNum,pageSize,"");
        }
        if(userDOList.getTotal() > 0){
            userDOList.getList().stream().forEach(list->{
                list.setData(formatter.format(list.getCreateTime()));
            });
        }
        return userDOList;
    }

    /**
     * 开发人员端展示用户信息
     */
    @RequestMapping("/deleteUserAu")
    @ResponseBody
    public String deleteUserAu(@RequestParam(value = "id") String id,
                                 @RequestParam(value = "enable") Integer enable) {
        UserDO userDO = userMapper.selectOne(new UserDO().setId(Integer.parseInt(id)));
        if (null == userDO) {
            return "编号查询的数据不存在";
        }
        if (enable == 1) {
            if (userDO.getIsDelete() == 1) {
                return "此账户已经是禁用状态";
            }
            userMapper.updateByPrimaryKey(userDO.setIsDelete(1));
            return "禁用成功！";
        } else {
            if (userDO.getIsDelete() == 0) {
                return "此账户已经是启用状态";
            }
            userMapper.updateByPrimaryKey(userDO.setIsDelete(0));
            return "启用成功！";
        }
    }

}
