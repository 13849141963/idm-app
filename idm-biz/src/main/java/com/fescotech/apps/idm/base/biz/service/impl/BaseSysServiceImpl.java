package com.fescotech.apps.idm.base.biz.service.impl;

import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.biz.bo.IBaseSysBo;
import com.fescotech.apps.idm.base.biz.service.IBaseSysService;
import com.fescotech.apps.idm.base.domain.BaseSys;

public class BaseSysServiceImpl implements IBaseSysService {

	private IBaseSysBo baseSysBo;
	public IBaseSysBo getBaseSysBo() {
		return baseSysBo;
	}

	public void setBaseSysBo(IBaseSysBo baseSysBo) {
		this.baseSysBo = baseSysBo;
	}

	@Override
	public List<BaseSys> queryList(Map<String, Object> map) {
		return baseSysBo.queryBaseSys(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return baseSysBo.queryTotal(map);
	}

	@Override
	public BaseSys queryObject(Long sysId) {
		return baseSysBo.loadBaseSys(sysId);
	}

	@Override
	public void deleteBatch(Long[] sysIds) {
		baseSysBo.deleteBatch(sysIds);
	}

	@Override
	public void save(BaseSys sys) {
		baseSysBo.insert(sys);
	}

	@Override
	public void update(BaseSys sys) {
		baseSysBo.update(sys);
	}

	@Override
	public void changeState(Long[] sysIds) {
		for(Long sysId:sysIds){
			BaseSys sys=baseSysBo.loadBaseSys(sysId);
			if(sys.getOpenFlag()==0){
				sys.setOpenFlag(1);
			}else {
				sys.setOpenFlag(0);
			}
			baseSysBo.update(sys);
		}
		
	}

	@Override
	public List<BaseSys> queryList(BaseSys baseSys) {
		return baseSysBo.queryBaseSysBySys(baseSys);
	}
	
	@Override
	public BaseSys loadBaseSys(BaseSys baseSys) {
        BaseSys baseSys1 = baseSysBo.loadBaseSysBySys(baseSys);
        return baseSysBo.loadBaseSysBySys(baseSys);
	}
}

