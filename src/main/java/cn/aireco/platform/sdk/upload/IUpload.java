package cn.aireco.platform.sdk.upload;

import cn.aireco.platform.sdk.client.FileBytesResponse;
import cn.aireco.platform.sdk.common.OSSClientMessage;


import java.io.File;

public interface IUpload {


    /**
     * 文件上传
     *
     * @return
     */
    OSSClientMessage<FileBytesResponse> upload(File file);


}
