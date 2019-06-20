package com.fescotech.apps.idm.test.zy;

import com.fescotech.apps.idm.admin.biz.bo.ISysMenuBo;
import com.fescotech.apps.idm.admin.biz.bo.ISysUserBo;
import com.fescotech.apps.idm.base.biz.bo.IBaseDataBo;
import com.fescotech.apps.idm.base.biz.bo.IBaseUserDataBo;
import com.fescotech.apps.idm.base.biz.service.IBaseUserDataService;
import com.fescotech.apps.idm.base.biz.service.impl.BaseUserDataService;
import com.fescotech.apps.idm.base.domain.BaseData;
import com.fescotech.apps.idm.base.domain.BaseUserData;
import com.fescotech.apps.idm.test.AbsRollbackTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

public class TestBo extends AbsRollbackTest {
    @Autowired
	private ISysUserBo sysUserBo;
    @Autowired
    private ISysMenuBo sysMenuBo;

    @Autowired
	private IBaseDataBo baseDataBo;
    
    /*@Test
	public void getUser(){
		Long userId =1L;
		SysUser su = sysUserBo.loadSysUser(userId);
		Assume.assumeNotNull(su);
	}
	
    *//* @Test
	public void getMenuTest(){
    	*//**//*SysMenuVo query = new SysMenuVo();
		List<SysMenuVo> menuIds = sysMenuBo.queryList(query, 2, 2);
		System.out.println(menuIds);*//**//*

         BaseData baseData = new BaseData();


         *//**//*baseData.setDataName("123");
         baseData.setDataType("fesco");
         baseDataBo.insert(baseData);
         *//**//*
         Map<String, Object> queryData = new HashMap<String, Object>();

         List<BaseData> baseData1 = baseDataBo.queryList(queryData);
         for (BaseData data : baseData1) {
             System.out.println(data);
         }


     }
    @Autowired
    private BaseDataService baseDataService;
    @Autowired
    private IBaseCorpinfoService baseCorpinfoService;
     //
     *//**//*@Test
     public void test(){

          *//**//**//**//*Map<String, Object> map = new HashMap<String, Object>();
         map.put("start", (0));
         map.put("end", 10);

         List<BaseCorpinfo> corpList = baseCorpinfoService.queryList(map);
         int total = baseCorpinfoService.queryTotal(map);

         Pager pageUtil = new Pager(corpList, total, 10, 0);
         System.out.println(pageUtil);*//**//**//**//*



         Map<String, Object> map = new HashMap<String, Object>();
         map.put("start", 0);
         map.put("end", 10);

         List<BaseData> resultAppList = new ArrayList<BaseData>();
         //分页查询数据
         List<BaseData> baseData = baseDataService.queryList(map);
         //查询总条数
         int total = baseDataService.queryTotal();


         Pager pageUtil = new Pager(resultAppList, total, 10, 0);
         R page = R.ok().put("page", pageUtil);
         System.out.println(page.get("page"));




     }*/
    /* @Autowired
     private BaseFunctionBo baseFunctionBo;
     //
     @Test
     public void tests(){
         *//*BaseFunctionVo ba = new BaseFunctionVo();
         ba.setFunctionName("数据权限集管理");
         ba.setUrl("/base/data/list");
         //权限管理,145
         //权限管理,213
         ba.setParentId(145l);
         ba.setIsLeaf(0);
         ba.setFunctionType(1);
         ba.setSysId(104);
         ba.setCreatorName("ADMIN");
         ba.setCreatorOrg(20l);
         ba.setPerm("base:data:list");
         baseFunctionBo.insert(ba);*//*
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("start", 0);
         map.put("end", 1000);
         List<BaseFunctionVo> baseData = baseFunctionBo.queryList(map);
         for (BaseFunctionVo baseDatum : baseData) {
             if(baseDatum.getFunctionName().equals("应用管理")) {
                 System.out.println(baseDatum.getFunctionName()+""+baseDatum.getUrl());
             }
         }


     }*/
     //
     @Test
     public void testss(){
         BaseData baseData = new BaseData();
         baseData.setDataId(1);
         baseData.setDataName("账号权限集");
         baseData.setDataType("fesco");
         List<BaseData> baseData1 = baseDataBo.queryByData(baseData);
         for (BaseData data : baseData1) {
             System.out.println(data.getDataName());
         }

     }
    //添加权限集
    @Test
    public void insert(){
        BaseData baseData = new BaseData();
        baseData.setDataId(1);
        baseData.setDataName("FESCO权限集");
        baseData.setDataType("FESCO");
       baseDataBo.insert(baseData);

    }

    //数据权限集的模糊查询
    @Test
    public void testlisk(){
       /* BaseData baseData = new BaseData();
        baseData.setDataName("集");
        List<BaseData> baseData1 = baseDataBo.queryByDataName(baseData);
        for (BaseData data : baseData1) {
            System.out.println(data.getDataName());
        }
        int i = baseDataBo.queryFuzzyTotal(baseData);
        System.out.println(i);*/
    }








}