package mc.message.service.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author: ZhuXingda
 * @Date: 2021/8/26 21:15
 */
@Configuration
public class MessageQueueChannelFactory {
    @Value("${mq.user.name}")
    private String userName;
    @Value("${mq.user.password}")
    private String password;
    @Value("${mq.user.virtualHost}")
    private String virtualHost;
    @Value("${mq.user.host}")
    private String host;
    @Value("${mq.user.port}")
    private int port;


    private ConnectionFactory connectionFactory;

    @PostConstruct
    public void init(){
        connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
    }

    @Bean
    public Channel videoSetUpdateChannel() throws Exception{
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("videoSetUpdateExchange", "fanout");
        channel.queueDeclare("videoSetUpdateQueue", false, false, false, null);
        channel.queueBind("videoSetUpdateQueue", "videoSetUpdateExchange", "");
        return channel;
    }
}
