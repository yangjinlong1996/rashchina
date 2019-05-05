package cn.edu.hsu.wanbeibookcitymanagementsystem.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Auther: Yuanzhu
 * @Date: 2018/11/1 19:32
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageList<T> {
    private List<T> list;
    private String totalPage;
    private String totalCount;

}
