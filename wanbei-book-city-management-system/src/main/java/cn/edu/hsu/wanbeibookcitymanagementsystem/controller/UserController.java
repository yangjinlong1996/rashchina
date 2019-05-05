package cn.edu.hsu.wanbeibookcitymanagementsystem.controller;

import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.BookDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.BuyBookDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.LikeBookDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dao.UserDO;
import cn.edu.hsu.wanbeibookcitymanagementsystem.dto.*;
import cn.edu.hsu.wanbeibookcitymanagementsystem.mapper.BookMapper;
import cn.edu.hsu.wanbeibookcitymanagementsystem.mapper.BuyBookMapper;
import cn.edu.hsu.wanbeibookcitymanagementsystem.mapper.LikeBookMapper;
import cn.edu.hsu.wanbeibookcitymanagementsystem.mapper.UserMapper;
import cn.edu.hsu.wanbeibookcitymanagementsystem.service.UserService;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.ConvertUtils;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.PageList;
import cn.edu.hsu.wanbeibookcitymanagementsystem.util.PageUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import lombok.extern.java.Log;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static java.lang.System.out;


/**
 * @author: yangjinlong
 * @date: 2018/12/12 14:17
 * @description:
 */
@Log
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    LikeBookMapper likeBookMapper;

    @Autowired
    BuyBookMapper buyBookMapper;

    /**
     * 欢迎页
     * @return
     */
    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    /**
     * 找回密码(Get请求)
     */
    @RequestMapping("/find")
    public String find(){
        return "find";
    }

    /**
     * 跳转猜你喜欢页面
     */
    @RequestMapping("/guessLike")
    public String guessLike(){
        return "guessLike";
    }

    /**
     * 游客登录
     */
    @RequestMapping("/tourist")
    public String tourist(){
        return "tourist";
    }

    /**
     * 跳转到个人中心页面
     */
    @RequestMapping("/personalCenter")
    public String personalCenter(){
        return "personalCenter";
    }

    /**
     * 跳转到反馈建议页面
     */
    @RequestMapping("/feedback")
    public String feedback(){
        return "feedback";
    }



    /**
     * 展示用户的喜欢的书籍
     */
    @RequestMapping("showLikeBooks")
    @ResponseBody
    public PageUtil<List<LikeBookDTO>> showLikeBooks(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum") == null ? "1" : request.getParameter("pageNum"));
        String username = request.getSession().getAttribute("loginName").toString();
        List<LikeBookDO> likeBookDOS =
                likeBookMapper.select(new LikeBookDO().setIsDelete(0).setUsername(username));
        log.info("打印用户喜欢的书籍-------->"+ JSONObject.toJSONString(likeBookDOS));
        return new PageUtil(ConvertUtils.convert(likeBookDOS,LikeBookDTO.class),pageNum,5);
    }


    /**
     * 找回密码
     */
    @RequestMapping("/findpassword")
    public String findPassword(HttpServletRequest request){
        String pwdKey = request.getParameter("pwdKey");
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");
        UserDO userDO = userService.findPassword(pwdKey,username);
        if(null == userDO.getData()){
            userDO.setPassword(newPassword);
            userMapper.updateByPrimaryKeySelective(userDO);
        }
        request.setAttribute("userDTO",ConvertUtils.convert(userDO,UserDTO.class));
        return "/findcheck";
    }


    /**
     * 请求合作
     * @param request
     * @return
     */
    @RequestMapping("/help")
    public String help(HttpServletRequest request){
        String buttonName1 = request.getParameter("btnVal1");
        String buttonName2 = request.getParameter("btnVal2");
        String buttonName3 = request.getParameter("btnVal3");
        String string1 = "";
        String string2 = "";
        if("出版书籍".equals(buttonName1)){
            string1 = "欢迎您与我司进行书籍出版的合作,请在下方留下您的联系方式,我们会尽快与您联系！";
            string2 = "请在此处描述书籍的详细信息...(结尾处请备注[书籍]二字,否则不予通过)";
        }
        if("广告洽谈".equals(buttonName2)){
            string1 = "欢迎您与我司进行广告洽谈的合作,请在下方留下您的联系方式,我们会尽快与您联系！";
            string2 = "请在此处描述广告的详细信息...(结尾处请备注[广告]二字,否则不予通过)";
        }
        if("我要应聘".equals(buttonName3)){
            string1 = "很高兴您在找寻工作的期间选择了我司,请在下方留下您的联系方式,我们会尽快与您联系！";
            string2 = "请在此处描述您简历的详细信息...(结尾处请备注[应聘]二字,否则不予通过)";
        }
        request.setAttribute("String1",string1);
        request.setAttribute("String2",string2);
        return "cooperate";
    }

    /**
     * 我的丛书
     */
    @RequestMapping("/mybooks")
    public String mybooks(){
        return "mybooks";
    }


    /**
     * 商城热卖
     */
    @RequestMapping("/mallSelling")
    public String mallSelling(){
        return "mallSelling";
    }

    /**
     * 从商场热卖或猜你喜欢中购买书籍
     */
    @RequestMapping("/mallSellingBuy")
    @ResponseBody
    public String mallSellingBuy(HttpServletRequest request){
        //拿到登录者的userId
        String userId = request.getSession().getAttribute("userId").toString();
        //拿到书名
        String bookName = request.getParameter("bookName");
        log.info("书名bookName--------->"+bookName);
        BookDO bookDO = bookMapper.selectOne(new BookDO().setBookName(bookName).setIsDelete(0));
        String message = "";
        if(null == bookDO){
            //如果书籍无法找到
            message = "书库中暂无库存,暂时无法购买";
            return message;
        }
        //如果书籍已经在待支付的列表中
        if(buyBookMapper.selectCount(new BuyBookDO().setUserId(userId).setBookId(bookDO.getId()).setStatus(1)) > 0){
            message = "书籍已经在您的购物车内,请前往个人中心支付即可";
            return message;
        }
        //购买书籍入库,变为待支付状态。
        buyBookMapper.insertSelective(new BuyBookDO()
                .setUserId(userId).setIsDelete(0).setStatus(1).setCreateTime(new Date()).setBookId(bookDO.getId()));
        message = "加入购物车成功,请前往个人中心完成支付!";
        return message;
    }

    /**
     * 展示我的丛书列表
     * @param request
     * @param response
     */
    @RequestMapping("/showbooks")
    @ResponseBody
    public void showBooks(HttpServletRequest request, HttpServletResponse response){
        try{
            Integer pageNo = Integer.parseInt(request.getParameter("pageNo") == null ? "1" : request.getParameter("pageNo"));
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            String username = request.getSession().getAttribute("loginName").toString();
            PrintWriter out = response.getWriter();
            List<BookDTO> bookDTOList = userService.listBook(username,pageNo,5);
            PageInfo<BookDTO> pageInfo = new PageInfo<>(bookDTOList);
            PageList<BookDTO> pageList = new PageList<>();
            pageList.setTotalCount("5")
                    .setTotalPage(pageInfo.getPages()+"")
                    .setList(ConvertUtils.convert(bookDTOList,BookDTO.class));
            if(CollectionUtils.isNotEmpty(pageList.getList())){
                pageList.getList().get(0).setData(pageNo.toString());
            }
            JSONArray dataMessage = JSONArray.parseArray(JSONArray.toJSONString(pageList.getList()));
            out.append(dataMessage.toString());
        }catch(NullPointerException e){
            e.printStackTrace();
        }catch(NumberFormatException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 合作信息入库
     */
    @RequestMapping("/cooperate")
    @ResponseBody
    public String cooperate(HttpServletRequest request) {
        String message = "";
        try {
            String username = request.getParameter("username");
            String information = request.getParameter("information");
            String contactWay = request.getParameter("contactWay");
            String email = request.getParameter("email");
            String trueUsername = (String) request.getSession().getAttribute("loginName");
            if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(information) || StringUtils.isNullOrEmpty(contactWay) ||
                    StringUtils.isNullOrEmpty(email)) {
                message = "系统检测到您有一项或多项必填项为空,请返回重新输入。";
                return message;
            }
            if (!username.equals(trueUsername)) {
                message = "系统检测到您填写的账号不是登录的账号，请重新输入";
                return message;
            }
            if(information.indexOf("书籍")==-1&&information.indexOf("广告")==-1&&information.indexOf("应聘")==-1){
                message = "您输入的合作详细信息有误(没有备注关键字),所以您的本次提交失败";
                return message;
            }
            CooperateDTO cooperateDTO = new CooperateDTO();
            cooperateDTO.setContactWay(contactWay)
                    .setUsername(username)
                    .setInformation(information)
                    .setEmail(email);

            userService.cooperate(cooperateDTO);
            message = "提交成功！我们已经收到您的合作消息,请耐心等待！";
            return message;
        } catch (Exception e) {
            message = "系统异常,请稍后再试";
            e.printStackTrace();
            return message;
        }
    }


    /**
     * 用户书籍加入书架
     */
    @RequestMapping("/addBooks")
    @ResponseBody
    public String addBooks(HttpServletRequest request){
        String message = "";
        String bookId = request.getParameter("bookId");
        BookDO bookDO = bookMapper.selectOne(new BookDO().setId(Integer.parseInt(bookId)).setIsDelete(0));
        String username = request.getSession().getAttribute("loginName").toString();
        Integer count = likeBookMapper.selectCount(new LikeBookDO().setIsDelete(0).setBookName(bookDO.getBookName()).setBookId(Integer.parseInt(bookId)));
        if(count != 0){
            message = "您的书架中已有该书籍,无须再次加入！";
        }else{
            userService.addBook(username,bookDO.getBookName());
            message = "加入书架成功！";
        }
        return message;
    }

    /**
     * 用户删除书籍
     */
    @RequestMapping("/deleteBook")
    @ResponseBody
    public String deleteBook(HttpServletRequest request){
        String message = "";
        String bookId = request.getParameter("bookId");
        BookDO bookDO = bookMapper.selectOne(new BookDO().setId(Integer.parseInt(bookId)).setIsDelete(0));
        bookMapper.updateByPrimaryKeySelective(bookDO.setIsDelete(1).setDeleteTime(new Date()));
        message = "您已将该书籍删除,刷新页面可以看到效果！";
        return message;
    }

    /**
     * 用户购买书籍
     */
    @RequestMapping("/buyBook")
    @ResponseBody
    public String buyBook(HttpServletRequest request){
        String message = "";
        String bookId = request.getParameter("bookId");
        log.info("bookId---->"+bookId);
        //拿到用户的username查询用户的userId
        String username = request.getSession().getAttribute("loginName").toString();
        UserDO userDO = userMapper.selectOne(new UserDO().setIsDelete(0).setPeople(1).setUsername(username));
        if(buyBookMapper.selectCount(new BuyBookDO()
                .setIsDelete(0).setBookId(Integer.parseInt(bookId)).setStatus(1).setUserId(userDO.getUserId())) > 0){
            message = "该书籍已经存在于您的购物车，请前往个人中心完成支付";
            return message;
        }
        buyBookMapper.insertSelective(new BuyBookDO()
                .setIsDelete(0).setUserId(userDO.getUserId()).setStatus(1).setCreateTime(new Date())
                .setBookId(Integer.parseInt(bookId)));
        message = "书籍购买操作已经提交，请前往个人中心完成支付！";
        return message;
    }

    /**
     * 统计用户的藏书
     */
    @RequestMapping("/statistics")
    @ResponseBody
    public StatisticsModel statistics(HttpServletRequest request){
        //拿到缓存中的用户信息
        String userId = request.getSession().getAttribute("userId").toString();
        log.info("拿到的userId------>"+userId);
        String username = request.getSession().getAttribute("loginName").toString();
        //查询书籍数，查询书架中数量，查询待购买的书籍
        Example example = new Example(BookDO.class);
        example.createCriteria().andIn("username",Arrays.asList("",null,username)).andEqualTo("isDelete",0);
        Integer books = bookMapper.selectCountByExample(example);
        Integer loveBooks = likeBookMapper.selectCount(new LikeBookDO().setIsDelete(0).setUsername(username));
        Integer buyBooks = buyBookMapper.selectCount(new BuyBookDO().setIsDelete(0).setStatus(1).setUserId(userId));
        return new StatisticsModel().setBooks(books).setBuyBooks(buyBooks).setLoveBooks(loveBooks);
    }


    /**
     * 展示用户的待购买书籍和已购买书籍
     */
    @RequestMapping("/showBuyBooks")
    @ResponseBody
    public PageUtil<List<BookDTO>> showBuyBooks(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        log.info("pageNum------->"+pageNum);
        //通过缓存拿到userId
        String userId = request.getSession().getAttribute("userId").toString();
        //拿到用户的购买书籍
        List<BuyBookDO> buyBookDOS = buyBookMapper.select(new BuyBookDO().setIsDelete(0).setUserId(userId));
        //类型转换
        List<BuyBookDTO> buyBookDTOS = ConvertUtils.convert(buyBookDOS,BuyBookDTO.class);
        //拼装书籍的名字和价格
        buyBookDTOS.stream().forEach(it->{
            BookDO bookDO = bookMapper.selectOne(new BookDO().setIsDelete(0).setId(it.getBookId()));
            it.setBookName(bookDO.getBookName()).setPrice(bookDO.getPrice());
        });
        log.info("用户的购物车数据为------>"+buyBookDTOS);
        return new PageUtil(buyBookDTOS,pageNum,5);
    }

    /**
     * 统计用户的藏书
     */
    @RequestMapping("/payBuyBook")
    @ResponseBody
    public String statistics(HttpServletRequest request,@RequestParam("bookId") Integer bookId){
        //通过缓存拿到userId
        String userId = request.getSession().getAttribute("userId").toString();
        BuyBookDO buyBookDO = buyBookMapper.selectOne(new BuyBookDO().setIsDelete(0).setUserId(userId).setBookId(bookId).setStatus(1));
        if(null == buyBookDO){
            return "书库中暂无此书,或您已经购买,请联系管理员处理";
        }
        buyBookMapper.updateByPrimaryKey(buyBookDO.setStatus(2));
        return "请求已经提交商家处理,请等待商家审核通过后即可发货";
    }
}
