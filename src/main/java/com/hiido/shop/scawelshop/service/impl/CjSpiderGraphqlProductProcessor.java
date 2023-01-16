package com.hiido.shop.scawelshop.service.impl;


import com.hiido.shop.scawelshop.mapper.AdvertiserMapper;
import com.hiido.shop.scawelshop.mapper.ShoppingProductMapper;
import com.hiido.shop.scawelshop.po.ShoppingProductPO;
import com.jayway.jsonpath.PathNotFoundException;
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

import javax.annotation.Resource;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;


/**
 * @description TODO: 通过请求cj graphql获取cj产品
 * @author YSLin
 * @date 2023/1/11 15:28
 * @deprecated 重试爬取失败的shoppingProducts
 */
@Service
public class CjSpiderGraphqlProductProcessor implements PageProcessor {

    static {
        // 设置SSL协议
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
    }

    private final Site site = Site.me().setSleepTime(2000).setRetryTimes(5).setTimeOut(30*60*1000)
            .setCharset("utf-8")
            .addHeader("Authorization", "Bearer 67xmj3evhgx1563mtkcnz00hnr")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.124 Safari/537.36 Edg/102.0.1245.41");

    private final Logger logger = LoggerFactory.getLogger(CjSpiderGraphqlProductProcessor.class);

    @Resource
    private AdvertiserMapper advertiserMapper;

    @Resource
    private ShoppingProductMapper shoppingProductMapper;

    @Override
    public void process(Page page) {
        // Html html = page.getHtml();
        String responseJsonStr = page.getJson().get();
        String totalCount = page.getJson().jsonPath("$.data.shoppingProducts.totalCount").get();
        String count = page.getJson().jsonPath("$.data.shoppingProducts.count").get();
        logger.info("本次请求数据量为：totalCount: {}, count: {}", totalCount, count);
        logger.info("本次请求的链接：" + page.getUrl().get());
        List<String> shoppingProductsStrList = jsonPathSelectList("$.data.shoppingProducts.resultList", responseJsonStr);

        List<ShoppingProductPO> shoppingProductPOList = new ArrayList<>();
        for (String shoppingProductsStr: shoppingProductsStrList) {
            String id = jsonPathSelect("id", shoppingProductsStr);

            ShoppingProductPO shoppingProductPO = new ShoppingProductPO();
            String advertiserId = jsonPathSelect("advertiserId", shoppingProductsStr);
            String advertiserName = jsonPathSelect("advertiserName", shoppingProductsStr);
            String adult = jsonPathSelect("adult", shoppingProductsStr);
            String adId = jsonPathSelect("adId", shoppingProductsStr);
            String brand = jsonPathSelect("brand", shoppingProductsStr);
            String advertiserCountry = jsonPathSelect("advertiserCountry", shoppingProductsStr);
            String color = jsonPathSelect("color", shoppingProductsStr);
            String availability = jsonPathSelect("availability", shoppingProductsStr);
            String additionalImageLink = jsonPathSelect("additionalImageLink", shoppingProductsStr);
            String ageGroup = jsonPathSelect("ageGroup", shoppingProductsStr);
            String catalogId = jsonPathSelect("catalogId", shoppingProductsStr);
            String availabilityDate = jsonPathSelect("availabilityDate", shoppingProductsStr);
            String condition = jsonPathSelect("condition", shoppingProductsStr);
            String title = jsonPathSelect("title", shoppingProductsStr);
            String tax = jsonPathSelect("tax", shoppingProductsStr);
            String size = jsonPathSelect("size", shoppingProductsStr);
            String isBundle = jsonPathSelect("isBundle", shoppingProductsStr);
            String unitPricingBaseMeasure = jsonPathSelect("unitPricingBaseMeasure", shoppingProductsStr);
            String customLabel0 = jsonPathSelect("customLabel0", shoppingProductsStr);
            String customLabel1 = jsonPathSelect("customLabel1", shoppingProductsStr);
            String customLabel2 = jsonPathSelect("customLabel2", shoppingProductsStr);
            String customLabel3 = jsonPathSelect("customLabel3", shoppingProductsStr);
            String customLabel4 = jsonPathSelect("customLabel4", shoppingProductsStr);
            String description = jsonPathSelect("description", shoppingProductsStr);
            String expirationDate = jsonPathSelect("expirationDate", shoppingProductsStr);
            String shipping = jsonPathSelect("shipping", shoppingProductsStr);
            String serviceableAreas = jsonPathSelect("serviceableAreas", shoppingProductsStr);
            String price = jsonPathSelect("price", shoppingProductsStr);
            String targetCountry = jsonPathSelect("targetCountry", shoppingProductsStr);
            String sourceFeedType = jsonPathSelect("sourceFeedType", shoppingProductsStr);
            String salePriceEffectiveDateStart = jsonPathSelect("salePriceEffectiveDateStart", shoppingProductsStr);
            String productType = jsonPathSelect("productType", shoppingProductsStr);
            String salePriceEffectiveDateEnd = jsonPathSelect("salePriceEffectiveDateEnd", shoppingProductsStr);
            String sizeType = jsonPathSelect("sizeType", shoppingProductsStr);
            String sizeSystem = jsonPathSelect("sizeSystem", shoppingProductsStr);
            String mpn = jsonPathSelect("mpn", shoppingProductsStr);
            String pattern = jsonPathSelect("pattern", shoppingProductsStr);
            String mobileLink = jsonPathSelect("mobileLink", shoppingProductsStr);
            String material = jsonPathSelect("material", shoppingProductsStr);
            String multipack = jsonPathSelect("multipack", shoppingProductsStr);
            String link = jsonPathSelect("link", shoppingProductsStr);
            // String linkCode = jsonPathSelect("linkCode", shoppingProductsStr);
            String installment = jsonPathSelect("installment", shoppingProductsStr);
            String salePrice = jsonPathSelect("salePrice", shoppingProductsStr);
            String maximumHandlingTime = jsonPathSelect("maximumHandlingTime", shoppingProductsStr);
            String minimumHandlingTime = jsonPathSelect("minimumHandlingTime", shoppingProductsStr);
            String itemListId = jsonPathSelect("itemListId", shoppingProductsStr);
            String isDeleted = jsonPathSelect("isDeleted", shoppingProductsStr);
            String imageLink = jsonPathSelect("imageLink", shoppingProductsStr);
            String loyaltyPoints = jsonPathSelect("loyaltyPoints", shoppingProductsStr);
            String itemGroupId = jsonPathSelect("itemGroupId", shoppingProductsStr);
            String lastUpdated = jsonPathSelect("lastUpdated", shoppingProductsStr);
            String unitPricingMeasure = jsonPathSelect("unitPricingMeasure", shoppingProductsStr);
            String identifierExists = jsonPathSelect("identifierExists", shoppingProductsStr);
            String gtin = jsonPathSelect("gtin", shoppingProductsStr);
            String googleProductCategory = jsonPathSelect("googleProductCategory", shoppingProductsStr);
            String energyEfficiencyClassMin = jsonPathSelect("energyEfficiencyClassMin", shoppingProductsStr);
            String energyEfficiencyClassMax = jsonPathSelect("energyEfficiencyClassMax", shoppingProductsStr);
            String energyEfficiencyClass = jsonPathSelect("energyEfficiencyClass", shoppingProductsStr);
            String gender = jsonPathSelect("gender", shoppingProductsStr);
            String itemListName = jsonPathSelect("itemListName", shoppingProductsStr);

            shoppingProductPO.setPid(id);
            shoppingProductPO.setEnergyEfficiencyClassMin(energyEfficiencyClassMin);
            shoppingProductPO.setEnergyEfficiencyClassMax(energyEfficiencyClassMax);
            shoppingProductPO.setEnergyEfficiencyClass(energyEfficiencyClass);
            shoppingProductPO.setGender(gender);

            shoppingProductPO.setItemListName(itemListName);
            shoppingProductPO.setGoogleProductCategory(googleProductCategory);
            shoppingProductPO.setGtin(gtin);
            shoppingProductPO.setIdentifierExists(identifierExists);
            shoppingProductPO.setUnitPricingMeasure(unitPricingMeasure);

            shoppingProductPO.setAdvertiserId(Integer.parseInt(advertiserId));
            shoppingProductPO.setAdvertiserName(advertiserName);
            shoppingProductPO.setAdult(adult);
            shoppingProductPO.setAdId(Integer.parseInt(adId));
            shoppingProductPO.setBrand(brand);
            shoppingProductPO.setAdvertiserCountry(advertiserCountry);
            shoppingProductPO.setColor(color);
            shoppingProductPO.setAvailability(availability);
            shoppingProductPO.setAdditionalImageLink(additionalImageLink);
            shoppingProductPO.setAgeGroup(ageGroup);
            shoppingProductPO.setCatalogId(catalogId);
            shoppingProductPO.setAvailabilityDate(availabilityDate);
            shoppingProductPO.setCondition(condition);
            shoppingProductPO.setTitle(title);
            shoppingProductPO.setTax(tax);
            shoppingProductPO.setSize(size);
            shoppingProductPO.setIsBundle(isBundle);
            shoppingProductPO.setUnitPricingBaseMeasure(unitPricingBaseMeasure);
            shoppingProductPO.setCustomLabel0(customLabel0);
            shoppingProductPO.setCustomLabel1(customLabel1);
            shoppingProductPO.setCustomLabel2(customLabel2);
            shoppingProductPO.setCustomLabel3(customLabel3);
            shoppingProductPO.setCustomLabel4(customLabel4);
            shoppingProductPO.setDescription(description);
            shoppingProductPO.setExpirationDate(expirationDate);
            shoppingProductPO.setShipping(shipping);
            shoppingProductPO.setServiceableAreas(serviceableAreas);
            shoppingProductPO.setPrice(price);
            shoppingProductPO.setTargetCountry(targetCountry);
            shoppingProductPO.setSourceFeedType(sourceFeedType);
            shoppingProductPO.setSalePriceEffectiveDateStart(salePriceEffectiveDateStart);
            shoppingProductPO.setProductType(productType);
            shoppingProductPO.setSalePriceEffectiveDateEnd(salePriceEffectiveDateEnd);
            shoppingProductPO.setSizeType(sizeType);
            shoppingProductPO.setSizeSystem(sizeSystem);
            shoppingProductPO.setMpn(mpn);
            shoppingProductPO.setPattern(pattern);
            shoppingProductPO.setMobileLink(mobileLink);
            shoppingProductPO.setMaterial(material);
            shoppingProductPO.setMultipack(multipack);
            shoppingProductPO.setLink(link);
            // shoppingProductPO.setLinkCode(linkCode);
            shoppingProductPO.setInstallment(installment);
            shoppingProductPO.setSalePrice(salePrice);
            shoppingProductPO.setMaximumHandlingTime(maximumHandlingTime);
            shoppingProductPO.setMinimumHandlingTime(minimumHandlingTime);
            shoppingProductPO.setItemListId(itemListId);
            shoppingProductPO.setIsDeleted(Boolean.parseBoolean(isDeleted)?1:0);
            shoppingProductPO.setImageLink(imageLink);
            shoppingProductPO.setLoyaltyPoints(loyaltyPoints);
            shoppingProductPO.setItemGroupId(itemGroupId);
            shoppingProductPO.setLastUpdated(lastUpdated);

            shoppingProductPOList.add(shoppingProductPO);
        }

        try{
            // 批量插入数据库
            if(shoppingProductPOList.size() != 0) {
                shoppingProductMapper.saveBatchByNative(shoppingProductPOList);
                logger.info("写入数据成功: " + shoppingProductPOList.size());
            }

        } catch (SQLSyntaxErrorException e) {
            logger.info("请求异常， 数据为: " + page.getJson().get());
            throw new RuntimeException(e);
        }
    }



    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 启动爬虫，请求产品数据
     */
    public void start() {
        // 查询所有的advertiser_id
        List<Integer> advertiserIds = advertiserMapper.listAdvertiserIds();
        logger.info("已请求到广告商信息...");

        String url = "https://ads.api.cj.com/query";

        String graphqlData = "{\n" +
                "    shoppingProducts(companyId: 6305866, partnerIds:[%d], offset:%d, limit:%d){\n" +
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
                "availability\n" +
                "availabilityDate\n" +
                "brand\n" +
                "ageGroup\n" +
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
                "    currency\n" +
                "}\n" +
                "salePriceEffectiveDateEnd\n" +
                "salePriceEffectiveDateStart\n" +
                "serviceableAreas\n" +
                "shipping {\n" +
                "    country\n" +
                "    height\n" +
                "    length\n" +
                "    locationGroupName\n" +
                "    service\n" +
                "    locationId\n" +
                "    postalCode\n" +
                "    region\n" +
                "    weight\n" +
                "    width\n" +
                "}\n" +
                "size\n" +
                "sizeSystem\n" +
                "sizeType\n" +
                "sourceFeedType\n" +
                "targetCountry\n" +
                "tax {\n" +
                "    taxShip\n" +
                "    rate\n" +
                "    locationId\n" +
                "}\n" +
                "title\n" +
                "unitPricingBaseMeasure \n" +
                "unitPricingMeasure\n" +
                "\n" +
                "        }\n" +
                "    }\n" +
                "}";

        List<String> stringList = new ArrayList<>();
        stringList.add("5178287.3000");
        stringList.add("5233286.5000");
        stringList.add("5233431.0");
        stringList.add("5240813.1000");
        stringList.add("5246033.2000");
        stringList.add("5258938.1000");
        stringList.add("4885459.4000");
        stringList.add("4942550.0");
        stringList.add("4942550.9000");
        stringList.add("4958915.2000");
        stringList.add("4965120.0");
        stringList.add("4976598.1000");
        stringList.add("4976598.3000");
        stringList.add("4976598.9000");
        stringList.add("4971848.8000");
        stringList.add("5134053.4000");
        stringList.add("5134053.5000");
        stringList.add("5126501.5000");
        stringList.add("5134053.8000");
        stringList.add("5068016.7000");

        stringList.add("5086876.1000");
        stringList.add("5397322.3000");
        stringList.add("5397322.6000");
        stringList.add("5397322.9000");
        stringList.add("5397322.5000");
        stringList.add("6299640.0");
        stringList.add("1220066.1000");
        stringList.add("2362700.3000");
        stringList.add("2362700.0");
        stringList.add("2362700.1000");
        stringList.add("2362700.2000");
        stringList.add("2362700.5000");
        stringList.add("2362700.7000");
        stringList.add("2362700.6000");
        stringList.add("2362700.8000");
        stringList.add("2362700.4000");
        stringList.add("2936591.7000");

        stringList.add("5885341.4000");
        stringList.add("4450361.8000");
        stringList.add("4996706.3000");
        stringList.add("5444996.1000");

        stringList.add("5909452.0");
        stringList.add("5916190.5000");
        stringList.add("5916190.1000");
        stringList.add("5916190.0");
        stringList.add("5655714.0");


        Spider spider = Spider.create(this);
        boolean jumpFlag = true;

        for (String adIdAndOffset: stringList) {
            int limit = 1000;
            int max = 10000;
            String[] ads = adIdAndOffset.split("\\.");

            int advertiserId = Integer.parseInt(ads[0]);
            int offset = Integer.parseInt(ads[1]);

            String params = String.format(graphqlData, advertiserId, offset, limit);
            Request request = new Request();
            request.setRequestBody(HttpRequestBody.json(params, "UTF-8"));
            request.setMethod(HttpConstant.Method.POST);
            request.setUrl(url+"#"+advertiserId+"."+offset);
            spider.addRequest(request);
//            break;
        }
        spider.thread(6);
        spider.run();
    }

    public static void main(String[] args) {

    }

    /**
     * 根据请求返回的json数据查询某个值，String
     * @param key json-item的键值
     * @param jsonStr json字符串
     * @return 查找到的json-value
     */
    private String jsonPathSelect(String key, String jsonStr) {
        String result = null;
        try {
            result = new JsonPathSelector(key).select(jsonStr);
        } catch (PathNotFoundException e) {
            // logger.error("路径解析错误: " + e.getMessage());
        }
        return result;
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
}
