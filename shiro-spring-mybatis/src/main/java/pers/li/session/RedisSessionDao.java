package pers.li.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import pers.li.utils.JedisUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * create by lishengbo on 2018-05-31 15:23
 */
@SuppressWarnings("all")
@Component
public class RedisSessionDao extends AbstractSessionDAO {



    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private JedisUtil jedisUtil;

    private final  String shiro_session_prefic="pers-session:";

    private byte[] getKey(String key){
        return (shiro_session_prefic+key).getBytes();
    }

    //创建session
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        //绑定session和sessionId关系
        assignSessionId(session,sessionId);
        if (session != null) {

            Collection<Object> attributeKeys = session.getAttributeKeys();
            for (Object s : attributeKeys
                    ) {
                logger.error("创建key:"+attributeKeys+"  //"+session.getAttribute(s));
            }
        }
        saveSession(session);
        return sessionId;
    }

    private void saveSession(Session session) {
        if(session!=null && session.getId()!=null){
            byte[] key=getKey(session.getId().toString());
            byte[] value= SerializationUtils.serialize(session);
            jedisUtil.set(key,value);
            jedisUtil.expire(key,600);
        }
    }

    //通过sessionId获取session
    protected Session doReadSession(Serializable sessionId) {
        if(sessionId==null){
            return null;
        }
        byte[] key=getKey(sessionId.toString());
        byte[] value=jedisUtil.get(key);
        Session session = (Session) SerializationUtils.deserialize(value);
        if (session != null) {

            Collection<Object> attributeKeys = session.getAttributeKeys();
            for (Object s : attributeKeys
                    ) {
                logger.error("读取key:"+s+"  //"+session.getAttribute(s));
            }
        }
        //反序列化为session对象
        return session ;
    }

    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    public void delete(Session session) {
        if(session==null||session.getId()==null){
            return;
        }
        byte[] key = getKey(session.getId().toString());
        jedisUtil.del(key);
    }

    //获取指定的存活的session--可用来统计在线人数
    public Collection<Session> getActiveSessions() {

        Set<byte[]> keys=jedisUtil.keys(shiro_session_prefic);
        Set<Session> sessions=new HashSet<Session>();
        if(CollectionUtils.isEmpty(keys)){
            return sessions;
        }
        for (byte[] key:keys
             ) {
            Session session=(Session)SerializationUtils.deserialize(jedisUtil.get(key));
            sessions.add(session);
        }

        return sessions;
    }
}
