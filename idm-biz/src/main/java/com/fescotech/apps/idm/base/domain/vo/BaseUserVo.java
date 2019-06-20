package com.fescotech.apps.idm.base.domain.vo;
import java.util.List;
import com.fescotech.apps.idm.base.domain.BaseUser;
public class BaseUserVo extends BaseUser {
	/**用户权限**/
	private List<String> perms;
	/**
	 * 公司名称
	 */
	private Long corpName; 
	
	/**
	 * 用户所属机构名称
	 */
	private String orgName;
	
	public List<String> getPerms() {
		return perms;
	}
	public void setPerms(List<String> perms) {
		this.perms = perms;
	}
	public Long getCorpName() {
		return corpName;
	}
	public void setCorpName(Long corpName) {
		this.corpName = corpName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
}