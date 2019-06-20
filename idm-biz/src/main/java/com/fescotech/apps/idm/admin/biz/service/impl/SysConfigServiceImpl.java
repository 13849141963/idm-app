package com.fescotech.apps.idm.admin.biz.service.impl;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fescotech.apps.idm.admin.biz.bo.ISysConfigBo;
import com.fescotech.apps.idm.admin.biz.service.ISysConfigService;
import com.fescotech.apps.idm.admin.domain.SysConfig;

@Service("sysConfigService")
public class SysConfigServiceImpl implements ISysConfigService {
	private ISysConfigBo sysConfigBo;
	
	@Override
	public void save(SysConfig  config) {
		sysConfigBo.insert(config);
	}

	@Override
	public void update(SysConfig config) {
		sysConfigBo.update(config);
	}

	@Override
	public void updateValueByKey(String key, String value) {
		/*sysConfigBo.updateValueByKey(key, value);*/
	}

	@Override
	public void deleteBatch(Long[] ids) {
		/*sysConfigBo.deleteBatch(ids);*/
	}

	@Override
	public List<SysConfig> queryList(Map<String, Object> map) {
		return sysConfigBo.querySysConfig(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		//return sysConfigBo.queryTotal(map);
		return 0;
	}

	@Override
	public SysConfig queryObject(Long id) {
		return sysConfigBo.loadSysConfig(id);
	}

	@Override
	public String getValue(String key, String defaultValue) {
		/*
			String value = sysConfigBo.queryByKey(key);
			if(StringUtils.isBlank(value)){
				return defaultValue;
			}
			return value;
		*/
		return null;
	}
	
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) throws Exception {
		String value = getValue(key, null);
		if(StringUtils.isNotBlank(value)){
			return JSON.parseObject(value, clazz);
		}
		
		return clazz.newInstance();
	}

	public ISysConfigBo getSysConfigBo() {
		return sysConfigBo;
	}

	public void setSysConfigBo(ISysConfigBo sysConfigBo) {
		this.sysConfigBo = sysConfigBo;
	}
	
}
