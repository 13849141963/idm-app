package com.fescotech.apps.idm.test.zy.user;
import com.fescotech.apps.idm.base.biz.bo.IBaseUserDataBo;
import com.fescotech.apps.idm.base.biz.service.impl.BaseUserDataService;
import com.fescotech.apps.idm.base.domain.BaseUserData;
import com.fescotech.apps.idm.base.domain.vo.BaseUserDataVo;
import com.fescotech.apps.idm.test.AbsRollbackTest;
import com.ibatis.sqlmap.client.SqlMapClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class TestBaseUserDate extends AbsRollbackTest {

    @Autowired
    private IBaseUserDataBo baseUserDataBo;
    @Autowired
    private BaseUserDataService baseUserDataService;




    @Test
    public void testliskss(){

       /* BaseUserData ba = new BaseUserData();
       //ba.setBase_id(23l);
        ba.setUser_id(142l);
        ba.setBase_id(1L);

        BaseUserData ba1 = new BaseUserData();
        //ba.setBase_id(25l);
        //ba.setBase_id(1l);
        ba1.setUser_id(143l);
        ba1.setBase_id(1L);


        BaseUserData ba2 = new BaseUserData();
       // ba.setBase_id(24l);
        //ba.setBase_id(1l);
        ba2.setUser_id(144l);
        ba2.setBase_id(1L);

*//*

        List<BaseUserData> list = new ArrayList<BaseUserData>();
        list.add(ba1);
        list.add(ba2);
        list.add(ba);
*//*

        baseUserDataBo.insertList(//list);*/


        List<BaseUserData> lists = new ArrayList<>();

        Long[] ss = {7l,8l,9l};
        long userId= 1l;
        for (Long s : ss) {
            BaseUserData baseUserData = new BaseUserData();
            baseUserData.setBase_id(s);
            baseUserData.setUser_id(userId);
            lists.add(baseUserData);
        }



        for (BaseUserData list : lists) {
            System.out.println(list.getBase_id());
        }


        baseUserDataBo.insertList(lists);












        //ba.setUser_base_id(10l);

        //baseUserDataBo.insert(ba);

        //Long[] ss = {7l,8l,9l};
        // baseUserDataBo.deleteBatch(ss);

        //baseUserDataBo.update(ba);
       /* HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("start", 0);
        map.put("end", 10);
        List<BaseUserData> baseUserData = baseUserDataBo.queryList(map);

        for (BaseUserData baseUserDatum : baseUserData) {
            System.out.println(baseUserDatum.getUser_base_id());
        }*/
        /*int i = baseUserDataBo.queryTotal();
        System.out.println(i);*/

        //baseUserDataService.insert(ba);
        //baseUserDataService.deleteBatch(ss);
        //baseUserDataService.update(ba);

        /*HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("start", 0);
        map.put("end", 10);
        List<BaseUserData> baseUserData = baseUserDataService.queryList(map);
        for (BaseUserData baseUserDatum : baseUserData) {
            System.out.println(baseUserDatum.getUser_base_id());
        }*/

        /*List<BaseUserDataVo> baseUserData = baseUserDataService.queryByData(ba);
        for (BaseUserDataVo baseUserDatum : baseUserData) {
            System.out.println(baseUserDatum.getLoginName());
        }*/
      /*  int total = baseUserDataService.queryByDataCount(ba);
        System.out.println(total);

        List<BaseUserDataVo> list = baseUserDataService.queryByData(ba);

        System.out.println(list.size());*/

       /* Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", (1 - 1) * 10);
        map.put("end", 1*10);

        //分页查询数据
        List<BaseUserData> baseuSERData = baseUserDataService.queryList(map);

        for (BaseUserData baseuSERDatum : baseuSERData) {
            System.out.println(baseuSERDatum);
        }*/
        /*List<BaseUserDataVo> list = baseUserDataService.queryByData(ba);
        for (BaseUserDataVo baseUserDataVo : list) {
            System.out.println(baseUserDataVo.getLoginName());
        }*/

    }


}