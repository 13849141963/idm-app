package com.fescotech.apps.idm.base.biz.bo.impl;
import java.util.List;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseDictBo;
import com.fescotech.apps.idm.base.domain.BaseDict;

import java.util.Map;
public class BaseDictBo extends AbstractBoT implements IBaseDictBo{
	public void insert(BaseDict baseDict){
		baseDao.insert("baseDictSql.insertBaseDict",baseDict);
	}
	
	public void update(BaseDict baseDict){
		baseDao.update("baseDictSql.updateBaseDict",baseDict);
	}
	
	public List<BaseDict> queryBaseDict(BaseDict baseDict){
		return baseDao.queryForList("baseDictSql.queryBaseDict",baseDict,BaseDict.class);
	}
	
	public BaseDict loadBaseDict(Long dictId){
		return (BaseDict)baseDao.queryForObject("baseDictSql.loadBaseDict",dictId);
	}
	public int queryBaseDictCount(BaseDict baseDict){
		return (Integer)baseDao.queryForObject("baseDictSql.queryBaseDictCount",baseDict);
	}
	
	public List<BaseDict> queryBaseDictList(Map<String,Object> querybaseDictParams,int offset,int limit){
	   return baseDao.queryForList("baseDictSql.queryBaseDictList",querybaseDictParams,BaseDict.class,offset,limit);
	}

 
	@Override
	public List<BaseDict> queryBaseDictItemList(String dictType) {
		return baseDao.queryForList("baseDictSql.queryDictItemList",dictType,BaseDict.class);
	}

	@Override
	public BaseDict queryDictItem(String dictType, String dictCode) {
		BaseDict dict =new BaseDict();
		dict.setDictType(dictType);
		dict.setDictCode(dictCode);
		return (BaseDict)baseDao.queryForObject("baseDictSql.queryDictName", dict);
	}
}