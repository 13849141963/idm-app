package com.fescotech.apps.idm.api.util;

import org.apache.shiro.crypto.hash.Sha256Hash;

public class CheckUtil {
	public static String getSign(String idmKey, String loginName, String timeStamp,
			String userPwd) {
		return new Sha256Hash(idmKey+loginName+timeStamp+userPwd).toHex();
	}

	public static String getSignNoPwd(String idmKey, String roleId, String timeStamp) {
		return new Sha256Hash(idmKey+roleId+timeStamp).toHex();
	}
}
