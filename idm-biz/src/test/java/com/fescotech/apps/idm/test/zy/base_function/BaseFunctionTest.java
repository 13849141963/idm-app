package com.fescotech.apps.idm.test.zy.base_function;

import com.fescotech.apps.idm.base.biz.bo.IBaseFunctionBo;
import com.fescotech.apps.idm.base.domain.BaseFunction;
import com.fescotech.apps.idm.base.domain.vo.BaseFunctionVo;
import com.fescotech.apps.idm.test.AbsRollbackTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class BaseFunctionTest  extends AbsRollbackTest {


    @Autowired
    private IBaseFunctionBo baseFunctionBo;


    //
    @Test
    public void test(){
        BaseFunctionVo b = new BaseFunctionVo();
        b.setFunctionName("查看用户权限集");
        b.setParentId(109l);
        b.setUrl("/base/baseUserData/userData");
        b.setIsLeaf(0);
        b.setFunctionType(1);
        b.setOperTime(new Date());
        b.setSysId(101);
        b.setPerm("base:baseUserData:userData");
        b.setCreator(32l);
        b.setCreatorName("ADMIN");
        b.setCreatorOrg(20l);
        b.setCreateTime(new Date());



        baseFunctionBo.insert(b);




        System.out.println(b.getFunctionId());
        //591查看权限集

        Long[] ss = {591l,592l,593l};

        //baseFunctionBo.deleteBatch(ss);


    }

}
