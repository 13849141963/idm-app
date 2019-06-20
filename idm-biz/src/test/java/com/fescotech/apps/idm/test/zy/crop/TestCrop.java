package com.fescotech.apps.idm.test.zy.crop;

import com.fescotech.apps.idm.base.biz.service.IBaseCorpinfoService;
import com.fescotech.apps.idm.base.biz.service.IBaseOrganizationService;
import com.fescotech.apps.idm.base.domain.BaseCorpinfo;
import com.fescotech.apps.idm.base.domain.BaseOrganization;
import com.fescotech.apps.idm.test.AbsRollbackTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCrop  extends AbsRollbackTest {

    @Autowired
    private IBaseCorpinfoService baseCorpinfoService;
    @Autowired
    private IBaseOrganizationService baseOrganizationService;
    //
    @Test
    public void test(){

        BaseCorpinfo corp = new BaseCorpinfo();

        BaseOrganization org = new BaseOrganization();
		org.setAddress("1");
		org.setOrgName("1");
		org.setDescription("1");
		org.setShortName("1");
		baseOrganizationService.save(org);
    }


    //
    @Test
    public void test1(){
        Integer page = 1;
        Integer limit = 10;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (page - 1) * limit + 1);
        map.put("end", page*limit);
        map.put("orgName","公司");

        //查询列表数据
        List<BaseOrganization> orgList = baseOrganizationService.queryList(map);


        for (BaseOrganization baseCorpinfo : orgList) {
            System.out.println(baseCorpinfo.getOrgName());
        }

    }

}
