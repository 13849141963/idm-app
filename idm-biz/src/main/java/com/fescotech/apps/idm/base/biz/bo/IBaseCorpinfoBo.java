package com.fescotech.apps.idm.base.biz.bo;
import com.fescotech.apps.idm.base.domain.BaseCorpinfo;
import com.fescotech.apps.idm.base.domain.vo.BaseCorpinfoVo;

import java.util.List;
import java.util.Map;
public interface IBaseCorpinfoBo {
	public void insert(BaseCorpinfo baseCorpinfo);
	
	public void update(BaseCorpinfo baseCorpinfo);
	
	public List<BaseCorpinfo> queryBaseCorpinfo(BaseCorpinfo baseCorpinfo);

	public BaseCorpinfo loadBaseCorpinfo(Long corpId);
	
	public int queryBaseCorpinfoCount(BaseCorpinfo baseCorpinfo);

	List<BaseCorpinfoVo> queryTreeList();

	List<BaseCorpinfo> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	BaseCorpinfo get(Long corpId);

	void deleteBatch(Long[] corpIds);
}