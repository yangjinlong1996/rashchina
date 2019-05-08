package cn.edu.hsu.wanbeibookcitymanagementsystem.controller;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.UserDTO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.service.UserService;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.ConvertUtils;
import com.mysql.cj.util.StringUtils;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import static cn.edu.hsu.wanbeibookcitymanagementsystem.dao.UserDO.FINAL_PASSWORD;
import static cn.edu.hsu.wanbeibookcitymanagementsystem.dao.UserDO.FINAL_USERNAME;

/**
 * @author: yangjinlong
 * @date: 2018/12/20 14:48
 * @description:登录模块
 */
@Log
@Controller
public class LoginViewController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @RequestMapping("/index")
    public String test(){
        return "login";
    }

    @RequestMapping("/reg")
    public String reg(){
        return "reg";
    }

    /**
     * 注册校验
     * @param request
     * @return
     */
    @RequestMapping("/regcheck")
    @ResponseBody
    public String regCheck(HttpServletRequest request){
        UserDTO userDTO = new UserDTO();
        try{
            String username = request.getParameter(FINAL_USERNAME);
            String password = request.getParameter(FINAL_PASSWORD);
            String truePassword = request.getParameter("truePassword");
            String pwdKey = request.getParameter("pwdKey");
            String truePwdKey = request.getParameter("truePwdKey");
            String message;
            if(StringUtils.isNullOrEmpty(username)||StringUtils.isNullOrEmpty(password)||StringUtils.isNullOrEmpty(truePassword)
                    ||StringUtils.isNullOrEmpty(pwdKey)||StringUtils.isNullOrEmpty(truePwdKey)){
                  message  = "系统检测到您有必填项没有填写或填写的是空字符串,请修改后重试";
                  return message;
            }else if(username.length()<8||username.length()>16){
                  message  = "请控制您的账号在8-16个字符之间";
                  return message;
            }else if(password.length()<8||password.length()>16){
                  message  = "请控制您的密码在8-16个字符之间";
                  return message;
            }else if(!password.equals(truePassword)){
                message = "密码与确认密码不一致,请返回重新填写";
                return message;
            }else if(!pwdKey.equals(truePwdKey)){
                message = "密钥与确认密钥不一致,请返回重新填写";
                return message;
            }else{
                userDTO =  ConvertUtils.convert(userService.reg(username,password,pwdKey),UserDTO.class);
                message = userDTO.getData();
                if("该用户名已经被注册,请换一个再试。".equals(message)){
                    return message;
                }else{
                    message = "注册成功！";
                    return message;
                }
            }
        }catch (Exception e){
            String message = "系统异常,请稍后重试";
            e.printStackTrace();
            return message;
        }
    }

    /**
     * 登录校验
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request){
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String pwdKey = request.getParameter("pwdKey");
            String people = request.getParameter("people");
            String message;
            if(StringUtils.isNullOrEmpty(people)){
                message = "您还没有选择登录身份,请返回选择！";
                return message;
            }else if("4".equals(people)){
                message = "尊敬的游客,欢迎您！";
                return message;
            }
            UserDTO userDTO = ConvertUtils.convert(userService.userLogin(username,password,pwdKey,Integer.parseInt(people)),UserDTO.class);
            message = userDTO.getData();
            if (null == message) {
                request.getSession().setAttribute("loginName", username);
                request.getSession().setAttribute("userId", userDTO.getUserId());
                log.info("将用户的userId用session存储-------->"+userDTO.getUserId());
                if("1".equals(people)){
                    message = "登录成功!";
                }else if("2".equals(people)){
                    message = "尊敬的管理员,欢迎您！";
                }else if("3".equals(people)){
                    message = "尊敬的开发者,欢迎您！";
                }
                return message;
            }else{
                return message;
            }
        }catch (Exception e){
              String message = "网络未知异常,请稍后再试。";
              return message;
        }
    }

    /**
     * 注销登录
     * @param request
     * @return
     */
    @RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/index";
    }


}
