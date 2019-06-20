package com.fescotech.apps.idm.base.biz.bo;
import com.fescotech.apps.idm.base.domain.BaseLoginInfo;
import java.util.List;
import java.math.BigDecimal;
public interface IBaseLoginInfoBo {
	public void insert(BaseLoginInfo baseLoginInfo);
	
	public void update(BaseLoginInfo baseLoginInfo);
	
	public List<BaseLoginInfo> queryBaseLoginInfo(BaseLoginInfo baseLoginInfo);

	public BaseLoginInfo loadBaseLoginInfo(Long loginInfoId);
	
	public int queryBaseLoginInfoCount(BaseLoginInfo baseLoginInfo);
}