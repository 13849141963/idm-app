package com.fescotech.apps.idm.admin.biz.bo.impl;
import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.admin.biz.bo.ISysConfigBo;
import com.fescotech.apps.idm.admin.domain.SysConfig;
public class SysConfigBo extends AbstractBoT implements ISysConfigBo{
	public void insert(SysConfig sysConfig){
		baseDao.insert("sysConfigSql.insert",sysConfig);
	}
	
	public void update(SysConfig sysConfig){
		baseDao.update("sysConfigSql.update",sysConfig);
	}
	
	public List<SysConfig> querySysConfig(SysConfig sysConfig){
		return baseDao.queryForList("sysConfigSql.querySysConfig",sysConfig,SysConfig.class);
	}
	
	public SysConfig loadSysConfig(Long id){
		return (SysConfig)baseDao.queryForObject("sysConfigSql.queryByKey",id);
	}

	@Override
	public List<SysConfig> querySysConfig(Map<String, Object> query) {
		return baseDao.queryForList("sysConfigSql.querySysConfigMap",query,SysConfig.class);
	}
}