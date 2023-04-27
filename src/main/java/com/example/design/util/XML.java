package com.example.design.util;

import com.qimen.api.QimenRequest;
import com.taobao.api.internal.parser.xml.ObjectXmlParser;
import com.taobao.api.internal.util.XmlWriter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;



public class XML {

    private Logger log = LoggerFactory.getLogger(XML.class);

    /**
     * 奇门类转换xml，sdk自带的，也可以自己写
     * @param ob
     * @return
     */
    public String parseQiMen(Object ob){
        String write="";
        XmlWriter tRsp= new XmlWriter(true, "request", QimenRequest.class);
        write = tRsp.write(ob);
        return write;
    }

    /**
     * 奇门转换处理,sdk自带的，也可以自己写
     * @param body
     * @param t 转换类，列如SingleitemSynchronizeRequest 商品同步接口
     * @param <T>
     * @return
     */
    public <T>T convertQiMenClass(String body,Class<T> t)  {
        try {
            ObjectXmlParser xmlParser = new ObjectXmlParser(t);
            Object classObject = xmlParser.parse(body, "qimen1");
            return (T)classObject;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("转换奇门数据异常",e);
        }
        return null;
    }
}
