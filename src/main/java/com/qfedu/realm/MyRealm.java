package com.qfedu.realm;

import com.qfedu.dao.UserDao;
import com.qfedu.pojo.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

// 自定义realm
public class MyRealm extends AuthorizingRealm {

    @Autowired(required = false)
    private UserDao userDao;

    // 获取授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 获取登录后的合法的用户名
        String name = (String)principals.getPrimaryPrincipal();
        // 从数据库中好擦U型你用户的角色和权限列表
        List<String> roles = userDao.findRolesByName(name);
        List<String> perms = userDao.findPermsByName(name);

        // 授权信息对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(new HashSet<>(roles));
        info.setStringPermissions(new HashSet<>(perms));

        return info;
    }
    // 获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 从token中获取用户名
        String name = (String)token.getPrincipal();

        // 从数据库查询用户数据
        User user = userDao.findByName(name);
        // 保存合法认证信息的对象
        SimpleAuthenticationInfo info = null;
        if(user == null){
            info = new SimpleAuthenticationInfo(null, null, this.getName());
        }else{
            // 第一个参数，用户名
            // 第二个参数，合法密码
            // 第三个参数，realm的名称
            info = new SimpleAuthenticationInfo(name, user.getPassword(), this.getName());
        }

        return info;
    }
}






