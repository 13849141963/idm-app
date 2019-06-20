package com.fescotech.apps.idm.admin.biz.service;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.admin.domain.SysUser;
import com.fescotech.apps.idm.admin.domain.vo.SysUserVo;
/**
 * 系统用户
 */
public interface ISysUserService {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUser queryByUserName(String username);
	
	/**
	 * 根据用户ID，查询用户
	 * @param userId
	 * @return
	 */
	SysUserVo queryObject(Long userId);
	
	/**
	 * 查询用户列表
	 */
	List<SysUser> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存用户
	 */
	void save(SysUserVo user);
	
	/**
	 * 修改用户
	 */
	void update(SysUserVo user);
	
	/**
	 * 删除用户
	 */
	void deleteBatch(Long[] userIds);
	
	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	int updatePassword(Long userId, String password, String newPassword);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
	
}
