package cn.edu.hsu.wanbeibookcitymanagementsystem.util;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yangjinlong
 * @date: 2019/3/17 18:09
 * @description: 分页工具
 */
@Log
@Data
@Accessors(chain = true)
public class PageUtil<T> {

    /**
     * 需要分页的list
     */
    private List<T> list;

    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 当前是否是最后一页(是否含有下一页)
     */
    private Boolean lastPage;

    public PageUtil(List<T> list, Integer pageNum, Integer pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = list.size();
        this.pages = total % pageSize == 0 ? total / pageSize : (total /pageSize) + 1 ;
        this.lastPage = pageNum.equals(pages);
        this.list = list.parallelStream().skip((pageNum-1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    public PageUtil(){
        this.pageNum = 0;
        this.pageSize = 0;
        this.total = 0;
        this.pages = 0 ;
        this.lastPage = true;
        this.list = new ArrayList<>();
    }
}
