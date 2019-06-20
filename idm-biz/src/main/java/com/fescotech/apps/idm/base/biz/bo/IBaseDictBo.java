package com.fescotech.apps.idm.base.biz.bo;
import com.fescotech.apps.idm.base.domain.BaseDict;
import java.util.List;
import java.util.Map;
public interface IBaseDictBo {
	public void insert(BaseDict baseDict);
	
	public void update(BaseDict baseDict);
	
	public List<BaseDict> queryBaseDict(BaseDict baseDict);

	public BaseDict loadBaseDict(Long dictId);
	
	public int queryBaseDictCount(BaseDict baseDict);
	
	public List<BaseDict> queryBaseDictList(Map<String,Object> queryCon,int offset,int limit);
	
	public BaseDict queryDictItem(String dictType,String dictCode);
	
	public List<BaseDict> queryBaseDictItemList(String dictType);
}