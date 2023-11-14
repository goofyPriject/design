package cn.aireco.platform.service;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;
import java.util.stream.Stream;


//public class DemoService {
//
//    public void demo1() {
//
//    }
//
//    public static void main(String[] args) {
//        JSONObject xmlToJson = JSONObject.fromObject(xmlForJson(sb.toString()));
//
//    }
//
//    /**
//     * xml字符串转json字符串
//     * @param xml
//     * @return
//     */
//    private static Object xmlForJson(String xml) {
//        XMLSerializer xmlSerializer = new XMLSerializer();
//        String resutStr = xmlSerializer.read(xml).toString();
//        return resutStr;
//    }
//
//    /**
//     * 格式化xml,显示为容易看的XML格式
//     *
//     * @param xml 需要格式化的xml字符串
//     * @return
//     */
//    public static String formatXML(String xml) {
//        String requestXML = null;
//        try {
//            // 拿取解析器
//            SAXReader reader = new SAXReader();
//            Document document = reader.read(new StringReader(xml));
//            if (null != document) {
//                StringWriter stringWriter = new StringWriter();
//                // 格式化,每一级前的空格
//                OutputFormat format = new OutputFormat("	", true);
//                // xml声明与内容是否添加空行
//                format.setNewLineAfterDeclaration(false);
//                // 是否设置xml声明头部
//                format.setSuppressDeclaration(false);
//                // 是否分行
//                format.setNewlines(true);
//                XMLWriter writer = new XMLWriter(stringWriter, format);
//                writer.write(document);
//                writer.flush();
//                writer.close();
//                requestXML = stringWriter.getBuffer().toString();
//            }
//            return requestXML;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//}
