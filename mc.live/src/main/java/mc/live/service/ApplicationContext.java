package mc.live.service;

import mc.live.service.rpc.TestConsumer;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/3 01:24
 */
@Configuration
@ComponentScan(basePackages = "mc.live.service")
@EnableDubbo(scanBasePackages = "mc.live.service")
@PropertySource("classpath:/application.properties")
public class ApplicationContext {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class);
        context.start();
        TestConsumer testConsumer = context.getBean(TestConsumer.class);
        testConsumer.test();
    }
}
