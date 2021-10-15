package mc.video.servcie;

import mc.video.service.web.WebConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/7 16:36
 */
@Configuration
@ComponentScan(basePackages = "mc.video.service", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {WebConfig.class})
})
@PropertySource("classpath:/application.properties")
public class TestConfig {
}
