package com.fescotech.apps.idm.base.biz.bo.impl;
import java.util.List;
import java.util.Map;

import com.fesco.pafa.app.biz.bo.impl.AbstractBoT;
import com.fescotech.apps.idm.base.biz.bo.IBaseCorpinfoBo;
import com.fescotech.apps.idm.base.domain.BaseCorpinfo;
import com.fescotech.apps.idm.base.domain.vo.BaseCorpinfoVo;

public class BaseCorpinfoBo extends AbstractBoT implements IBaseCorpinfoBo{

	public void insert(BaseCorpinfo baseCorpinfo){
		baseDao.insert("baseCorpinfoSql.insertBaseCorpinfo",baseCorpinfo);
	}
	
	public void update(BaseCorpinfo baseCorpinfo){
		baseDao.update("baseCorpinfoSql.updateBaseCorpinfo",baseCorpinfo);
	}
	
	public List<BaseCorpinfo> queryBaseCorpinfo(BaseCorpinfo baseCorpinfo){
		return baseDao.queryForList("baseCorpinfoSql.queryBaseCorpinfo",baseCorpinfo,BaseCorpinfo.class);
	}
	
	public BaseCorpinfo loadBaseCorpinfo(Long corpId){
		return (BaseCorpinfo)baseDao.queryForObject("baseCorpinfoSql.queryByKey",corpId);
	}
	public int queryBaseCorpinfoCount(BaseCorpinfo baseCorpinfo){
		return (int)baseDao.queryForObject("baseCorpinfoSql.queryBaseCorpinfoCount",baseCorpinfo);
	}
	@Override
	public List<BaseCorpinfoVo> queryTreeList() {
		return baseDao.queryForList("baseCorpinfoSql.queryTreeList",null,BaseCorpinfoVo.class);
	}

	@Override
	public List<BaseCorpinfo> queryList(Map<String, Object> map) {
		return baseDao.queryForList("baseCorpinfoSql.queryList",map,BaseCorpinfo.class);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return (int)baseDao.queryForObject("baseCorpinfoSql.queryTotal",map);
	}

	@Override
	public BaseCorpinfo get(Long corpId) {
		return (BaseCorpinfo)baseDao.queryForObject("baseCorpinfoSql.queryBaseCorpinfoByKey",corpId);
	}

	@Override
	public void deleteBatch(Long[] corpIds) {
		baseDao.delete("baseCorpinfoSql.deleteBatch", corpIds);	
		
	}
}