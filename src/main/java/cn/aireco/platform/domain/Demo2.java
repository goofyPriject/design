package cn.aireco.platform.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo2 extends Demo1{

    /**
     * 解析地址
     * @author lin
     * @param address
     * @return
     */
    public static Map<String,String>  addressResolution(String address){
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^市]+市|.[^县]+县|.[^区]+区|.+旗|.+海域|.+岛)?(?<village>.*)";
        Matcher m= Pattern.compile(regex).matcher(address);
        String province,
        city,
        county,
        village;
        Map<String,String> row=null;
        while(m.find()){
            row=new LinkedHashMap<>();
            province=m.group("province");
            row.put("province", province==null?"":province.trim());
            city=m.group("city");
            row.put("city", city==null?"":city.trim());
            county=m.group("county");
            row.put("county", county==null?"":county.trim());
            village=m.group("village");
            row.put("village", village==null?"":village.trim());
        }
        return row;
    }

    public static void main(String[] args) {
        System.out.println(addressResolution("河北邯郸市邯山区浴新南街道中华南大街与秀水路交叉口西行300米北侧现代海棠湾（详细地址给收货人打电话）[6403]"));
    }
}
