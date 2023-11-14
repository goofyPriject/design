package cn.aireco.platform.sdk.client;


import cn.aireco.platform.sdk.common.OpenConfig;
import cn.aireco.platform.sdk.common.RequestForm;
import cn.aireco.platform.sdk.request.BaseRequest;
import cn.aireco.platform.sdk.response.BaseResponse;
import cn.aireco.platform.sdk.util.JsonUtil;
import cn.aireco.platform.sdk.util.SignUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求客户端
"OMS"
 */
public class OpenClient {
    private static final String ACCEPT_LANGUAGE = "Accept-Language";
    private static final String AUTHORIZATION = "Authorization";
    private static final String PREFIX_BEARER = "Bearer ";
    private static final OpenConfig DEFAULT_CONFIG = new OpenConfig();

    private String url;
    private String appKey;
    private String secret;

    private OpenConfig openConfig;
    private OpenRequest openRequest;

    public OpenClient(String url, String appKey, String secret) {
        this(url, appKey, secret, DEFAULT_CONFIG);
    }

    public OpenClient(String url, String appKey, String secret, OpenConfig openConfig) {
        if (openConfig == null) {
            throw new IllegalArgumentException("openConfig不能为null");
        }
        this.url = url;
        this.appKey = appKey;
        this.secret = secret;
        this.openConfig = openConfig;

        this.openRequest = new OpenRequest(openConfig);
    }

    /**
     * 请求接口
     * @param request 请求对象
     * @return 返回Response
     */
    public String execute(BaseRequest request) {
        return this.execute(request, null);
    }

    /**
     * 请求接口
     * @param request 请求对象
     * @param jwt jwt
     * @return 返回Response
     */
    public String execute(BaseRequest request, String jwt) {
        RequestForm requestForm = request.createRequestForm();
        // 表单数据
        Map<String, Object> form = requestForm.getForm();

        form.put(this.openConfig.getAppKeyName(), this.appKey);
        // 将data部分转成json并urlencode
        Object data = form.get(this.openConfig.getDataName());
        String dataJson = JsonUtil.toJSONString(data);
        dataJson = URLUtil.encode(dataJson);
        form.put(this.openConfig.getDataName(), dataJson);
        // 生成签名，并加入到form中
        String sign = SignUtil.createSign(form, this.secret);
        form.put(this.openConfig.getSignName(), sign);

        // 构建http请求header
        Map<String, String> header = this.buildHeader(jwt);

        String resp = doExecute(this.url, requestForm, header);

        return resp;
    }

    protected String doExecute(String url, RequestForm requestForm, Map<String, String> header) {
        return openRequest.request(this.url, requestForm, header);
    }

    protected <T extends BaseResponse<?>> T parseResponse(String resp, BaseRequest<T> request) {
        return JsonUtil.parseObject(resp, request.getResponseClass());
    }

    protected Map<String, String> buildHeader(String jwt) {
        Map<String, String> header = new HashMap<String, String>();
        header.put(ACCEPT_LANGUAGE, this.openConfig.getLocale());
        if (StrUtil.isNotEmpty(jwt)) {
            header.put(AUTHORIZATION, PREFIX_BEARER + jwt);
        }
        return header;
    }

    public OpenRequest getOpenRequest() {
        return openRequest;
    }

    public void setOpenRequest(OpenRequest openRequest) {
        this.openRequest = openRequest;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public OpenConfig getOpenConfig() {
        return openConfig;
    }

}
