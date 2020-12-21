package com.bt.dolphin.shiro.realm;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.bt.dolphin.common.constant.AdminConst;
import com.bt.dolphin.common.enums.StatusEnum;
import com.bt.dolphin.shiro.ShiroUtil;
import com.bt.dolphin.system.user.api.SysUserService;
import com.bt.dolphin.system.user.vo.SysUserVo;


/**
 * Realm：域，Shiro 从从 Realm 获取安全数据（如用户、角色、权限），
 * 就是说 SecurityManager 要验证用户身份，那么它需要从 Realm 获取相应的用户进行比较以确定用户身份是否合法；
 * 也需要从 Realm 得到用户相应的角色 / 权限进行验证用户是否能进行操作；可以把 Realm 看成 DataSource，即安全数据源。
 * @author cbt
 *
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;
    
    /**
     * 授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 获取用户Principal对象
        SysUserVo user = (SysUserVo) principal.getPrimaryPrincipal();

        // 管理员拥有所有权限
        if (user.getUserId().equals(AdminConst.ADMIN_ID)) {
            info.addRole(AdminConst.ADMIN_ROLE_NAME);
            info.addStringPermission("*:*:*");
            return info;
        }

        // 赋予角色和资源授权
      /*  Set<SysRoleVo> roles = ShiroUtil.getSubjectRoles();
        roles.forEach(role -> {
            info.addRole(role.getName());
            role.getMenus().forEach(resource -> {
                    info.addStringPermission(resource.getPermission());
            });
        });*/

        return info;
    }

    /**
     * 认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserPassOpenIdToken token = (UserPassOpenIdToken) authenticationToken;

        String type = token.getLoginType();
        SysUserVo user =null;
        if ("1".equals(type)) { //第三方登入
        	//user = sysUserService.findByQqOpenId(token.getUsername());
        }else{
            user = sysUserService.getUserByUserNo(token.getUsername());
        }
            // 判断用户名是否存在
            if (user == null) {
                throw new UnknownAccountException();
            } else if (StatusEnum.FREEZED.getCode().equals(user.getUserStatus())) {
                throw new LockedAccountException();
            }
            // 对盐进行加密处理
            ByteSource salt = ByteSource.Util.bytes(user.getSalt());

            /* 传入密码自动判断是否正确
             * 参数1：传入对象给Principal
             * 参数2：正确的用户密码
             * 参数3：加盐处理
             * 参数4：固定写法
             */
            return new SimpleAuthenticationInfo(user, user.getUserPassword(), salt, getName());

    }

    /**
     * 自定义密码验证匹配器
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        setCredentialsMatcher(new SimpleCredentialsMatcher() {
            @Override
            public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
                UserPassOpenIdToken token = (UserPassOpenIdToken) authenticationToken;
                SimpleAuthenticationInfo info = (SimpleAuthenticationInfo) authenticationInfo;
                // 获取明文密码及密码盐
                String password = String.valueOf(token.getPassword());
                String salt = CodecSupport.toString(info.getCredentialsSalt().getBytes());
                if ("1".equals(token.getLoginType())) {
                  return true;
                }

                return equals(ShiroUtil.encrypt(password, salt), info.getCredentials());
            }
        });
    }
}
