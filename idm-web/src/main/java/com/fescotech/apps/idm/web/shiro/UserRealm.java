package com.fescotech.apps.idm.web.shiro;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import com.fescotech.apps.idm.admin.biz.service.ISysMenuService;
import com.fescotech.apps.idm.admin.biz.service.ISysUserService;
import com.fescotech.apps.idm.admin.domain.SysUser;
import com.fescotech.apps.idm.admin.domain.vo.SysMenuVo;
import com.fescotech.apps.idm.base.biz.service.IBaseUserService;
import com.fescotech.apps.idm.web.util.ShiroUtils;
/**
 * 认证
 */
public class UserRealm extends AuthorizingRealm {
     
    private  ISysUserService sysUserService;
    
    private  ISysMenuService sysMenuService;
    
    private IBaseUserService baseUserService;
    
    public IBaseUserService getBaseUserService() {
		return baseUserService;
	}

	public void setBaseUserService(IBaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}

	public ISysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public ISysMenuService getSysMenuService() {
		return sysMenuService;
	}

	public void setSysMenuService(ISysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}

	/**
     * 授权(验证权限时调用)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUser user = (SysUser)principals.getPrimaryPrincipal();
		Long userId = user.getUserId();
		
		List<String> permsList = null;
		
		//系统管理员，拥有最高权限
		if(userId == 1){
			List<SysMenuVo> menuList = sysMenuService.queryList(new HashMap<String, Object>());
			permsList = new ArrayList<String>(menuList.size());
			 for(SysMenuVo menu : menuList){
				permsList.add(menu.getPerms());
			} 
		}else{
			permsList = sysUserService.queryAllPerms(userId);
		}

		//用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for(String perms : permsList){
			if(StringUtils.isBlank(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        //查询用户信息
        SysUser user = sysUserService.queryByUserName(username);
        
        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        
        //密码错误
        if(!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        
        //账号锁定
        if(user.getStatus() == 0){
        	throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        
        //将登陆用户的权限存进session
        /*HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getUserId());*/
        List<String> permsList = sysUserService.queryAllPerms(user.getUserId());
        ShiroUtils.setSessionAttribute(user.getUserId()+"-perms", permsList);
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
	}

}
