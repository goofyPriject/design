package cn.aireco.platform.sdk.response;


import cn.aireco.platform.sdk.common.SdkConfig;
import lombok.Data;

/**
 * 返回对象，后续返回对象都要继承这个类
 */
@Data
public  class BaseResponseSimple {

	private static String SUCCESS_CODE = SdkConfig.SUCCESS_CODE;

	private String code;
	private String msg;
	private String data;

	public boolean isSuccess() {
		return SUCCESS_CODE.equals(code);
	}

}
