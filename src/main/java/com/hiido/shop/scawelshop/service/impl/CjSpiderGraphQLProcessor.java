package com.hiido.shop.scawelshop.service.impl;


import com.hiido.shop.scawelshop.bo.AdvertiseBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.ArrayList;
import java.util.List;


/**
 * 通过请求cj graphql获取cj产品
 * @author YSLin
 * @deprecated 废弃
 */
@Service
public class CjSpiderGraphQLProcessor implements PageProcessor {

    static {
        // 设置SSL协议
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
    }

    private final Site site = Site.me().setSleepTime(1000).setRetryTimes(5)
            .setTimeOut(10000)
            .setCharset("utf-8")
            .addHeader("Authorization", "Bearer 67xmj3evhgx1563mtkcnz00hnr")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.41");

    private final Logger logger = LoggerFactory.getLogger(CjSpiderGraphQLProcessor.class);

    private int totalResults;

    /**
     * 根据请求返回的json数据查询某个值，String
     * @param key json-item的键值
     * @param jsonStr json字符串
     * @return 查找到的json-value
     */
    private String jsonPathSelect(String key, String jsonStr) {
        return new JsonPathSelector(key).select(jsonStr);
    }

    /**
     * 根据请求返回的json数据查询某个列表，List<String>
     * @param key json-item的键值
     * @param jsonStr json字符串
     * @return 查找到的json-value
     */
    private List<String> jsonPathSelectList(String key, String jsonStr) {
        return new JsonPathSelector(key).selectList(jsonStr);
    }

    @Override
    public void process(Page page) {
//        Html html = page.getHtml();
        String responseJsonStr = page.getRawText();
        List<String> advertisers = jsonPathSelectList("advertisers", responseJsonStr);
        List<AdvertiseBO> advertiseBOList = new ArrayList<>();
        for (String advertiseItem: advertisers) {
            long advertiserId = Long.parseLong(jsonPathSelect("advertiserId", advertiseItem));
            String advertiserName = jsonPathSelect("advertiserName", advertiseItem);
            int advertiserStatus = Integer.parseInt(jsonPathSelect("advertiserStatus", advertiseItem));
            String advertiserUrl = jsonPathSelect("advertiserUrl", advertiseItem);

            AdvertiseBO advertiseBO = new AdvertiseBO();
            advertiseBO.setAdvertiserId(advertiserId);
            advertiseBO.setAdvertiserName(advertiserName);
            advertiseBO.setAdvertiserStatus(advertiserStatus);
            advertiseBO.setAdvertiserUrl(advertiserUrl);

            advertiseBOList.add(advertiseBO);
        }
        logger.info("请求成功");
    }



    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        String url = "https://ads.api.cj.com/query";
//        String advertiserUrl = "https://members.cj.com/member/publisher/6305866/advertiserSearch.json";
//        Map<String, Object> map = new HashMap<>();

//        String params = "?pageNumber=1&publisherId=6305866&pageSize=50&geographicSource=&relationshipStatus=&approvalRequired=false&autoRollover=false&autoApprovedByPublisher=false&manualApprovedByPublisher=false&autoRejectedByPublisher=false&sortColumn=advertiserName&sortDescending=false";
//        String[] paramKeyValues = params.split("&");
//        NameValuePair[] values = new NameValuePair[paramKeyValues.length];
//        for (int i = 0; i < paramKeyValues.length; i++) {
//            String[] kv = paramKeyValues[i].split("=");
//            values[i] = new BasicNameValuePair(kv[0], kv.length == 1 ? "" : kv[1]);
//        }
        Request request = new Request();
        request.setMethod(HttpConstant.Method.POST);
        request.setUrl(url);
//        map.put("nameValuePair", values);
//        request.setExtras(map);
        request.addHeader("Cookie", "hubspotutk=dc6b8a7452cd8b577eda97aef3b0e593; __hssrc=1; visitor_id701523=1398900167; visitor_id701523-hash=99c03c751d8de64364f00aea5f50f5069636d36c1e7e1171d8cf19876484058b606671e54102ca53e0a0e420af25473dc9d08e10; _ga_MHX0C3C1PT=GS1.1.1672969587.5.0.1672969587.0.0.0; _ga=GA1.2.908240406.1672384032; __hstc=168269822.dc6b8a7452cd8b577eda97aef3b0e593.1672384511456.1672887996400.1672969595250.3; _ga_494MDL3VSL=GS1.1.1672995319.6.0.1672995563.60.0.0; authentication_token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik4wUTFOalF3T1RoR1FVWXpNakUyUkVJeFFUTTBOelJETVRRd05EY3lNMFV5TXpBME9EQXdOdyJ9.eyJodHRwczovL3d3dy5jai5jb20vdXNlcmlkIjoiOGMyNDIyOGItNzU5ZS00Y2YyLWJkY2YtY2E3Y2MxMDdmMmI0IiwiaXNzIjoiaHR0cHM6Ly9zaWduaW4uY2ouY29tLyIsInN1YiI6ImF1dGgwfDhjMjQyMjhiLTc1OWUtNGNmMi1iZGNmLWNhN2NjMTA3ZjJiNCIsImF1ZCI6WyJjai1wbGF0Zm9ybSIsImh0dHBzOi8vc2lnbmluLmNqYWZmaWxpYXRlLmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2NzMyMzEwODAsImV4cCI6MTY3MzI3NDI4MCwiYXpwIjoicXBYNjlzbng3WFNjSUJMcDg1ZTVieEI5QWpEcU5qcGMiLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIG9mZmxpbmVfYWNjZXNzIn0.bw7J2UOH_2RPSNLXgT2b5oIB44rhDlDFh138HknAnt3fau4bamjG9ofpF9LRohn7K4IJJecAFLWbLbitWJv9ienCEFnYi7naKQalPiI-WDELBcy-FL0PkjQsGmafQhntVvboQIjrE4aMKqjGp6ADbU7dVLmScS0SApAS0we1Px048X6tWqy46B3tqeVfbloeADfsELgUquD96q0HU3uXmtwP7xQIWX7C-pIkOaRBnuo7SVrRKA-p1asGq1_r9NkL821Z_rxkKwklLcJHn7ep7yfjF_MvWUnqW8rureLhi51IbeASP5DyWfsx8t7nFWQesU7YsoRh1PZD7_xSpjtUow; CONTID=5847143; jsContactId=5847143; jsCompanyId=6305866; jsCu=USD; jsDt=d-MMM-yyyy; jsLa=zh; cjuMember=0; AuthenticationToken=7bbe8921-2640-4f02-8ec5-2d5e112c2a41; JSESSIONID=aaaKuPACcVL3Pfb8DLAvy; _gid=GA1.2.380186400.1673231089");
        HttpRequestBody httpRequestBody = new HttpRequestBody();
        String graphqlData = "{\n" +
                "    shoppingProducts(companyId: 6305866, adIds:[14537787], limit:5){\n" +
                "        count\n" +
                "        totalCount\n" +
                "        limit\n" +
                "        resultList{\n" +
                "            id\n" +
                "            adId\n" +
                "            additionalImageLink\n" +
                "            adult\n" +
                "advertiserCountry\n" +
                "advertiserId\n" +
                "advertiserName\n" +
                "ageGroup\n" +
                "availability\n" +
                "availabilityDate\n" +
                "brand\n" +
                "catalogId\n" +
                "color\n" +
                "condition\n" +
                "customLabel0\n" +
                "customLabel1\n" +
                "customLabel2\n" +
                "customLabel3\n" +
                "customLabel4\n" +
                "description\n" +
                "energyEfficiencyClass\n" +
                "energyEfficiencyClassMax\n" +
                "energyEfficiencyClassMin\n" +
                "expirationDate\n" +
                "gender\n" +
                "googleProductCategory{\n" +
                "    id\n" +
                "    \n" +
                "}\n" +
                "gtin\n" +
                "identifierExists\n" +
                "imageLink\n" +
                "installment{\n" +
                "    months\n" +
                "    \n" +
                "}\n" +
                "isBundle\n" +
                "isDeleted\n" +
                "itemGroupId\n" +
                "itemListId\n" +
                "itemListName\n" +
                "lastUpdated\n" +
                "link\n" +
                "linkCode(pid:1){\n" +
                "    imageUrl\n" +
                "}\n" +
                "loyaltyPoints{\n" +
                "    name\n" +
                "}\n" +
                "material\n" +
                "maximumHandlingTime\n" +
                "minimumHandlingTime\n" +
                "mobileLink\n" +
                "mpn\n" +
                "multipack\n" +
                "pattern\n" +
                "price {\n" +
                "    amount\n" +
                "    currency\n" +
                "}\n" +
                "productType\n" +
                "salePrice{\n" +
                "    amount\n" +
                "}\n" +
                "salePriceEffectiveDateEnd\n" +
                "salePriceEffectiveDateStart\n" +
                "serviceableAreas\n" +
                "shipping {\n" +
                "    service\n" +
                "}\n" +
                "size\n" +
                "sizeSystem\n" +
                "sizeType\n" +
                "sourceFeedType\n" +
                "targetCountry\n" +
                "tax {\n" +
                "    taxShip\n" +
                "}\n" +
                "title\n" +
                "unitPricingBaseMeasure \n" +
                "unitPricingMeasure\n" +
                "\n" +
                "        }\n" +
                "    }\n" +
                "}";
//        httpRequestBody.setBody(httpRequestBody.json(graphqlData));
        request.setRequestBody(HttpRequestBody.json(graphqlData, "UTF-8"));
        Spider spider = Spider.create(new CjSpiderGraphQLProcessor())
                .thread(1)
                .addRequest(request);
        spider.run();
    }
}
