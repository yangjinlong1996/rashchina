package cn.edu.hsu.wanbeibookcitymanagementsystem.interceptor;

import lombok.experimental.Accessors;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author: yangjinlong
 * @date: 2018/12/20 14:50
 * @description:
 */
@Accessors(chain = true)
@SpringBootConfiguration
public class Configuration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        // 拦截路径
        loginRegistry.addPathPatterns(
                Arrays.asList("/welcome","/mybooks","/mallSelling","/guessLike","/personalCenter","/feedback"));
        // 排除路径
        loginRegistry.excludePathPatterns("/index")
                     .excludePathPatterns("/find")
                     .excludePathPatterns("/reg")
                     .excludePathPatterns("/findcheck")
                     .excludePathPatterns("/regcheck")
                     .excludePathPatterns("/session");
        // 排除资源请求
        loginRegistry.excludePathPatterns("/css/login/*.css");
        loginRegistry.excludePathPatterns("/js/login/**/*.js");
        loginRegistry.excludePathPatterns("/image/login/*.png");
    }
}
