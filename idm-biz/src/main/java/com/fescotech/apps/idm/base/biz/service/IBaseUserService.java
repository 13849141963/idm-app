package com.fescotech.apps.idm.base.biz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseUser;
import com.fescotech.apps.idm.base.domain.vo.BaseUserVo;

public interface IBaseUserService {
	List<BaseUserVo> queryPageUserByOrg(Long orgId,int offset,int limit);

	List<BaseUserVo> queryList(Map<String, Object> map);
	
	List<BaseUserVo> queryPageList(Map<String, Object> map,int offset,int limit);

	int queryTotal(BaseUserVo total);

	BaseUserVo queryObject(Long userId);
	
	BaseUserVo checkLogin(String loginName);

	void saveBaseUser(BaseUser user);

	void updateBaseUser(BaseUser user);

	void deleteBatch(Long[] userIds);

	List<BaseUser> queryUserList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	BaseUser queryUserById(Long userId);

	void changeState(Long userId, int i);
	
	BaseUser queryUserByLoginName(String loginName);

	List<String> queryAllPerms(HashMap<String, Object> map);

	int updatePassword(Long userId, String password, String newPassword);

	BaseUser queryUserByBaseUser(BaseUser user);


}