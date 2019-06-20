package com.fescotech.idm.client.model;
/**
 * api返回客户端对象
 */
public class RemoteResult  {
	public static final String SUCCESS = "0";
	public static final String FAIL = "1";
    private String code;
    private String errorMsg;
    private Object successResult;

    public RemoteResult(String code, String errorMsg, Object successResult) {
        super();
        this.code = code;
        this.errorMsg = errorMsg;
        this.successResult = successResult;
    }

    public RemoteResult() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getSuccessResult() {
        return successResult;
    }

    public void setSuccessResult(Object successResult) {
        this.successResult = successResult;
    }
    
	/***
	 * 创建成功结果
	 * @param t 结果对象
	 * @return
	 */
	public static   RemoteResult createSuccessResult(Object t){
		return new RemoteResult(RemoteResult.SUCCESS, null, t);
	}

	/***
	 * 创建成功结果
	 * @return
	 */
	public static  RemoteResult createSuccessResult(){
		return new RemoteResult(RemoteResult.SUCCESS, null, null);
	}
	
	/***
	 * 创建失败结果
	 * @param msg 错误消息
	 * @return
	 */
	public static   RemoteResult  createFailResult(String msg){
		return new RemoteResult(RemoteResult.FAIL, msg, null);
	}
	
	/***
	 * 创建失败结果
	 * @param code 错误代码
	 * @param msg 错误消息
	 * @return
	 */
	public static   RemoteResult createFailResult(String code, String msg){
		return new RemoteResult(code, msg, null);
	}
	
	/***
	 * 创建失败结果
	 * @param code 错误代码
	 * @param msg 错误消息
	 * @param obj 结果对象
	 * @return
	 */
	public static  RemoteResult createFailResult(String code, String msg, Object obj){
		return new RemoteResult(code, msg, obj);
	}

	@Override
	public String toString() {
		return "RemoteResult [code=" + code + ", errorMsg=" + errorMsg + ", successResult=" + successResult + "]";
	}

}
