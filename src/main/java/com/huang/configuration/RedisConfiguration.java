package com.huang.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis缓存初始化类
 *
 * @Author huangjihui
 * @Date 2018/11/9 10:35
 */
@Configuration
public class RedisConfiguration {

    @Bean(name = "sourceStringRedisTemplate")
    public StringRedisTemplate redisSourceTemplate(RedisProperties sourceRedisProperties) {
        StringRedisTemplate temple = new StringRedisTemplate();
        temple.setConnectionFactory(
                connectionFactory(sourceRedisProperties));

        return temple;
    }

    @Bean(name = "targetStringRedisTemplate")
    public StringRedisTemplate redisTargetTemplate(RedisProperties targetRedisProperties) {
        StringRedisTemplate temple = new StringRedisTemplate();
        temple.setConnectionFactory(
                connectionFactory(targetRedisProperties));

        return temple;
    }


    private RedisConnectionFactory connectionFactory(RedisProperties redisProperties) {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(redisProperties.getHost());
        jedis.setPort(redisProperties.getPort());
        if (!StringUtils.isEmpty(redisProperties.getPassword())) {
            jedis.setPassword(redisProperties.getPassword());
        }
        if (redisProperties.getDatabaseIndex() != 0) {
            jedis.setDatabase(redisProperties.getDatabaseIndex());
        }
        // 连接池使用默认配置
        jedis.setPoolConfig(new JedisPoolConfig());
        // 初始化连接pool
        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;

        return factory;
    }

    @Bean(name = "sourceRedisProperties")
    @ConfigurationProperties(prefix = "redis.source")
    public RedisProperties getSourceRedisProperties() {
        return new RedisProperties();
    }

    @Bean(name = "targetRedisProperties")
    @ConfigurationProperties(prefix = "redis.target")
    public RedisProperties getTargetRedisProperties() {
        return new RedisProperties();
    }
}
