package com.fescotech.apps.idm.admin.domain.vo;
import java.util.List;
import com.fescotech.apps.idm.admin.domain.SysUser;
public class SysUserVo extends SysUser {
	private static final long serialVersionUID = -7630605273122063162L;
	/**
	 * 角色ID列表
	 */
	private List<Long> roleIdList;
	private List<Long> sysIdList;

	public List<Long> getSysIdList() {
		return sysIdList;
	}

	public void setSysIdList(List<Long> sysIdList) {
		this.sysIdList = sysIdList;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}
	 
}