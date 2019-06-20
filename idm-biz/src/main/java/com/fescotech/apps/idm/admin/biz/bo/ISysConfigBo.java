package com.fescotech.apps.idm.admin.biz.bo;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.admin.domain.SysConfig;

public interface ISysConfigBo {
	public void insert(SysConfig sysConfig);
	
	public void update(SysConfig sysConfig);
	
	public List<SysConfig> querySysConfig(SysConfig sysConfig);
	
	public List<SysConfig> querySysConfig(Map<String,Object> query);
	
	public SysConfig loadSysConfig(Long id);
}