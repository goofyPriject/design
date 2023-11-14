package cn.aireco.platform.sdk.request;

import cn.aireco.platform.sdk.response.HelloResponse;

public class HelloRequest extends BaseRequest<HelloResponse> {
    @Override
    public String name() {
        return "hello";
    }
}
