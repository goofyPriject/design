package cn.aireco.platform.sdk.response;


import cn.aireco.platform.sdk.common.SdkConfig;

/**
 * 返回对象，后续返回对象都要继承这个类
 * @param <T> 对应的data字段映射的对象
 */
public abstract class BaseResponse<T> {

	private static String SUCCESS_CODE = SdkConfig.SUCCESS_CODE;

	private String code;
	private String msg;
	private T data;

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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
