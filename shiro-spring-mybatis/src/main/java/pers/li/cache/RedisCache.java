package pers.li.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import pers.li.utils.JedisUtil;

import java.util.Collection;
import java.util.Set;

/**
 * create by lishengbo on 2018-05-31 17:11
 */
@Component
public class RedisCache<K,V> implements Cache<K,V> {

    @Autowired
    private JedisUtil jedisUtil;

    private final String cache_prefix="pers-cache:";

    private  byte[] getKey(K k){
        if(k instanceof String){
            return (cache_prefix+k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    public V get(K k) throws CacheException {
        System.out.println("从redis获取权限数据----");
        byte[] val = jedisUtil.get(getKey(k));
        if (val!=null){
            return (V)SerializationUtils.deserialize(val);
        }
        //此处可优化，直接读内存--二级缓存--进一步提高系统性能
        return null;
    }

    public V put(K k, V v) throws CacheException {
        byte[] key = getKey(k);
        byte[] val = SerializationUtils.serialize(v);
        jedisUtil.set(key,val);
        jedisUtil.expire(key,600);
        return v;

    }

    public V remove(K k) throws CacheException {
        byte[] key = getKey(k);
        V v = get(k);
        jedisUtil.del(key);
        return v;
    }

    public void clear() throws CacheException {
        //-----clear 不做清空
    }

    public int size() {
        return 0;
    }

    public Set<K> keys() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }
}
