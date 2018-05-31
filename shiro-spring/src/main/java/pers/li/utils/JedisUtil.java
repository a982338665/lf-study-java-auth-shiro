package pers.li.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;

/**
 * create by lishengbo on 2018-05-31 15:24
 *
 * 访问工具包
 */
@Component
public class JedisUtil {


    /**
     * 需要与spring-redis配置文件中的一样
     */
    @Resource
    private JedisPool jedisPool;

    private Jedis getResource(){
        return jedisPool.getResource();
    }

    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis =getResource();
        try {
            jedis.set(key,value);
            return value;
        }finally {
            jedis.close();
        }

    }

    //key超时时间---单位为秒
    public void expire(byte[] key, int i) {
        Jedis jedis =getResource();
        try {
            jedis.expire(key,i);
        }finally {
            jedis.close();
        }
    }

    public byte[] get(byte[] key) {
        Jedis jedis =getResource();
        try {
            return  jedis.get(key);
        }finally {
            jedis.close();
        }
    }

    public void del(byte[] key) {
        Jedis jedis =getResource();
        try {
            jedis.del(key);
        }finally {
            jedis.close();
        }
    }

    public Set<byte[]> keys(String shiro_session_prefic) {
        Jedis jedis =getResource();
        try {
            return jedis.keys((shiro_session_prefic+"*0").getBytes());
        }finally {
            jedis.close();
        }
    }
}
