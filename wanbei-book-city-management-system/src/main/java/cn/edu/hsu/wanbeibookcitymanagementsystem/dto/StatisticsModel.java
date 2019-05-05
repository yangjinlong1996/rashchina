package cn.edu.hsu.wanbeibookcitymanagementsystem.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: yangJinLong
 * @date: 2019/4/19 9:28
 * @annotation:
 **/
@Data
@Accessors(chain = true)
public class StatisticsModel {
    /**
     * 我的丛书总数
     */
    private Integer books;

    /**
     * 收藏的总书
     */
    private Integer loveBooks;

    /**
     * 待购买的书籍总书
     */
    private Integer buyBooks;



}
