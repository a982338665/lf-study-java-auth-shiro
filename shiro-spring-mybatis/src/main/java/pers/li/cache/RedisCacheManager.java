package pers.li.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;


/**
 * create by lishengbo on 2018-05-31 17:10
 */

public class RedisCacheManager implements CacheManager {

    @Resource
    private RedisCache redisCache;


    //String s表示缓存名称，可用可不用
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return redisCache;
    }
}
