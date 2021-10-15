package mc.cas.center.conf;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/4 11:44
 */
@Configuration
@ComponentScan(basePackages = "mc.cas.center")
@PropertySource("classpath:/application.properties")
@EnableDubbo(scanBasePackages = "mc.cas.center")
public class ApplicationContext {
}
