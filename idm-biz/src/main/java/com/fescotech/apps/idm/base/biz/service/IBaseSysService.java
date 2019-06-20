package com.fescotech.apps.idm.base.biz.service;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseSys;

public interface IBaseSysService {

	List<BaseSys> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);


	void save(BaseSys app);

	void update(BaseSys app);

	void changeState(Long[] sysIds);

	List<BaseSys> queryList(BaseSys baseSys);

	void deleteBatch(Long[] sysIds);

	BaseSys queryObject(Long sysId);

	BaseSys loadBaseSys(BaseSys baseSys);
}
