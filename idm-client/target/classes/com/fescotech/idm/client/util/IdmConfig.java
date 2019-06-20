package com.fescotech.idm.client.util;
import com.fescotech.idm.client.exception.IdmException;
/**
 * IDM客户端应用属性配置读取
 */
public final class IdmConfig {
	private java.util.Properties props=new java.util.Properties();
	private static IdmConfig instance = null;
	public static synchronized IdmConfig me() {		  
		if (instance == null) {
			instance = new IdmConfig();
			instance.init();
		}		 
		return instance;
	}
 
	
	private void init(){
		try{
			props.load(IdmConfig.class.getResourceAsStream("/idmconfig.properties"));			 
		}catch(Exception e){
			throw new IdmException("装载系统配置文件【idmconfig.properties】失败",e);
		}		
	}
 
	private boolean isEmptyByTrim(String src){
		if(src==null || "".equals(src.trim()))
			return true;
		return false;
	}
	/**
	 * 获取配置参数
	 * @param key
	 * @return
	 */
	public String getPropValue(String key){
	    String propValue = props.getProperty(key); 
	    if(isEmptyByTrim(propValue))
		return propValue;
	    return  this.translateProperty(propValue);
	}
	/**
	 * 获取配置参数，若参数不存在则返回默认值
	 * @param key  配置参数名称
	 * @param defaultValue  默认值
	 * @return
	 */
	public String getPropValue(String key,String defaultValue){
	    String propValue = props.getProperty(key,defaultValue);
	    if(isEmptyByTrim(propValue))
		return propValue;
	    return  this.translateProperty(propValue);
	}
	 
	/**
	 * 获取int类型配置参数，若参数不存在则返回默认值
	 * @param key 配置参数名称
	 * @param defaultValue 默认值
	 * @return
	 */
	public int getPropValueAsInt(String key,int defaultValue){
		String cfg= getPropValue(key);
		if(cfg==null)
		    return defaultValue;		
		else
		{
			return Integer.parseInt(cfg.trim());
		}
	}
	/**
	 *  获取boolean类型配置参数，若参数不存在则返回默认值
	 * @param key 配置参数名称
	 * @param defaultValue 默认值
	 * @return
	 */
	public boolean getPropValueAsBool(String key,boolean dv){
		String cfg= getPropValue(key);
		if(cfg==null)return dv;
		else
		{
			return Boolean.parseBoolean(cfg.trim().toLowerCase());
		}
	}
	
	
	/**
	 * 将属性值中变量替换为正确的变量值
	 * @param expression
	 * @return
	 */
	private String translateProperty(String expression) {
		while (true) {
			int x = expression.indexOf("${");
			int y = expression.indexOf("}", x);
			if ((x != -1) && (y != -1)) {
				String var = expression.substring(x + 2, y);
				String o = props.getProperty(var);
				if (o != null) {
					expression = expression.substring(0, x) + o + expression.substring(y + 1);
				} else {
					expression = expression.substring(0, x)	+ expression.substring(y + 1);
				}
			} else {
				break;
			}
		}
		if(!isEmptyByTrim(expression) ){
		    int idx1 = expression.indexOf("${");
		    int idx2 = expression.indexOf("}");
		    if(idx1!=-1 && idx2>idx1)
		    {
			return translateProperty(expression);
		    }
		}
		return expression;
	}
}