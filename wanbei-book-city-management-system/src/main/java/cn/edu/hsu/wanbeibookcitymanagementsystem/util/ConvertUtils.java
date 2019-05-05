package cn.edu.hsu.wanbeibookcitymanagementsystem.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.java.Log;

import java.util.List;
import java.util.logging.Level;

//import com.ucmed.petra.inquiry.util.model.ModelDataObjectUtil;

/**
 * @Auther: Yuanzhu
 * @Date: 2018/11/2 11:03
 * @Description:
 */
@Log
public class ConvertUtils {

    private static final String PAGEINFO_LIST_PROPERTY = "list";
    /**
     * 对象之间的转换
     *
     * @param o
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convert(Object o, Class<T> clazz) {
        try {
            String ObjectJsonString = JSONObject.toJSONString(o);
            T ConversionClass = JSONObject.parseObject(ObjectJsonString, clazz);
            return ConversionClass;
        } catch (Exception e) {
            log.log(Level.SEVERE, "ObjectConversionException_Object=" + o.toString() + " ConversionClass=" + clazz, e);
        }
        return null;
    }

    public static <T> List<T> convert(List<?> list, Class<T> clazz) {
        try {
            String listJsonString = JSONObject.toJSONString(list);
            List<T> convertList = JSONObject.parseArray(listJsonString, clazz);
            return convertList;
        } catch (Exception e) {
            log.log(Level.SEVERE, "ListConversionException_List=" + JSONObject.toJSONString(list) + " ConversionClass=" + clazz, e);
        }
        return null;
    }
}
