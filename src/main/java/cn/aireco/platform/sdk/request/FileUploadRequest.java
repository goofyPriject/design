package cn.aireco.platform.sdk.request;


import cn.aireco.platform.sdk.response.FileUploadResponse;

public class FileUploadRequest extends BaseRequest<FileUploadResponse> {
    @Override
    public String name() {
        return "file.upload";
    }
}
