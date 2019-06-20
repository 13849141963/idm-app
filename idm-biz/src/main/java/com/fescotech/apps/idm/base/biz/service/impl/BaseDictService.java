package com.fescotech.apps.idm.base.biz.service.impl;

import java.util.List;

import com.fescotech.apps.idm.base.biz.bo.IBaseDictBo;
import com.fescotech.apps.idm.base.biz.service.IBaseDictService;
import com.fescotech.apps.idm.base.domain.BaseDict;

public class BaseDictService implements IBaseDictService {
	private IBaseDictBo baseDictBo;
	public void setBaseDictBo(IBaseDictBo baseDictBo) {
		this.baseDictBo = baseDictBo;
	}

	@Override
	public BaseDict queryDictName(String dictType, String itemCode) {
		BaseDict dict = new BaseDict();
		dict.setDictCode(itemCode);
		dict.setDictType(dictType);
		return baseDictBo.queryDictItem(dictType,itemCode);
	}

	@Override
	public List<BaseDict> queryBaseDictItemList(String dictType) {
		return baseDictBo.queryBaseDictItemList(dictType);
	}

}
