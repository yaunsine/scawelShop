package com.hiido.shop.scawelshop.service.impl;


import com.hiido.shop.scawelshop.bo.AdvertiserBO;
import com.hiido.shop.scawelshop.model.ActionNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.JsonPathSelector;
import us.codecraft.webmagic.utils.HttpConstant;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 通过请求cj rest-api获取cj产品
 * @author YSLin
 */
@Service
public class CjSpiderRestApiAdvertiserProcessor implements PageProcessor {

    static {
        // 设置SSL协议
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
    }

    private final Site site = Site.me().setSleepTime(1000).setRetryTimes(5)
            .setTimeOut(10000)
            .setCharset("utf-8")
            .addHeader("Authorization", "Bearer 67xmj3evhgx1563mtkcnz00hnr")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.41");

    private final Logger logger = LoggerFactory.getLogger(CjSpiderRestApiAdvertiserProcessor.class);

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

    @Resource(name = "advertiserService")
    private AdvertiserServiceImpl advertiserService;

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        List<String> advertiserSelector = html.xpath("advertisers/advertiser").all();
        List<AdvertiserBO> advertiserBOList = new ArrayList<>();
        for (String advertiserStr: advertiserSelector) {
            Html advertiserHtml = Html.create(advertiserStr);
            String advertiserId = advertiserHtml.xpath("advertiser-id/text()").get().trim();
            String accountStatus = advertiserHtml.xpath("account-status/text()").get().trim();
            String sevenDayEpc = advertiserHtml.xpath("seven-day-epc/text()").get().trim();
            String threeMonthEpc = advertiserHtml.xpath("three-month-epc/text()").get().trim();
            String language = advertiserHtml.xpath("language/text()").get().trim();
            String advertiserName = advertiserHtml.xpath("advertiser-name/text()").get().trim();
            String programUrl = advertiserHtml.xpath("program-url/text()").get().trim();
            String relationshipStatus = advertiserHtml.xpath("relationship-status/text()").get().trim();
            String mobileTrackingCertified = advertiserHtml.xpath("mobile-tracking-certified/text()").get().trim();
            String cookielessTrackingEnabled = advertiserHtml.xpath("cookieless-tracking-enabled/text()").get().trim();
            String networkRank = advertiserHtml.xpath("network-rank/text()").get().trim();
            String primaryCategoryParent = advertiserHtml.xpath("primary-category/parent/text()").get().trim();
            String primaryCategoryChild = advertiserHtml.xpath("primary-category/child/text()").get().trim();
            String performanceIncentives = advertiserHtml.xpath("performance-incentives/text()").get().trim();

            AdvertiserBO advertiserBO = new AdvertiserBO();
            advertiserBO.setAdvertiserId(Integer.parseInt(advertiserId));
            advertiserBO.setAccountStatus(accountStatus);
            advertiserBO.setSevenDayEpc(sevenDayEpc);
            advertiserBO.setThreeMonthEpc(threeMonthEpc);
            advertiserBO.setLanguage(language);
            advertiserBO.setAdvertiserName(advertiserName);
            advertiserBO.setProgramUrl(programUrl);
            advertiserBO.setRelationshipStatus(relationshipStatus);
            advertiserBO.setMobileTrackingCertified(Boolean.parseBoolean(mobileTrackingCertified)?1:0);
            advertiserBO.setCookielessTrackingCertified(Boolean.parseBoolean(cookielessTrackingEnabled)?1:0);
            advertiserBO.setNetworkRank(networkRank);
            advertiserBO.setPrimaryCategoryParent(primaryCategoryParent);
            advertiserBO.setPrimaryCategoryChild(primaryCategoryChild);
            advertiserBO.setPerformanceIncentives(Boolean.parseBoolean(performanceIncentives)?1:0);

            List<String> actions = advertiserHtml.xpath("actions/action").all();
            List<String> actionNodeList = new ArrayList<>();

            for (String actionStr: actions) {
                Html actionHtml = Html.create(actionStr);
                String name = actionHtml.xpath("name/text()").get().trim();
                String type = actionHtml.xpath("type/text()").get().trim();
                String id = actionHtml.xpath("id/text()").get().trim();
                String commission = actionHtml.xpath("commission/default/text()").get().trim();

                ActionNode actionNode = new ActionNode();
                actionNode.setName(name);
                actionNode.setType(type);
                actionNode.setId(Integer.parseInt(id));
                actionNode.setCommission(commission);


                actionNodeList.add(actionNode.toString());
            }

            advertiserBO.setActions(String.join(",", actionNodeList));
            List<String> linkTypes = advertiserHtml.xpath("link-types/link-type/text()").replace(" ", "").all();

            advertiserBO.setLinkTypes(String.join(",", linkTypes));
            advertiserBOList.add(advertiserBO);
            logger.info("请求成功");
        }
        advertiserService.saveBatchNative(advertiserBOList);
        logger.info("请求成功");
    }



    @Override
    public Site getSite() {
        return site;
    }

//    private static Request request;
//
//    @Autowired
//    @Qualifier("request")
//    private void request() {
//        request = new Request();
//    }

    public void start() {
        int recordsPerPage = 100;
        int pageNumMax = 3268 / 100 + 1;

        Spider spider = Spider.create(this);
        for (int i = 1; i <= pageNumMax ; i++) {
            String url = "https://advertiser-lookup.api.cj.com/v2/advertiser-lookup?requestor-cid=6305866&advertiser-ids=";
            url = url + "&records-per-page=100&page-number=" + i;
            Request request = new Request();
            request.setMethod(HttpConstant.Method.GET);

            request.setUrl(url);
            spider.addRequest(request);
        }

        spider.thread(5);
        spider.run();
    }

    public static void main(String[] args) {
        /*
       String advertiserUrl = "https://members.cj.com/member/publisher/6305866/advertiserSearch.json";
       Map<String, Object> map = new HashMap<>();

       String params = "?pageNumber=1&publisherId=6305866&pageSize=50&geographicSource=&relationshipStatus=&approvalRequired=false&autoRollover=false&autoApprovedByPublisher=false&manualApprovedByPublisher=false&autoRejectedByPublisher=false&sortColumn=advertiserName&sortDescending=false";
       String[] paramKeyValues = params.split("&");
       NameValuePair[] values = new NameValuePair[paramKeyValues.length];
       for (int i = 0; i < paramKeyValues.length; i++) {
           String[] kv = paramKeyValues[i].split("=");
           values[i] = new BasicNameValuePair(kv[0], kv.length == 1 ? "" : kv[1]);
       }
       start();
       */
    }
}
