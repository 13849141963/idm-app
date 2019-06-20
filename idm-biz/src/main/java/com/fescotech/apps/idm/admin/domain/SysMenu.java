package com.fescotech.apps.idm.admin.domain;
/**
 * 系统菜单定义
 */
public class SysMenu {

	/**
	 * 
	 */
	private String icon; 
	
	/**
	 * 
	 */
	private Long menuId; 
	
	/**
	 * 
	 */
	private String perms; 
	
	/**
	 * 
	 */
	private Integer orderNum; 
	
	/**
	 * 
	 */
	private String menuName; 
	
	/**
	 * 
	 */
	private Integer menuType; 
	
	/**
	 * 
	 */
	private String url; 
	
	/**
	 * 
	 */
	private Long parentId; 
	
	
	/**
	 * 读取
	 */
	public String getIcon(){
		return icon;
	} 
	
	/**
	 * 设置 
	 */
	public void setIcon(String icon){
		this.icon = icon;
	}
	
	/**
	 * 读取
	 */
	public Long getMenuId(){
		return menuId;
	} 
	
	/**
	 * 设置 
	 */
	public void setMenuId(Long menuId){
		this.menuId = menuId;
	}
	
	/**
	 * 读取
	 */
	public String getPerms(){
		return perms;
	} 
	
	/**
	 * 设置 
	 */
	public void setPerms(String perms){
		this.perms = perms;
	}
	
	/**
	 * 读取
	 */
	public Integer getOrderNum(){
		return orderNum;
	} 
	
	/**
	 * 设置 
	 */
	public void setOrderNum(Integer orderNum){
		this.orderNum = orderNum;
	}
	
	
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	/**
	 * 读取
	 */
	public String getUrl(){
		return url;
	} 
	
	/**
	 * 设置 
	 */
	public void setUrl(String url){
		this.url = url;
	}
	
	/**
	 * 读取
	 */
	public Long getParentId(){
		return parentId;
	} 
	
	/**
	 * 设置 
	 */
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	
	
}
