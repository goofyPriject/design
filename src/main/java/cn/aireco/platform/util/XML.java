package cn.aireco.platform.util;

import com.qimen.api.QimenRequest;
import com.qimen.api.response.SingleitemSynchronizeResponse;
import com.taobao.api.internal.parser.xml.ObjectXmlParser;
import com.taobao.api.internal.util.XmlWriter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;


public class XML {

    private static Logger log = LoggerFactory.getLogger(XML.class);

    /**
     * 奇门类转换xml，sdk自带的，也可以自己写
     * @param ob
     * @return
     */
    public static String parseQiMen(Object ob){
        String title = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        String write="";
        XmlWriter tRsp= new XmlWriter(true, "response", QimenRequest.class);
        write = tRsp.write(ob);
        return title+write;
    }

    /**
     * 奇门转换处理,sdk自带的，也可以自己写
     * @param body
     * @param t 转换类，列如SingleitemSynchronizeRequest 商品同步接口
     * @param <T>
     * @return
     */
    public static <T>T convertQiMenClass(String body,Class<T> t)  {
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

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(date);
        SingleitemSynchronizeResponse response = new SingleitemSynchronizeResponse();
        response.setItemId("122121");
        response.setFlag("success");
        String s = parseQiMen(response);
        System.out.println(s);
    }
}
