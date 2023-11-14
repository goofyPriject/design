package cn.aireco.platform.sdk.request;


import cn.aireco.platform.sdk.response.CommonResponse;

/**
"OMS"
 */
public class CommonRequest extends BaseRequest<CommonResponse> {

    public CommonRequest(String name) {
        this.setName(name);
    }

    public CommonRequest(String name, String version) {
        this.setName(name);
        this.setVersion(version);
    }

    @Override
    public String name() {
        return "";
    }
}
