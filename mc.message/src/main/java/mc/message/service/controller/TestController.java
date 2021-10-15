package mc.message.service.controller;

import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/27 01:08
 */
@RestController
public class TestController {
    @Autowired
    Channel channel;

    @GetMapping("/test")
    public void test() throws IOException {
        channel.basicPublish("videoSetUpdateExchange", "", null,"aaaaaaa".getBytes(StandardCharsets.UTF_8));
    }
}
