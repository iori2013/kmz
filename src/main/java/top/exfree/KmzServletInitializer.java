package top.exfree;

import org.apache.shiro.codec.Base64;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import top.exfree.web.common.utils.security.CipherUtils;

/**
 * web容器中进行部署
 * 
 * @author kmz
 */
public class KmzServletInitializer extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(KmzApplication.class);
    }


    public static void main(String[] args) {
        String aes = Base64.encodeToString(CipherUtils.generateNewKey(128, "AES").getEncoded());
        System.out.println(aes);
    }


}
