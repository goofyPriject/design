package cn.aireco.platform.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddressMatcher {

    // 计算两个字符串之间的Levenshtein距离
    private static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        // 初始化dp数组
        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= b.length(); j++) {
            dp[0][j] = j;
        }

        // 填充dp数组
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }

        return dp[a.length()][b.length()];
    }

    // 计算两个字符串之间的相似度百分比
    public static double similarityPercentage(String a, String b) {
        int maxLen = Math.max(a.length(), b.length());
        int distance = levenshteinDistance(a, b);
        return (1.0 - (double) distance / maxLen) * 100;
    }

    // 检查地址是否在黑名单中（基于相似度百分比）
    public static Double isAddressInBlacklist(String address, List<String> blacklist, double threshold) {
        List<Double> max = new ArrayList<>();
        for (String blacklistedAddress : blacklist) {
            double similarity = similarityPercentage(address, blacklistedAddress);
            max.add(similarity);
        }
        return Collections.max(max);

    }

    // 主方法，用于测试
    public static void main(String[] args) {

        List<String> blacklist = Arrays.asList(
                "广东省广州市海珠区中洲交易中心1209[6883]",
                "山东省济南市历下区华强广场三楼h3062b监控室旁边[8618]",
                "陕西省西安市新城区尚朴路农业银行隔壁[0579]",
                "陕西省西安市新城区尚朴路农业银行隔壁二楼[4429]",
                "浙江省 舟山市 定海区 文化广场18号苏宁易购电脑柜",
                "江苏省 苏州市 太仓市 五洋商城17栋110",
                "江苏省 连云港市 海州区 苏宁广场亚朵酒店",
                "河南省 郑州市 新郑市 河南省郑州市新郑市新烟街道人民路168号郑州西亚斯学院伦敦街北翼来宝物流园区",
                "福建省 福州市 台江区 工业路博美诗邦5#1704",
                "湖北省 武汉市 洪山区 关山街道珞喻路1037号 华中科技大学 虹景国际学生公寓[2308]",
                "山东省 济南市 天桥区 山东通讯城4楼418[6211]",
                "江苏省 苏州市 太仓市 城厢镇城厢镇人民北路13号永琪美容美发[9523]",
                "湖北省宜昌市当阳市东正街韵达快递**",
                "福建省 漳州市 龙文区 华东工业品批发市场17栋1号[5700]",
                "广东深圳市龙华区大浪街道华胜商业大厦1502",
                "广东深圳市龙华区大浪街道羊台新村69栋",
                "山东济南市天桥区北村街道济泺路92号山东通讯城419",
                "山东济南市天桥区北村街道济泺路92号山东通讯城4层418室",
                "山东济南市天桥区药山街道大魏明都5号楼",
                "山东济南市天桥区药山街道金容花园冬园1-1-501",
                "山东济南市天桥区药山街道金容花园冬园郑家超市",
                "重庆市 重庆市 巴南区 渝南大道103号缇香荟商业广场2-10",
                "四川省 绵阳市 涪城区 毅德商贸城百家福超市",
                "江苏省 盐城市 滨海县 新街路与招商场路交叉口北80米陈涛商贸城",
                "河南省 郑州市 新郑市 郑州升达经贸管理学院[3718]",
                "河南省 郑州市 新郑市 龙湖镇文昌路一号郑州升达经贸管理学院",
                "山东济南市天桥区药山街道大魏明都5号楼4单元2603",
                "山东济南市天桥区泺口街道赵家庄小区东区18号楼三单元",
                "山东济南市天桥区泺口街道赵家庄小区东区18号楼三单元东区18号楼3单元",
                "山东济南市天桥区药山街道大魏便民市场东排门头房3号",
                "山东济南市天桥区药山街道大魏明都济南市天桥区大魏庄路大魏农贸市场东排门头房3号",
                "山东济南市天桥区北园街道济南市天桥区济泺路92号通讯城4楼418",
                "浙江省 台州市 临海市 大田街道 世通物流5-22菜鸟驿站[0938",
                "四川省 成都市 新都区 新都镇宝光大道北段139号信地兰卡威[2294",
                "陕西省 榆林市 榆阳区 伟业新天地[2276",
                "陕西省 渭南市 澄城县 安里镇段庄村一组[4096",
                "安徽省 合肥市 瑶海区 京商商贸城F区7街JG栋401[2017",
                "安徽省 安庆市 宿松县 孚玉镇小河路玉成商行[0189",
                "浙江省 杭州市 西湖区 塘苗路18号c303[6283]",
                "河南省 驻马店市 上蔡县 党店镇[9989",
                "辽宁省 盘锦市 双台子区 兴隆繁荣里小区30－1－602[7876",
                "辽宁省 盘锦市 双台子区 兴隆繁荣里小区30号楼一单元[9383",
                "山东省 青岛市 崂山区 海尔路170号512[5927",
                "山东省 青岛市 崂山区 海尔路鑫裕和大厦508[3978",
                "山东省 青岛市 崂山区 海尔路鑫裕和大厦5楼507[2036",
                "辽宁省 盘锦市 双台子区 繁荣里小区30－1－602[0022",
                "辽宁省 盘锦市 双台子区 铁东街道繁荣里小区众旺乐超市[3315",
                "吉林省 长春市 朝阳区 桦甸街万科繁荣里4-1001[6621]",
                "河南省 周口市 鹿邑县 鹿邑县马铺镇马楼[0773]",
                "广东省 深圳市 龙华区 华联社区 龙马新村291号 （龙华汽车站后 ）恒龙大厦C栋（或保安亭）[5720]",
                "江苏省 南京市 江宁区 淳化街道弘景大道3601号南京晓庄学院方山校区[6637",
                "***********",
                "广东省 深圳市 福田区 华强北街道深纺大厦西座C5217[6692",
                "江苏省 苏州市 工业园区 清宁路28号文缘人才公寓[6065",
                "浙江省 杭州市 富阳区 银湖街道碧桂园铂玺湾1幢1603室[2275",
                "浙江省 杭州市 富阳区 银湖街道新桥新路碧桂园铂玺湾1-1603[2275",
                "安徽省滁州市琅琊区花慕悦祛斑科技（大润***",
                "安徽省阜阳市界首市天安路*号界首市公安局",
                "安徽省合肥市肥东县安徽省合肥市肥东县撮镇镇合马路*号安徽水利水电职业技术学院",
                "山东省 济南市 历下区 山大路157号华强数码广场B栋12楼1204[6880",
                "吉林省白山市江源区石人镇林子**",
                "安徽省滁州市来安县汊河镇王来村前**",
                "安徽省合肥市庐阳区农科南路学院里小区A*栋",
                "江苏省无锡市锡山区羊尖胶山路*号",
                "江苏省无锡市锡山区羊尖镇胶山路*号",
                "江苏省无锡市锡山区羊尖镇胶山路*号无锡中地钻探装备有限公司",
                "山东省济南市天桥区济洛路*号",
                "山东省济宁市任城区商动力电商大厦B座*",
                "安徽省蚌埠市龙子湖区安徽财经大学龙湖东校区北***",
                "安徽省亳州市利辛县七彩名*",
                "安徽省滁州市凤阳县东华路*号安徽科技学院东区",
                "安徽省阜阳市界首市裕民家园**",
                "重庆市重庆市渝中区大坪新浪通讯市场一楼*号开间",
                "广东省深圳市福田区华强北华强广场D座*H",
                "广东省深圳市福田区华强北路*号到了打电话",
                "安徽省 六安市 金安区 皋城东路125号喜洋洋工业园朝阳轮胎[5202]",
                "北京市 北京市 海淀区 西直门外上园村3号北京交通大学主校区南门近邻宝快递服务中心[6607]",
                "福建省 厦门市 湖里区 （万山小区）南山路75号401室[6447]",
                "广东省 深圳市 龙华区 壹成中心8区3栋4101[8607]",
                "江西省 吉安市 吉水县 滨江凤凰城B区13栋1601[6111]",
                "上海市 上海市 松江区 广富林路1188弄保利西子湾小区 170号1103室[5618]",
                "浙江省 杭州市 余杭区 余杭区杭泰路67-1东北麻辣烫旁边小铁门楼梯[5417]",
                "广东省东莞市大朗镇广东省东莞市富康路*号环球贸易广场环球大厦B座*室",
                "安徽省安庆市迎江区红旗小区*号楼二单元*室",
                "安徽省阜阳市阜南县张寨路*号比亚迪阜南弗迪公司",
                "广东省广州市番禺区桥南街陈涌村上澳东街四巷*号",
                "广东省深圳市南山区西丽街道茶光路*号冠铭花园*B*",
                "广东省中山市五桂山镇龙塘鲤鱼山村*号菜鸟驿站",
                "广西壮族自治区北海市海城区涠洲镇西角村一组*号",
                "河北省石家庄市裕华区裕翔园*号楼",
                "河南省洛阳市涧西区园林小区，*号楼*",
                "黑龙江省伊春市友好区蓝莓街*号楼盛煜小厨",
                "湖北省武汉市江夏区杨桥湖大道湖北经济学院西区****",
                "江苏省南京市浦口区泰西路*号浦东大厦",
                "江苏省宿迁市宿豫区锦华御园*栋*",
                "江苏省无锡市新吴区江溪街道 万裕苑一区*栋",
                "江苏省徐州市贾汪区南湖雅园*-*-*",
                "江苏省盐城市亭湖区中天阳光雅居*号楼*单元*",
                "山东省济南市历下区姚家街道解放东路*号山东政法学院东门菜鸟驿站",
                "四川省成都市彭州市石化公*",
                "四川省成都市新都区石板滩镇景润华庭*栋",
                "四川省德阳市绵竹市紫岩街道东方明珠四栋二单元*",
                "北京市北京市海淀区软件园二期联想**",
                "安徽省合肥市瑶海区新海西*",
                "安徽省淮北市相山区安徽省淮北市相山区东山路*号淮北师范大学",
                "安徽省宿州市砀山县关帝庙大李**",
                "安徽省宿州市砀山县关帝庙镇晨晨鱼**",
                "北京市北京市通州区科创七街，悦康药***",
                "河北省保定市莲池区中央司法警官**",
                "河北省石家庄市新乐市河北省石家庄市新乐市 长寿街道 河北美院南校区快递之****",
                "河南省焦作市武陟县东胜苑小区三号楼*单元",
                "山东省 潍坊市 奎文区 北苑街道四平路与玄武街交叉口东50米路北华港石油天然气有限公司[7798",
                "山西省 太原市 万柏林区 窊流路66号太原科技大学主校区[2190",
                "上海市 上海市 浦东新区 上海健康医学院北苑[5824",
                "天津市 天津市 西青区 天津市西青区精武镇宾水西道393号天津师范大学[8192",
                "上海市上海市黄浦区淮海东路*号新尚数码广场一楼Z*",
                "江苏省南通市如皋市建设小*",
                "辽宁省沈阳市浑南区白塔河路碧桂园公园里二期*-*#*-*-*",
                "广东省深圳市光明区光明大道*正南方向*米宏发万悦山*栋*",
                "江苏省扬州市高邮市海潮路东路*号 高邮联通*楼",
                "江苏省扬州市高邮市江苏省高邮市海潮东路*号（高邮联通*楼集团部）",
                "广东省广州市番禺区沙头街道沙头新村*街*号",
                "江苏省南京市秦淮区后标营 *-*郭粉通讯",
                "江苏省南京市秦淮区后标营*号 郭粉代收",
                "辽宁省大连市旅顺口区菜鸟驿站(大连旅顺一品山水*号店)",
                "广东省深圳市坪山区世茂广场*室",
                "广东省佛山市南海区里水镇 里广路草场工业区C*栋*团SUN BIN煜熙仓 *",
                "四川省成都市成华区华林三路*号铁建广场*栋",
                "重庆市重庆市永川区红河大道*号重庆文理学院(红河校区)A区",
                "安徽省合肥市瑶海区京商商贸城D区九街BF栋*",
                "广东省佛山市禅城区张槎街道保利i**",
                "广东省揭阳市东山区玉浦新区E*栋明达器材",
                "福建省厦门市集美区诚毅北大街*号软件园三期B*栋公寓楼",
                "福建省福州市鼓楼区福州市鼓楼区软件大道软件园C区*号楼",
                "河北省承德市承德县下板城镇二路市场花都底商板城通讯****",
                "福建省泉州市鲤城区新天百纳苑*号楼",
                "重庆市重庆市永川区红河大道*号重庆文理学院红河A区",
                "浙江省温州市乐清市上米岙 上滩头 乐清市凡夫电器****",
                "广东省佛山市南海区佛平三路*号万科金色领域*座*",
                "广东省佛山市南海区佛平三路*号万科金色领域*座菜鸟驿站",
                "广东省汕头市龙湖区龙湖区新溪镇北兴路新大地通讯手机店三楼桂*****",
                "福建省福州市台江区西洋路*号*号楼*层巨象电梯口货架上",
                "福建省福州市台江区西洋路*号(西洋地铁站B口旁)福州旧晚报社 *#* 巨象文化传媒有限公司",
                "河南省新乡市红旗区洪门镇新乡学院东***",
                "安徽省阜阳市颍州区易景国际花园二十一栋三*二室",
                "安徽省阜阳市颍州区易景国际花园*栋*室",
                "安徽省阜阳市颍州区林颍路易景国际花园菜鸟驿站(阜阳易景国际南区*号楼店)",
                "广东省揭阳市揭西县钱坑镇钱坑大**",
                "广东省揭阳市揭西县钱坑镇钱坑大桥头乐盛***",
                "湖北省襄阳市襄州区东风汽车大道孙庄路妈***",
                "山西省太原市小店区昌盛街倔强蜗牛教***",
                "江苏省徐州市邳州市运河街道天鸿世**",
                "北京市北京市朝阳区望京街道南湖西园*号楼*",
                "江西省宜春市袁州区慈化镇伯塘**",
                "山东省潍坊市坊子区坊城街道石沟河村菜***",
                "安徽省合肥市瑶海区前江路*号安徽中医药大学菜鸟驿站",
                "江苏省无锡市滨湖区雪浪街道蠡湖大道*号江南大学北区梅园",
                "江苏省淮安市淮阴区马头镇福兴**",
                "江西省九江市柴桑区九瑞大道*号 西溪悦府*栋*",
                "江苏省盐城市东台市安丰镇安丰**",
                "广东省中山市神湾镇宥南村瑞泰大街成业四街*号",
                "山东省淄博市临淄区牛山路绿茵青青家园 放***",
                "江西省抚州市临川区江西省抚州市临川区钟岭街道王安石大道*号抚州幼儿师范高等专科学校",
                "河北省邯郸市峰峰矿区临水镇德易峰尚**",
                "天津市天津市红桥区河通花园*-*",
                "广东省汕尾市陆河县河田镇河中路大圆盘路口大****",
                "广东省广州市白云区广花四路*~*号快递超市(白沙小区店)",
                "重庆市重庆市渝北区黄竹路*号康庄美地C区*幢",
                "广东省深圳市宝安区西乡街道茶西新村*号*",
                "广东省中山市小榄镇小榄镇同乐路 * 号东升高级中学",
                "陕西省西安市莲湖区土门街道牡丹庄园*号楼",
                "上海市上海市闵行区君临天下*号",
                "江苏省连云港市海州区春晖路*号江苏财会职业学院",
                "江西省萍乡市安源区Y*江西应用工程职业学院-宿舍楼",
                "安徽省合肥市新站综合开发试验区银城旭辉樾溪臺*栋",
                "四川省成都市成华区湖秀二路*号",
                "内蒙古自治区呼和浩特市回民区北二环路内蒙古财经大学西区近邻宝*****",
                "上海市上海市浦东新区北中路与莲园路交叉口西南方向*米北中路*弄小区*号楼*",
                "北京市北京市海淀区北太平庄街道 西土城路*号 北京邮电大学",
                "北京市北京市丰台区北京市丰台区郭公庄地铁站B口福慧东路*号院*号楼（臻御府西区）*单元*",
                "上海市上海市松江区贵德路*号",
                "江苏省南京市鼓楼区下关街道宜城公寓盐***",
                "四川省德阳市旌阳区石桥新村 * 期 A 区 * 栋菜鸟驿站",
                "河南省焦作市沁阳市天庆加气站旁河南晋控天庆煤化工有*****",
                "广东省广州市海珠区南洲街道西滘村礼堂菜***",
                "河南省郑州市中牟县港城公*",
                "北京市北京市昌平区西环里小区*号楼*单元*",
                "广东省揭阳市惠来县华湖镇 溪洋新村凯里亚德酒店隔壁（不要寄*****",
                "广东省揭阳市榕城区揭阳职业技术学院学生宿***"

        );

        String addressToCheck = "广东省佛山市南海区狮山镇官窑永和官堂中村六巷*号"; // 示例地址，有少量字符不同
        double threshold = 90.0; // 相似度阈值


        System.out.println("地址在黑名单中（基于相似度）：" + isAddressInBlacklist(addressToCheck, blacklist, threshold));


//        if (isAddressInBlacklist(addressToCheck, blacklist, threshold)) {
//            System.out.println("地址在黑名单中（基于相似度）：" + addressToCheck);
//        } else {
//            System.out.println("地址正常（基于相似度）：" + addressToCheck);
//        }
    }
}
