package mc.video.service.web;

import mc.video.service.aop.interceptor.LoginStatusCheckInterceptor;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "mc.video.service")
@PropertySource("classpath:/application.properties")
@EnableDubbo(scanBasePackages = "mc.video.service")
public class WebConfig implements WebMvcConfigurer{
    @Value("${front.url}")
    String ALLOW_ORIGIN;

    @Autowired
    LoginStatusCheckInterceptor loginStatusCheckInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(ALLOW_ORIGIN)
                .allowCredentials(true)
                .allowedMethods("GET", "POST","PUT","OPTIONS","DELETE")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/cover/**").addResourceLocations("classpath:/cover/");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new GsonHttpMessageConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginStatusCheckInterceptor).addPathPatterns("/detail/**");
    }
}
