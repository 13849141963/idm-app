package com.fescotech.apps.idm.test.zy.org;

import com.fescotech.apps.idm.base.biz.service.IBaseOrganizationService;
import com.fescotech.apps.idm.base.domain.BaseCorpinfo;
import com.fescotech.apps.idm.base.domain.BaseOrganization;
import com.fescotech.apps.idm.test.AbsRollbackTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestOrg extends AbsRollbackTest {


    @Autowired
    private IBaseOrganizationService baseOrganizationService;


    @Test
    public void test(){

       /* BaseCorpinfo corp = new BaseCorpinfo();

        BaseOrganization org = new BaseOrganization();
        org.setAddress("地址");
        org.setOrgName("1");
        org.setDescription("1");
        org.setShortName("简称");
        org.setParentId(88l);
        baseOrganizationService.save(org);*/

       // baseOrganizationService.queryList(map);
    }
}
