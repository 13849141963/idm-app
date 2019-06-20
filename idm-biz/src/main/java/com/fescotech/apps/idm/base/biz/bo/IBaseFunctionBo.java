package com.fescotech.apps.idm.base.biz.bo;
import java.util.List;
import java.util.Map;

import com.fescotech.apps.idm.base.domain.BaseFunction;
import com.fescotech.apps.idm.base.domain.vo.BaseFunctionVo;
public interface IBaseFunctionBo {
	public void insert(BaseFunctionVo baseFunctionVo);
	
	public void update(BaseFunctionVo baseFunctionVo);
	
	public List<BaseFunctionVo> queryBaseFunction(BaseFunction baseFunction);

	public BaseFunctionVo loadBaseFunction(Long functionId);
	
	public int queryBaseFunctionCount(BaseFunction baseFunction);
	
	public List<BaseFunction> queryBaseFunctionList(Map<String,Object> queryCon,int offset,int limit);
	
	public List<BaseFunctionVo> queryList(Map<String, Object> map);
	
	public void deleteBatch(Long[] functionIds);
}