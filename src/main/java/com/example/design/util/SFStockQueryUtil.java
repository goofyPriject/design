package com.example.design.util;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SFStockQueryUtil {

    private static final Logger logger = LoggerFactory.getLogger(SFStockQueryUtil.class);

    private static final String END_FLAG = "-1";

    public static Map<String, BigDecimal> doQuery() throws Exception {
        //接入编码
        String sfUrl = "https://portal-gateway.sf-express.com/scc-portal-api-service/omsPortalService/sendRequest";
        String sfKey = "29d4323922ba4885d4bbf694f73ddb0e";

        int pageIndex = 1;
        String msg = "";
        Map<String, BigDecimal> stockMap = new HashMap<>();
        while (StringUtils.isEmpty(msg)) {
            String xml = constructQueryXml(pageIndex);
            logger.info("顺丰库存查询报文:" + xml);
            String response = SfRequestUtils.sendPost(xml, sfUrl, sfKey);
            logger.info("顺丰库存查询返回报文:" + response);
            msg = parseXml(response, stockMap);
            pageIndex++;
        }
        if (StringUtils.equals(END_FLAG, msg)) {
            return stockMap;
        }
        throw new Exception(msg);
    }

    private static String constructQueryXml(int pageIndex) {
        Document document = DocumentHelper.createDocument();
        Element request = document.addElement("Request");
        request.addAttribute("service", "INVENTORY_BALANCE_PAGE_QUERY_SERVICE");
        request.addAttribute("lang", "zh-CN");
        // 请求头
        Element head = request.addElement("Head");
        //接入编码
        String sfAccessCode = "Upp9decuHkVPStdemjBMFw==";
        head.addElement("AccessCode").setText(sfAccessCode);
        //货主编码
        String companyCode = "W5717118022";
        head.addElement("Checkword").setText(companyCode);
        // 请求体
        Element body = request.addElement("Body");
        Element inventoryRequest = body.addElement("WmsInventoryBalancePageQueryRequest");
        //获取仓库的货主编码
        inventoryRequest.addElement("CompanyCode").setText(companyCode);
        //顺丰仓库代码
        String sfWarehouseCode = "571DCK";
        inventoryRequest.addElement("WarehouseCode").setText(sfWarehouseCode);
        inventoryRequest.addElement("PageIndex").setText(pageIndex + "");

        return document.asXML();
    }

    private static String parseXml(String xml, Map<String, BigDecimal> stockMap) throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        Element rootElement = document.getRootElement();
        //区分是系统正常还是系统异常
        String head = (String) rootElement.element("Head").getData();
        // 顺丰系统异常
        if ("ERR".equals(head)) {
            String errorMsg = (String) rootElement.element("Error").getData();
            return errorMsg;
        }

        Element response = rootElement.element("Body").element("WmsInventoryBalancePageQueryResponse");
        Element responseStatusEle = response.element("Result");
        String status = (String) responseStatusEle.getData();
        if (!StringUtils.equals("1", status)) {
            return "顺丰查询失败";
        }

        List<Element> list = response.element("List").elements();
        for (Element item : list) {
            String skuNo = item.elementText("SkuNo");
            BigDecimal availableQty = new BigDecimal(item.element("OnHandQty").getText());
            if (!stockMap.containsKey(skuNo)) {
                stockMap.put(skuNo, BigDecimal.ZERO);
            }
            stockMap.put(skuNo, stockMap.get(skuNo).add(availableQty));
        }

        int totalPage = Integer.valueOf((String) response.element("TotalPage").getData());
        int pageIndex = Integer.valueOf((String) response.element("PageIndex").getData());

        return pageIndex >= totalPage ? END_FLAG : "";
    }
}
