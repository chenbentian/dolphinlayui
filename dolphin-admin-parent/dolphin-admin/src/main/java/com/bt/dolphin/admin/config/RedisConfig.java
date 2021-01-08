package com.bt.dolphin.admin.config;
import java.lang.reflect.Method;
import java.time.Duration;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
  
/**
 * redis 客户端代码
 * @author cbt-34201
 *
 */
@Configuration  
@EnableCaching  
public class RedisConfig extends CachingConfigurerSupport{  
  
    @Bean  
    public KeyGenerator wiselyKeyGenerator(){  
        return new KeyGenerator() {  
            @Override  
            public Object generate(Object target, Method method, Object... params) {  
                StringBuilder sb = new StringBuilder();  
                sb.append(target.getClass().getName());  
                sb.append(method.getName());  
                for (Object obj : params) {  
                    sb.append(obj.toString());  
                }  
                return sb.toString();  
            }  
        };  
  
    }  
    
    /**
     * 
     * 方法说明： 1、由于配置了多个缓冲ehcache,redis ,需要制定一个默认的，Primary
     * 	     1、不管是redis还是 ehcache都是实现了spring的CacheManager
     *
     * Author：        cbt               
     * Create Date：   2020年12月28日 下午6:34:36
     * History:  2020年12月28日 下午6:34:36   cbt-34201   Created.
     *
     * @param redisConnectionFactory
     * @return
     *
     */
    @Bean
    @Primary
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1)); // 设置缓存有效期一小时
        System.out.println("《========【开启redis】 ======== 》 ");
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig(Thread.currentThread().getContextClassLoader()))
                .build();
    }
    
    
    @Bean  
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {  
        StringRedisTemplate template = new StringRedisTemplate(factory);  
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);  
        ObjectMapper om = new ObjectMapper();  
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);  
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);  
        jackson2JsonRedisSerializer.setObjectMapper(om);  
        template.setValueSerializer(jackson2JsonRedisSerializer);  
        template.afterPropertiesSet();  
        
//        RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
//
//        template.setKeySerializer(redisSerializer);
//
//        template.setHashKeySerializer(redisSerializer);
        return template;  
    }  
    
   @Bean
    public  ConfigureRedisAction configureRedisAction() {
    	return ConfigureRedisAction.NO_OP;
    }
   
    /**
     * 设置sessionId的cookieName
     * @return
     */
 /*   @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
            //cookie名字
          //  defaultCookieSerializer.setCookieName("sessionId");
            //域
          //  defaultCookieSerializer.setDomainName("xxx.com");
            //存储路径
         //   defaultCookieSerializer.setCookiePath("/");
        return defaultCookieSerializer;
    }*/

} 