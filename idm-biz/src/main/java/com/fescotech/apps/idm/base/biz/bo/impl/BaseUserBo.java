package com.fescotech.apps.idm.base.biz.bo.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseUserBo;
import com.fescotech.apps.idm.base.domain.BaseUser;
import com.fescotech.apps.idm.base.domain.vo.BaseUserVo;

public class BaseUserBo extends AbstractBoT implements IBaseUserBo{

	public void insert(BaseUser baseUser){
		baseDao.insert("baseUserSql.insertBaseUser",baseUser);
	}
	
	public void update(BaseUser baseUser){
		baseDao.update("baseUserSql.updateBaseUser",baseUser);
	}
	
	public List<BaseUser> queryBaseUser(BaseUser baseUser){
		return baseDao.queryForList("baseUserSql.queryBaseUser",baseUser,BaseUser.class);
	}
	
	public BaseUserVo loadBaseUser(Long userId){
		return (BaseUserVo)baseDao.queryForObject("baseUserSql.queryByKey",userId);
	}
	
	
	public int queryBaseUserCount(BaseUser baseUser){
		return (int)baseDao.queryForObject("baseUserSql.queryBaseUserCount",baseUser);
	}

	@Override
	public  BaseUserVo  queryBaseUserByName(String loginName) {	 
		return (BaseUserVo)baseDao.queryForObject("baseUserSql.queryBaseUserByName",loginName);
	}

	//用于主页面大列表的查询
	@Override
	public List<BaseUser> queryUserList(Map<String, Object> query) {
		return baseDao.queryForList("baseUserSql.queryUserList",query,BaseUser.class);
	}
	
	//做主页显示时用的查询总数量
	@Override
	public int queryTotal(Map<String, Object> query) {
		return (int)baseDao.queryForObject("baseUserSql.queryBaseUserTotal",query);
	}

	@Override
	public void deleteBatch(Long[] userIds) {
		baseDao.delete("baseUserSql.deleteBatch",userIds);
	}

	@Override
	public BaseUser queryUserById(Long userId) {
		return (BaseUser)baseDao.queryForObject("baseUserSql.queryBaseUserById",userId);
	}

	@Override
	public void saveBaseUser(BaseUser user) {
		baseDao.insert("baseUserSql.insertBaseUser", user);
	}

	@Override
	public BaseUser queryUserByLoginName(String loginName) {
		return (BaseUser)baseDao.queryForObject("baseUserSql.queryBaseUserByUserName",loginName);
	}

	@Override
	public List<String> queryAllPerms(HashMap<String, Object> map) {
		return baseDao.queryForList("baseUserSql.queryAllPerms",map,String.class);
	}

	@Override
	public BaseUser queryUserByBaseUser(BaseUser user) {
		// TODO Auto-generated method stub queryBaseUser
		return (BaseUser)baseDao.queryForObject("baseUserSql.queryBaseUser",user);
	}
	
	
		
}