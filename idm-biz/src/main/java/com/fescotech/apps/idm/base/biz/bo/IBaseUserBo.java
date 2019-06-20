package com.fescotech.apps.idm.base.biz.bo;
import com.fescotech.apps.idm.base.domain.BaseUser;
import com.fescotech.apps.idm.base.domain.vo.BaseUserVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
public interface IBaseUserBo {
	
	public void insert(BaseUser baseUser);
	
	public void update(BaseUser baseUser);
	
	public List<BaseUser> queryBaseUser(BaseUser baseUser);

	public BaseUser loadBaseUser(Long userId);
	
	public int queryBaseUserCount(BaseUser baseUser);

	public BaseUserVo queryBaseUserByName(String userName);

	public List<BaseUser> queryUserList(Map<String, Object> map);

	int queryTotal(Map<String, Object> query);

	public void deleteBatch(Long[] userIds);

	public BaseUser queryUserById(Long userId);

	public void saveBaseUser(BaseUser user);

	public BaseUser queryUserByLoginName(String loginName);

	public List<String> queryAllPerms(HashMap<String, Object> map);

	public BaseUser queryUserByBaseUser(BaseUser user);

	
}