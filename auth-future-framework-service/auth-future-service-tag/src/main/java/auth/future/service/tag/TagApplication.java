package auth.future.service.tag;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hzy
 * @since 2023-12-14
 **/
@SpringBootApplication
//@MapperScan("auth.future.service.tag.mapper")
public class TagApplication {
    public static void main(String[] args) {
        SpringApplication.run(TagApplication.class,args);
    }
}
