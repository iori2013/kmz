package top.exfree.web.framework.shiro.util;

import top.exfree.web.framework.shiro.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import top.exfree.web.framework.shiro.realm.UserRealm;

/**
 * 用户授权信息
 * 
 * @author kmz
 */
public class AuthorizationUtils
{
    /**
     * 清理所有用户授权信息缓存
     */
    public static void clearAllCachedAuthorizationInfo()
    {
        getUserRealm().clearAllCachedAuthorizationInfo();
    }

    /**
     * 获取自定义Realm
     */
    public static UserRealm getUserRealm()
    {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        return (UserRealm) rsm.getRealms().iterator().next();
    }
}
