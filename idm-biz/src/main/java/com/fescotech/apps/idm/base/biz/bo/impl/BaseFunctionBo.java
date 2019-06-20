package com.fescotech.apps.idm.base.biz.bo.impl;
import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseFunctionBo;
import com.fescotech.apps.idm.base.domain.BaseFunction;
import com.fescotech.apps.idm.base.domain.vo.BaseFunctionVo;

 
public class BaseFunctionBo extends AbstractBoT implements IBaseFunctionBo{

	public void insert(BaseFunctionVo baseFunctionVo){
		baseDao.insert("baseFunctionSql.insertBaseFunction",baseFunctionVo);
	}
	
	public void update(BaseFunctionVo baseFunctionVo){
		baseDao.update("baseFunctionSql.updateBaseFunction",baseFunctionVo);
	}
	
	public List<BaseFunctionVo> queryBaseFunction(BaseFunction baseFunction){
		return baseDao.queryForList("baseFunctionSql.queryBaseFunction",baseFunction,BaseFunctionVo.class);
	}
	
	public BaseFunctionVo loadBaseFunction(Long functionId){
		return (BaseFunctionVo)baseDao.queryForObject("baseFunctionSql.loadBaseFunctionByKey",functionId);
	}
	public int queryBaseFunctionCount(BaseFunction baseFunction){
		return (int)baseDao.queryForObject("baseFunctionSql.queryBaseFunctionCount",baseFunction);
	}
	
	public List<BaseFunction> queryBaseFunctionList(Map<String,Object> querybaseFunctionParams,int offset,int limit){
	   return baseDao.queryForList("baseFunctionSql.queryBaseFunctionList",querybaseFunctionParams,BaseFunction.class,offset,limit);
	}

	@Override
	public List<BaseFunctionVo> queryList(Map<String, Object> map) {
		return baseDao.queryForList("baseFunctionSql.queryList",map,BaseFunctionVo.class);
	}

	@Override
	public void deleteBatch(Long[] functionIds) {
		baseDao.delete("baseFunctionSql.deleteBatch", functionIds);
	}
}