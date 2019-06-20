package com.fescotech.apps.idm.test.bo;
import java.util.List;

import org.junit.Assume;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fescotech.apps.idm.admin.biz.bo.ISysMenuBo;
import com.fescotech.apps.idm.admin.biz.bo.ISysUserBo;
import com.fescotech.apps.idm.admin.domain.SysUser;
import com.fescotech.apps.idm.admin.domain.vo.SysMenuVo;
import com.fescotech.apps.idm.test.AbsRollbackTest;

public class TestBo  extends AbsRollbackTest {
    @Autowired
	private ISysUserBo sysUserBo;
    @Autowired
    private ISysMenuBo sysMenuBo;
    
    @Test
	public void getUser(){
		Long userId =1L;
		SysUser su = sysUserBo.loadSysUser(userId);
		Assume.assumeNotNull(su);
	}
	
     @Test
	public void getMenuTest(){
    	SysMenuVo query = new SysMenuVo();
		List<SysMenuVo> menuIds = sysMenuBo.queryList(query, 2, 2);
		System.out.println(menuIds);
		 
	}   
}