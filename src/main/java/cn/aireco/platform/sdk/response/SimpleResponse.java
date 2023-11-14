package cn.aireco.platform.sdk.response;


import cn.aireco.platform.sdk.common.SdkConfig;

/**
 * 返回对象，后续返回对象都要继承这个类
 */
public  class SimpleResponse {

	private static String SUCCESS_CODE = SdkConfig.SUCCESS_CODE;

	private String code;
	private String msg;
	private String data;

	public boolean isSuccess() {
		return SUCCESS_CODE.equals(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
