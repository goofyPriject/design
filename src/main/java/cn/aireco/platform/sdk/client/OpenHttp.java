package cn.aireco.platform.sdk.client;


import cn.aireco.platform.sdk.common.OpenConfig;
import cn.aireco.platform.sdk.common.UploadFile;
import okhttp3.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
"OMS"
 */
public class OpenHttp {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private Map<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();

    private OkHttpClient httpClient;
    private OpenConfig openConfig;

    public OpenHttp(OpenConfig openConfig) {
        this.openConfig = openConfig;
        this.initHttpClient(openConfig);
    }

    protected void initHttpClient(OpenConfig openConfig) {
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(openConfig.getConnectTimeoutSeconds(), TimeUnit.SECONDS) // 设置链接超时时间，默认10秒
                .readTimeout(openConfig.getReadTimeoutSeconds(), TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                        cookieStore.put(httpUrl.host(), list);
                    }

                    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                }).build();
    }

    /**
     * get请求
     *
     * @param url
     * @param header
     * @return
     * @throws IOException
     */
    public String get(String url, Map<String, String> header) throws IOException {
        Request.Builder builder = new Request.Builder().url(url).get();
        // 添加header
        addHeader(builder, header);

        Request request = builder.build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 提交json字符串到请求体
     *
     * @param url
     * @param json
     * @param header header内容
     * @return
     * @throws IOException
     */
    public String postJsonBody(String url, String json, Map<String, String> header) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder builder = new Request.Builder().url(url).post(body);
        // 添加header
        addHeader(builder, header);

        Request request = builder.build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 提交表单，并且上传文件
     *
     * @param url
     * @param form
     * @param header
     * @param files
     * @return
     * @throws IOException
     */
    public String postFile(String url, Map<String, Object> form, Map<String, String> header, List<UploadFile> files)
            throws IOException {
        // 创建MultipartBody.Builder，用于添加请求的数据
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        bodyBuilder.setType(MultipartBody.FORM);

        for (UploadFile uploadFile : files) {
            bodyBuilder.addFormDataPart(uploadFile.getName(), // 请求的名字
                    uploadFile.getFileName(), // 文件的文字，服务器端用来解析的
                    RequestBody.create(null, uploadFile.getFileData()) // 创建RequestBody，把上传的文件放入
            );
        }

        Set<Map.Entry<String, Object>> entrySet = form.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            bodyBuilder.addFormDataPart(entry.getKey(), String.valueOf(entry.getValue()));
        }

        RequestBody requestBody = bodyBuilder.build();

        Request.Builder builder = new Request.Builder().url(url).post(requestBody);

        // 添加header
        addHeader(builder, header);

        Request request = builder.build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    private void addHeader(Request.Builder builder, Map<String, String> header) {
        if (header != null) {
            Set<Map.Entry<String, String>> entrySet = header.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                builder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
    }

    public void setCookieStore(Map<String, List<Cookie>> cookieStore) {
        this.cookieStore = cookieStore;
    }

    public void setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setOpenConfig(OpenConfig openConfig) {
        this.openConfig = openConfig;
    }
}
