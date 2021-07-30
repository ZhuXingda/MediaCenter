package mh.cas.center.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@Configuration
public class JedisPoolUtil {
    @Value("${redis.host}")
    String host;
    @Value("${redis.port}")
    int port;
    @Value("${redis.maxIdle}")
    int maxIdle;
    @Value("${redis.minIdle}")
    int minIdle;
    @Value("${redis.maxTotal}")
    int maxTotal;

    @Bean
    public JedisPool JedisPool() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxTotal(maxTotal);
        return new JedisPool(genericObjectPoolConfig, host,port);
    }
}
