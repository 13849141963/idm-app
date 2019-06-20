package com.fescotech.apps.idm.admin.biz.service.impl;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fescotech.apps.idm.admin.biz.bo.ISysUserRoleBo;
import com.fescotech.apps.idm.admin.biz.service.ISysUserRoleService;
/**
 * 用户与角色对应关系
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements ISysUserRoleService {
	private ISysUserRoleBo sysUserRoleBo;
	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		if(roleIdList.size() == 0){
			return ;
		}
		sysUserRoleBo.saveOrUpdate(userId, roleIdList);
	}

	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return sysUserRoleBo.queryRoleIdList(userId);
	}

	@Override
	public void delete(Long userId) {
		sysUserRoleBo.deleteByUserId(userId);
	}

	public ISysUserRoleBo getSysUserRoleBo() {
		return sysUserRoleBo;
	}

	public void setSysUserRoleBo(ISysUserRoleBo sysUserRoleBo) {
		this.sysUserRoleBo = sysUserRoleBo;
	}
	
}
