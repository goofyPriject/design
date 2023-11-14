package cn.aireco.platform.sdk.request;


import cn.aireco.platform.sdk.response.GetGoodsResponse;

public class GetGoodsRequest extends BaseRequest<GetGoodsResponse> {
    @Override
    public String name() {
        return "goods.get";
    }
}
