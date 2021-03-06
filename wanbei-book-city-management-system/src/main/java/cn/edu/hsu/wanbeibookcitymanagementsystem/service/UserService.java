package cn.edu.hsu.wanbeibookcitymanagementsystem.service;

import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.BookDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.CooperateDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.UserDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.BookDTO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.CooperateDTO;

import java.util.List;

/**
 * @author: yangjinlong
 * @date: 2018/12/12 14:23
 * @description:
 */
public interface UserService {

    /**
     * 根据用户名和密码完成用户登录
     * 根据秘钥验证
     */
    UserDO userLogin(String username, String password,String pwdKey,Integer people);

    /**
     * 根据用户名和秘钥完成找回密码
     */
    UserDO findPassword(String pwdKey,String username);

    /**
     * 实现注册功能
     */
    UserDO reg(String username,String password,String pwdKey);

    /**
     * 实现用户的合作信息存储
     * @param cooperateDTO
     */
    void cooperate(CooperateDTO cooperateDTO);

    /**
     * 根据用户账号查询书籍
     * @param username
     * @return
     */
    List<BookDTO> listBook(String username,Integer pageNo,Integer pageSize);

    /**
     * 将用户喜欢的书籍加入书架
     * @param username
     * @param bookName
     */
    void addBook(String username,String bookName);
}
