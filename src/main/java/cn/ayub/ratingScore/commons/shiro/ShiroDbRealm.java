package cn.ayub.ratingScore.commons.shiro;
import java.util.List;

import cn.ayub.ratingScore.commons.utils.IPUtils;
import cn.ayub.ratingScore.systmanager.model.SysUser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ayub.ratingScore.systmanager.model.vo.SysUserVo;
import cn.ayub.ratingScore.systmanager.service.ISysUserService;

//import com.wangzhixuan.service.IRoleService;

/**
 * @description：shiro权限认证
 * @author：**
 * @date：2015/10/1 14:51
 */
public class ShiroDbRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LogManager.getLogger(ShiroDbRealm.class);

    @Autowired
    private ISysUserService sysUserService;
//    @Autowired private IRoleService roleService;
    
    public ShiroDbRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }
    
    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        LOGGER.info("Shiro开始登录认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        SysUser sysUser=new SysUser();
        sysUser.setLoginAccount(token.getUsername());
        List<SysUser> list = sysUserService.getSysUserByloginAccount(sysUser);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        SysUser user=list.get(0);
        if (user.getStatus() == 0) {
//            throw new DisabledAccountException("账号未启用！");
            return null;
        }
        // 读取用户的url和角色
//        Map<String, Set<String>> resourceMap = roleService.selectResourceMapByUserId(user.getId());
//        Set<String> urls = resourceMap.get("urls");
//        Set<String> roles = resourceMap.get("roles");
        /*ShiroUser shiroUser = new ShiroUser(user.getUserId(), user.getLoginAccount(), user.getUserName(), urls);
        shiroUser.setRoles(roles);*/
        ShiroUser shiroUser = new ShiroUser(user.getUserId(), user.getLoginAccount(), user.getUserName(), null);
        shiroUser.setRoles(null);
        // 认证缓存信息
        return new SimpleAuthenticationInfo(shiroUser, user.getPassword().toCharArray(),
                ShiroByteSource.of(user.getSalf()), getName());
    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(shiroUser.getRoles());
        info.addStringPermissions(shiroUser.getUrlSet());
        return info;
    }
    
    @Override
    public void onLogout(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        removeUserCache(shiroUser);
    }

    /**
     * 清除用户缓存
     * @param shiroUser
     */
    public void removeUserCache(ShiroUser shiroUser){
        removeUserCache(shiroUser.getLoginName());
    }

    /**
     * 清除用户缓存
     * @param loginName
     */
    public void removeUserCache(String loginName){
        SimplePrincipalCollection principals = new SimplePrincipalCollection();
        principals.add(loginName, super.getName());
        super.clearCachedAuthenticationInfo(principals);
    }
}
