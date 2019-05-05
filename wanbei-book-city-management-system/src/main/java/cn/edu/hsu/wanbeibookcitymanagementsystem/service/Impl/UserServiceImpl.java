package cn.edu.hsu.wanbeibookcitymanagementsystem.service.Impl;

import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.BookDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.CooperateDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.LikeBookDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.UserDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.BookDTO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.CooperateDTO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.mapper.BookMapper;
import cn.edu.hsu.wanbeibookcitymanagementsystem.mapper.CooperateMapper;
import cn.edu.hsu.wanbeibookcitymanagementsystem.mapper.LikeBookMapper;
import cn.edu.hsu.wanbeibookcitymanagementsystem.mapper.UserMapper;
import cn.edu.hsu.wanbeibookcitymanagementsystem.service.UserService;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.ConvertUtils;
import com.github.pagehelper.PageHelper;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

import static cn.edu.hsu.wanbeibookcitymanagementsystem.util.statusUtils.*;

/**
 * @author: yangjinlong
 * @date: 2018/12/12 14:25
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CooperateMapper cooperateMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    LikeBookMapper likeBookMapper;

    /**
     * 根据用户名和密码完成用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @param pwdKey 密玥
     */
    @Override
    public UserDO userLogin(String username, String password,String pwdKey,Integer people) {
        UserDO userDO = new UserDO();
        userDO.setUsername(username)
                .setPassword(password)
                .setPwdKey(pwdKey)
                .setPeople(people)
                .setIsDelete(IS_DELETE_ZERO);
        if(StringUtils.isNullOrEmpty(username)){
            userDO.setData("用户名不得为空,请重新输入！");
            return userDO;
        }else if(StringUtils.isNullOrEmpty(password)){
            userDO.setData("密码不能为空,请重新输入！");
            return userDO;
        }else if(StringUtils.isNullOrEmpty(pwdKey)){
            userDO.setData("秘钥不得为空,请重新输入！");
            return userDO;
        }else if(ZERO == userMapper.selectCount(userDO)){
            userDO.setData("用户名·密码·密钥·用户身份中有一个或多个错误,请重新输入！");
            return userDO;
        }else if(ONE < userMapper.selectCount(userDO)){
            userDO.setData("查询到多个账户,查询结果异常,请联系系统管理员解决！");
            return userDO;
        }
        return userMapper.selectOne(userDO);
    }

    /**
     * 根据用户名和秘钥完成找回密码
     *
     * @param pwdKey
     * @param username
     */
    @Override
    public UserDO findPassword(String pwdKey, String username) {
        UserDO userDO = new UserDO();
        userDO.setPwdKey(pwdKey)
                .setUsername(username)
                .setIsDelete(ZERO);
        if(ZERO == userMapper.selectCount(userDO)){
            userDO.setData("秘钥或账号错误,请检查后再试！");
            return userDO;
        }else if(ONE < userMapper.selectCount(userDO)){
            userDO.setData("查询到多个结果,请联系管理员解决！");
            return userDO;
        }
        return userMapper.selectOne(userDO);
    }

    /**
     * 实现注册功能
     *
     * @param username
     * @param password
     * @param pwdKey
     */
    @Override
    public UserDO reg(String username, String password, String pwdKey) {

        UserDO userDO = new UserDO();
        userDO.setUsername(username);
        if(ZERO != userMapper.selectCount(userDO)){
            userDO.setData("该用户名已经被注册,请换一个再试。");
            return userDO;
        }else{
            //注册时:为每个用户分配一个userId
            String uuid = UUID.randomUUID().toString().replaceAll("-","");;
            userDO.setUserId(uuid);
            userDO.setPassword(password)
                    .setPwdKey(pwdKey)
                    .setIsDelete(ZERO)
                    .setPeople(ONE)
                    .setCreateTime(new Date());
            userMapper.insertSelective(userDO);
            return userDO;
        }
    }

    /**
     * 实现用户的合作信息存储
     *
     * @param cooperateDTO
     */
    @Override
    public void cooperate(CooperateDTO cooperateDTO) {
       CooperateDO cooperateDO = ConvertUtils.convert(cooperateDTO,CooperateDO.class);
       cooperateDO.setIsDelete(ZERO)
               .setCreateTime(new Date());
        boolean b1 = cooperateDO.getInformation().indexOf("书籍")!=-1;
        boolean b2 = cooperateDO.getInformation().indexOf("广告")!=-1;
        boolean b3 = cooperateDO.getInformation().indexOf("应聘")!=-1;
       if((b1&&b2&&b3)||(b1&&b2)||(b2&&b3)||(b1&&b3)){
           cooperateDO.setType(FOUR);
       }else if(b1){
           cooperateDO.setType(ONE);
       }else if(b2){
           cooperateDO.setType(TWO);
       }else if(b3){
           cooperateDO.setType(THREE);
       }
       //将用户的userId存进表里
       UserDO userDO = userMapper.selectOne(new UserDO().setUsername(cooperateDTO.getUsername()).setIsDelete(0));
       if(null != userDO){
           cooperateDO.setUserId(userDO.getUserId());
       }
       cooperateMapper.insertSelective(cooperateDO);
    }

    /**
     * 根据用户账号查询书籍
     *
     * @param username
     * @return
     */
    @Override
    public List<BookDTO> listBook(String username,Integer pageNo,Integer pageSize) {
        Example example = new Example(BookDO.class);
        //如果数据库中userName为空,则为公用,所有人都能看到
        example.createCriteria().andEqualTo("isDelete",0)
                .andIn("username", Arrays.asList("",null,username));
        if(ZERO == bookMapper.selectCountByExample(example)){
            return new ArrayList<>();
        }
        PageHelper.startPage(pageNo, pageSize);
        return ConvertUtils.convert(bookMapper.selectByExample(example),BookDTO.class);
    }

    /**
     * 将用户喜欢的书籍加入书架
     * @param username
     * @param bookName
     */
    @Override
    public void addBook(String username, String bookName) {

        likeBookMapper.insert(new LikeBookDO()
                .setBookName(bookName)
                .setCreateTime(new Date())
                .setId(null)
                .setIsDelete(0)
                .setBookId(bookMapper.selectOne(new BookDO().setIsDelete(0).setBookName(bookName)).getId())
                .setUsername(username));
    }
}
