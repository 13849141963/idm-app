package com.fescotech.apps.idm.web.util;


import net.sf.json.JSONObject;

import java.util.HashMap;

import static com.fescotech.apps.idm.web.util.WsUtils.*;

/**
 * 客户接口调用工具
 */
public class WebServerUtils {
    /**
     * @param url webservice 服务端访问URL
     */
    private static String url = "http://10.0.75.151:9001/hrService";
    /**
     * @param nameSpace webservice 服务端访问空间
     */
    private static String nameSpace = "http://hr.fws.fesco.com/";
    /**
     * @param appKey   接口appkey
     */
    private static String appKey = "ElectricitySystem";

    /**
     * @param Sign   接口签名
     */
    private static String Sign = "c5205611a0939282b3d72dac5f4383b1";




    /**
     * 根据客户id查询客户信息
     * @param para  String para = "{\"custIds\":\"251036\"}";
     * @return
     */
    public static String getCustInfoById(String para) {
        String returnFromWS = axis2Invoke("getCustInfoById", para, url, nameSpace, appKey, Sign);
        return returnFromWS;
    }

    /**
     * 根据客户id查询客户对应的业务客户 参数json 类型字符串
     * @param para String para = "{\"custId\":\"251036\"}";
     * @return
     */
    public static String getBusiCustListByCustId(String para) {
        String returnFromWS = axis2Invoke("getBusiCustListByCustId", para, url, nameSpace, appKey, Sign);
        return returnFromWS;
    }


    /**
     * 根据客户名称查询多个客户 分页 pageIndex： 第几页码数字 ， pageSize    一页多少条数据 companyName 客户名称
     * @param pageIndex
     * @param pageSize
     * @param companyName
     */
    public static String getCustInfoByCompanyName(int pageIndex, int pageSize, String companyName) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        map.put("companyName", companyName);
        String para = JSONObject.fromObject(map).toString();
        String returnFromWS = axis2Invoke("getCustInfoByCompanyName", para, url, nameSpace, appKey, Sign);
        return returnFromWS;
    }

}
