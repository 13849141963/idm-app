package com.fescotech.apps.idm.base.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fesco.pafa.exceptions.MessageException;
import com.fescotech.apps.idm.base.biz.bo.IBaseUserBo;
import com.fescotech.apps.idm.base.biz.service.IBaseUserService;
import com.fescotech.apps.idm.base.domain.BaseUser;
import com.fescotech.apps.idm.base.domain.vo.BaseUserVo;
import com.fescotech.apps.idm.util.ResException;

public class BaseUserService implements IBaseUserService {
	private IBaseUserBo baseUserBo;

	public void setBaseUserBo(IBaseUserBo baseUserBo) {
		this.baseUserBo = baseUserBo;
	}

	@Override
	public List<BaseUserVo> queryPageUserByOrg(Long orgId, int offset, int limit) {
		return null;
	}

	@Override
	public List<BaseUserVo> queryList(Map<String, Object> map) {
		return null;
	}

	@Override
	public List<BaseUserVo> queryPageList(Map<String, Object> map, int offset,
			int limit) {
		return null;
	}

	@Override
	public int queryTotal(BaseUserVo total) {
		return 0;
	}
	
	
	
	@Override
	public BaseUserVo queryObject(Long userId) {
		return null;
	}

	@Override
	public BaseUserVo checkLogin(String loginName){
		return baseUserBo.queryBaseUserByName(loginName);
	}

	@Override
	public void saveBaseUser(BaseUser user) {
		baseUserBo.saveBaseUser(user);
	}

	@Override
	public void updateBaseUser(BaseUser user) {
		baseUserBo.update(user);
	}

	@Override
	public void deleteBatch(Long[] userIds) {
		baseUserBo.deleteBatch(userIds);
	}

	@Override
	public List<BaseUser> queryUserList(Map<String, Object> map) {
		return baseUserBo.queryUserList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> query) {
		return baseUserBo.queryTotal(query);
	}

	@Override
	public BaseUser queryUserById(Long userId) {
		return baseUserBo.queryUserById(userId);
	}

	@Override
	public void changeState(Long userId, int i) {
		BaseUser baseUser=baseUserBo.queryUserById(userId);
		if(baseUser.getUserStatus().equals(i)){
			throw new ResException("此用户已在此状态之中");
		}
		baseUser.setUserStatus(i);
		baseUserBo.update(baseUser);
	}
	
	@Override
	public BaseUser queryUserByLoginName(String loginName) {
		return baseUserBo.queryUserByLoginName(loginName);
	}
	
	@Override
	public BaseUser queryUserByBaseUser(BaseUser user) {
		return baseUserBo.queryUserByBaseUser(user);
	}

	@Override
	public List<String> queryAllPerms(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return baseUserBo.queryAllPerms(map);
	}

	//修改密码
	@Override
	public int updatePassword(Long userId, String password, String newPassword) {
		BaseUser bUser=baseUserBo.queryUserById(userId);
		if(bUser==null) 
			return 0;
		if(!password.equals(bUser.getUserPwd()))
			return 0;
		bUser.setUserPwd(newPassword);
		baseUserBo.update(bUser);
		return 1;
	}
	
}


