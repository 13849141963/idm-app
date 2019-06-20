package com.fescotech.apps.idm.base.biz.bo;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseSys;

public interface IBaseSysBo {

	List<BaseSys> queryBaseSys(Map<String, Object> query);

	int queryTotal(Map<String, Object> queryData);

	BaseSys loadBaseSys(Long sysId);


	void insert(BaseSys sys);

	void update(BaseSys sys);

	List<BaseSys> queryBaseSysBySys(BaseSys sys);

	void deleteBatch(Long[] sysIds);

	BaseSys loadBaseSysBySys(BaseSys sys);

	

}
