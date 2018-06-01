package pers.li.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import java.io.Serializable;

/**
 * create by lishengbo on 2018-05-31 16:48
 * <p>
 * 使用默认的sessionManager管理，每次请求会查询多次redis，会造成服务器压力
 * 故做此优化
 */
@SuppressWarnings("all")
public class CustomSessionManager extends DefaultWebSessionManager {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {

        Serializable sessionId = getSessionId(sessionKey);

        ServletRequest request = null;

        if (sessionKey instanceof WebSessionKey) {

            request = ((WebSessionKey) sessionKey).getServletRequest();

        }
        //先从request取session，存在直接用
        if (request != null && sessionId != null) {
            Session session = (Session) request.getAttribute(sessionId.toString());
            if (session != null) {
                return session;
            }
        }

        //不存在，在缓存中取，然后放进request
        Session session = super.retrieveSession(sessionKey);
//================================================================
//   读取key:org.apache.shiro.subject.support.DefaultSubjectContext_AUTHENTICATED_SESSION_KEY  //true
//   读取key:org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY     //soso
//================================================================
//
//        if (session != null) {
//
//            Collection<Object> attributeKeys = session.getAttributeKeys();
//            for (Object s : attributeKeys
//                    ) {
//                logger.error("key:"+s+"  //"+session.getAttribute(s));
//            }
//        }
        if (request != null && sessionId != null) {
            request.setAttribute(sessionId.toString(), session);
        }

        return session;

    }
}
