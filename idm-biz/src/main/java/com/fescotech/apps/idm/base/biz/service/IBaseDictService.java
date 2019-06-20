package com.fescotech.apps.idm.base.biz.service;
import java.util.List;
import com.fescotech.apps.idm.base.domain.BaseDict;
public interface IBaseDictService {
	/**
	 * 根据字典类型和字典项编码获取字典项对象
	 * @param dictType  字典类型
	 * @param itemCode  字典项代码
	 * @return
	 */
	public BaseDict queryDictName(String dictType,String itemCode);
	
	/**
	 * 根据字典类型获取字典项列表
	 * @param dictType 字典类型
	 * @return
	 */
	public List<BaseDict> queryBaseDictItemList(String dictType);
}
