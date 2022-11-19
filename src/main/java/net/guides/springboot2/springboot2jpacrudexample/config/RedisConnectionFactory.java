package net.guides.springboot2.springboot2jpacrudexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import net.guides.springboot2.springboot2jpacrudexample.model.Employee;
@Configuration
public class RedisConnectionFactory {
    @SuppressWarnings("deprecation")
    @Bean
    public static JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }
      //Creating Connection with Redis
        public static RedisTemplate<String, Employee> redisTemplate() {
            RedisTemplate<String, Employee> redisTemplate = new RedisTemplate<String ,Employee>();
            redisTemplate.setConnectionFactory(redisConnectionFactory());
            redisTemplate.afterPropertiesSet();
            return redisTemplate;
        }
}
