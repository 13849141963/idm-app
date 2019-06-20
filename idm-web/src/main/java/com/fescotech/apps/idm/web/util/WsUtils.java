package com.fescotech.apps.idm.web.util;

import com.fesco.fws.sign.SignUtil;
import com.fesco.fws.utils.JsonUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.namespace.QName;
import net.sf.json.JSONObject;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.ConnectionType;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

public class WsUtils {
    private static final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
    private static final long CONNECTION_TIME = 30000L;
    private static final ConnectionType CONNECTION_TYPE;

    static {
        CONNECTION_TYPE = ConnectionType.CLOSE;
    }

    public WsUtils() {
    }

    private static Client getClient(String url) {
        return dcf.createClient(url);
    }

    public static Object[] invoke(String url, String methodName, Object... params) throws Exception {
        Client client = getClient(url);
        HTTPConduit http = (HTTPConduit)client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(30000L);
        httpClientPolicy.setConnection(CONNECTION_TYPE);
        http.setClient(httpClientPolicy);
        return client.invoke(methodName, params);
    }

    public static String axis2Invoke(String method, String para, String url, String nameSpace, String appKey, String appSecret) {
        RPCServiceClient serviceClient = null;

        try {
            serviceClient = new RPCServiceClient();
        } catch (AxisFault var19) {
            var19.printStackTrace();
        }

        Options options = serviceClient.getOptions();
        EndpointReference targetEPR = new EndpointReference(url);
        options.setTo(targetEPR);
        Map<String, String> paranMap = new HashMap();
        paranMap.put("appkey", appKey);
        paranMap.put("format", "json");
        if (para == null) {
            para = "{}";
        } else if (para.indexOf("[") == 0) {
            paranMap.put("list", para);
            para = "{list:" + para + "}";
        } else {
            JSONObject jo = JSONObject.fromObject(para);
            Iterator iterator = jo.keys();

            while(iterator.hasNext()) {
                String key = (String)iterator.next();
                String value = jo.getString(key);
                paranMap.put(key, value);
            }
        }

        String jsonParam = JsonUtils.toJSONString(paranMap);
        String mysign = null;

        try {
            mysign = SignUtil.mySign(appKey, appSecret, jsonParam, "json");
        } catch (Exception var18) {
            var18.printStackTrace();
        }

        Object[] entryArgs = new Object[]{appKey, mysign, para, "json"};
        Class[] classes = new Class[]{String.class};
        QName opName = new QName(nameSpace, method);
        String result = null;

        try {
            result = (String)serviceClient.invokeBlocking(opName, entryArgs, classes)[0];
        } catch (AxisFault var17) {
            var17.printStackTrace();
        }

        return result;
    }

    public static Object[] invoke2(String url, String nameSpace, String param, Object[] entry, Class[] returnTypes) throws Exception {
        try {
            RPCServiceClient serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();
            EndpointReference targetEPR = new EndpointReference(url);
            options.setTo(targetEPR);
            options.setTimeOutInMilliSeconds(600000L);
            QName opName = new QName(nameSpace, param);
            Object[] results = serviceClient.invokeBlocking(opName, entry, returnTypes);
            serviceClient.cleanupTransport();
            serviceClient.cleanup();
            serviceClient = null;
            return results;
        } catch (Exception var10) {
            var10.printStackTrace();
            throw new Exception(var10);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(invoke("http://10.0.45.76:9012/socialService?wsdl", "getAllMethod", "fesco", "d31fae18a821e71fc004044d00ef4033")[0]);
        System.out.println(invoke("http://10.0.45.76:9012/socialService?wsdl", "getAllMethod", "fesco", "d31fae18a821e71fc004044d00ef4033")[0]);
    }
}
