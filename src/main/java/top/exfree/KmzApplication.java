package top.exfree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author kmz
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class KmzApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(KmzApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  后台管理启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}