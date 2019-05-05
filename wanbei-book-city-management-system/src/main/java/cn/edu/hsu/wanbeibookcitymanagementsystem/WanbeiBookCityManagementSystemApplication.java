package cn.edu.hsu.wanbeibookcitymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("cn.edu.hsu.wanbeibookcitymanagementsystem.mapper")
public class WanbeiBookCityManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(WanbeiBookCityManagementSystemApplication.class, args);
    }
}
