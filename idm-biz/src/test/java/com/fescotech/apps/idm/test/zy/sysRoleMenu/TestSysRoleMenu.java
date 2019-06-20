package com.fescotech.apps.idm.test.zy.sysRoleMenu;

import com.fescotech.apps.idm.admin.biz.bo.ISysRoleMenuBo;
import com.fescotech.apps.idm.admin.domain.SysRoleMenu;
import com.fescotech.apps.idm.test.AbsRollbackTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TestSysRoleMenu extends AbsRollbackTest {

    @Autowired
    private ISysRoleMenuBo sysRoleMenuBo;


    //
    @Test
    public void test(){
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setRoleId(1l);
        sysRoleMenu.setMenuId(222l);
        sysRoleMenuBo.insert(sysRoleMenu);


    }
}
