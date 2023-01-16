package com.hiido.shop.scawelshop.bo;

import lombok.Data;

/**
 * 业务对象，用于映射爬取的advertiser对象
 * @author YSLIN
 * @deprecated
 */

@Data
public class AdvertiseBO {
    /**
     * {"advertiserId":3022407,
     * "advertiserName":"(eUK) eUKhost Ltd",
     * "advertiserUrl":"http://www.eukhost.com",
     * "advertiserStatus":0,
     * "hidden":false,
     * "mobileCertified":false,
     * "crossDeviceEnabled":true,
     * "cookielessTrackingEnabled":false,
     * "functionalCurrency":"GBP",
     * "logoId":10974805,
     * "categoryId":385,
     * "rating":1,
     * "threeMonthEpc":1.58,
     * "sevenDayEpc":7.958,
     * "statuses":[],
     * "categoryIds":[385],
     * "languageIds":[9],
     * "serviceableAreas":["PR","HK","PT","HN","PW","PY","HR","YE","HU","QA","ID","IE","IL","AE","IO","ZA","IS","IT","AN","AQ","AR","AS","AT","RE","ZM","AU","AW","AZ","RO","RS","BD","BE","JM","RU","BG","JO","BH","JP","BI","BJ","BM","BO","SA","BR","SC","BS","BT","SE","BV","SG","SH","SI","SJ","SK","SL","KE","SM","SN","SO","KH","CA","KI","SR","CC","CD","CG","CH","KR","SZ","CL","CM","CN","CO","KW","KY","CR","KZ","TC","TF","CV","TG","TH","CX","CY","LB","TJ","CZ","LC","TM","TN","TO","TR","LK","TT","DE","TW","TZ","DK","LS","LT","DM","LU","LV","DO","UA","UG","MA","MC","DZ","MD","ME","UM","MG","MK","US","ML","EE","MN","EG","MO","MP","EH","MQ","UY","MR","UZ","MS","MT","MU","MV","MW","VA","MX","MY","ER","MZ","VC","ES","ET","VE","VG","NA","VI","NC","NE","NF","VN","NG","NI","NL","NO","NP","FI","NR","FK","FM","FO","NZ","FR","GA","GB","GD","GE","OM","GF","GH","GI","GL","GM","GN","GP","GQ","GR","GS","GT","GU","GW","PA","GY","PE","PF","PG","PH","PL","PM"],
     * "programTerms":[
     *  {"name":"eUKhost Ltd - 65% Flat Commissions",
     *  "id":162470,
     *  "saleMaxFlat":0.0,
     *  "saleMinFlat":0.0,
     *  "saleMinPercent":0.65,
     *  "saleMaxPercent":0.65,
     *  "leadMinFlat":0.0,
     *  "leadMaxFlat":0.0,
     *  "leadMinPercent":0.0,
     *  "leadMaxPercent":0.0,
     *  "minItemLevelFixedLeadCommission":0.0,
     *  "maxItemLevelFixedLeadCommission":0.0,
     *  "minPercentFixedLeadCommission":0.0,
     *  "maxPercentFixedLeadCommission":0.0,
     *  "minItemLevelFixedSaleCommission":0.0,
     *  "maxItemLevelFixedSaleCommission":0.0,
     *  "minPercentSaleCommission":0.65,
     *  "maxPercentSaleCommission":0.65,
     *  "minOrderLevelFixedLeadCommission":0.0,
     *  "maxOrderLevelFixedLeadCommission":0.0,
     *  "minOrderLevelFixedSaleCommission":0.0,
     *  "maxOrderLevelFixedSaleCommission":0.0,
     *  "minViewThroughItemLevelFixedLeadCommission":0.0,
     *  "maxViewThroughItemLevelFixedLeadCommission":0.0,
     *  "minViewThroughPercentLeadCommission":0.0,
     *  "maxViewThroughPercentLeadCommission":0.0,
     *  "minViewThroughItemLevelFixedSaleCommission":0.0,
     *  "maxViewThroughItemLevelFixedSaleCommission":0.0,
     *  "minViewThroughPercentSaleCommission":0.0,
     *  "maxViewThroughPercentSaleCommission":0.0,
     *  "minViewThroughOrderLevelFixedLeadCommission":0.0,
     *  "maxViewThroughOrderLevelFixedLeadCommission":0.0,
     *  "minViewThroughOrderLevelFixedSaleCommission":0.0,
     *  "maxViewThroughOrderLevelFixedSaleCommission":0.0,
     *  "fixedClickCommission":0.0,
     *  "performanceIncentive":false,
     *  "isDefault":false,
     *  "hasSpecialTerms":false,
     *  "dateAccepted":"",
     *  "dateExpired":"",
     *  "advertiserFunctionalCurrency":"GBP",
     *  "sessionBasedTracking":false,
     *  "noCorrections":false
     *  },
     *  {"name":"eUKhost Ltd - 65% Flat Commission","id":169511,"saleMaxFlat":0.0,"saleMinFlat":0.0,"saleMinPercent":0.65,"saleMaxPercent":0.65,"leadMinFlat":0.0,"leadMaxFlat":0.0,"leadMinPercent":0.0,"leadMaxPercent":0.0,"minItemLevelFixedLeadCommission":0.0,"maxItemLevelFixedLeadCommission":0.0,"minPercentFixedLeadCommission":0.0,"maxPercentFixedLeadCommission":0.0,"minItemLevelFixedSaleCommission":0.0,"maxItemLevelFixedSaleCommission":0.0,"minPercentSaleCommission":0.65,"maxPercentSaleCommission":0.65,"minOrderLevelFixedLeadCommission":0.0,"maxOrderLevelFixedLeadCommission":0.0,"minOrderLevelFixedSaleCommission":0.0,"maxOrderLevelFixedSaleCommission":0.0,"minViewThroughItemLevelFixedLeadCommission":0.0,"maxViewThroughItemLevelFixedLeadCommission":0.0,"minViewThroughPercentLeadCommission":0.0,"maxViewThroughPercentLeadCommission":0.0,"minViewThroughItemLevelFixedSaleCommission":0.0,"maxViewThroughItemLevelFixedSaleCommission":0.0,"minViewThroughPercentSaleCommission":0.0,"maxViewThroughPercentSaleCommission":0.0,"minViewThroughOrderLevelFixedLeadCommission":0.0,"maxViewThroughOrderLevelFixedLeadCommission":0.0,"minViewThroughOrderLevelFixedSaleCommission":0.0,"maxViewThroughOrderLevelFixedSaleCommission":0.0,"fixedClickCommission":0.0,"performanceIncentive":false,"isDefault":true,"hasSpecialTerms":false,"dateAccepted":"","dateExpired":"","advertiserFunctionalCurrency":"GBP","sessionBasedTracking":false,"noCorrections":false},
     *  {"name":"eUKhost Ltd June 2014","id":138747,"saleMaxFlat":320.0,"saleMinFlat":1.5,"saleMinPercent":0.0,"saleMaxPercent":0.0,"leadMinFlat":0.0,"leadMaxFlat":0.0,"leadMinPercent":0.0,"leadMaxPercent":0.0,"minItemLevelFixedLeadCommission":0.0,"maxItemLevelFixedLeadCommission":0.0,"minPercentFixedLeadCommission":0.0,"maxPercentFixedLeadCommission":0.0,"minItemLevelFixedSaleCommission":1.5,"maxItemLevelFixedSaleCommission":320.0,"minPercentSaleCommission":0.0,"maxPercentSaleCommission":0.0,"minOrderLevelFixedLeadCommission":0.0,"maxOrderLevelFixedLeadCommission":0.0,"minOrderLevelFixedSaleCommission":0.0,"maxOrderLevelFixedSaleCommission":0.0,"minViewThroughItemLevelFixedLeadCommission":0.0,"maxViewThroughItemLevelFixedLeadCommission":0.0,"minViewThroughPercentLeadCommission":0.0,"maxViewThroughPercentLeadCommission":0.0,"minViewThroughItemLevelFixedSaleCommission":0.0,"maxViewThroughItemLevelFixedSaleCommission":0.0,"minViewThroughPercentSaleCommission":0.0,"maxViewThroughPercentSaleCommission":0.0,"minViewThroughOrderLevelFixedLeadCommission":0.0,"maxViewThroughOrderLevelFixedLeadCommission":0.0,"minViewThroughOrderLevelFixedSaleCommission":0.0,"maxViewThroughOrderLevelFixedSaleCommission":0.0,"fixedClickCommission":0.0,"performanceIncentive":true,"isDefault":false,"hasSpecialTerms":false,"dateAccepted":"","dateExpired":"","advertiserFunctionalCurrency":"GBP","sessionBasedTracking":false,"noCorrections":false},
     *  {"name":"eUKhost Ltd - 65% Flat Commissions + Bonus","id":168525,"saleMaxFlat":0.0,"saleMinFlat":0.0,"saleMinPercent":0.65,"saleMaxPercent":0.65,"leadMinFlat":0.0,"leadMaxFlat":0.0,"leadMinPercent":0.0,"leadMaxPercent":0.0,"minItemLevelFixedLeadCommission":0.0,"maxItemLevelFixedLeadCommission":0.0,"minPercentFixedLeadCommission":0.0,"maxPercentFixedLeadCommission":0.0,"minItemLevelFixedSaleCommission":0.0,"maxItemLevelFixedSaleCommission":0.0,"minPercentSaleCommission":0.65,"maxPercentSaleCommission":0.65,"minOrderLevelFixedLeadCommission":0.0,"maxOrderLevelFixedLeadCommission":0.0,"minOrderLevelFixedSaleCommission":0.0,"maxOrderLevelFixedSaleCommission":0.0,"minViewThroughItemLevelFixedLeadCommission":0.0,"maxViewThroughItemLevelFixedLeadCommission":0.0,"minViewThroughPercentLeadCommission":0.0,"maxViewThroughPercentLeadCommission":0.0,"minViewThroughItemLevelFixedSaleCommission":0.0,"maxViewThroughItemLevelFixedSaleCommission":0.0,"minViewThroughPercentSaleCommission":0.0,"maxViewThroughPercentSaleCommission":0.0,"minViewThroughOrderLevelFixedLeadCommission":0.0,"maxViewThroughOrderLevelFixedLeadCommission":0.0,"minViewThroughOrderLevelFixedSaleCommission":0.0,"maxViewThroughOrderLevelFixedSaleCommission":0.0,"fixedClickCommission":0.0,"performanceIncentive":true,"isDefault":false,"hasSpecialTerms":false,"dateAccepted":"","dateExpired":"","advertiserFunctionalCurrency":"GBP","sessionBasedTracking":false,"noCorrections":false}
     *  ],
     *  "keywords":"eukhost,hosting,web host,web hosts,website hosting,internet,server,web,web hosting,vps hosting,cloud hosting,cloud servers,windows hosting,linux hosting,best affiliate,big commissions,highest commissions,incentives,120 days cookie,webhosting,web hosting uk,uk hosting,cpanel,affordable,dedicated server,wordpress hosting,uk web hosting services,managed hosting,linux vps hosting,euk,cloud,wordpress",
     *  "country":"GB",
     *  "programDescription":" eukhost offers a widerange of web hosting solutions for individuals businesses developers and public sector organisations serving over 35000 customers around the world technology at eukhost is what really sets us apart from other companies our web servers are located in world class datacenters featuring advanced redundancy physical security and multiple fast network connections with more than 100 employees working for eukhost friendly and knowledgeable technical support billing and sales teams are available 24 hours a day 7 days a week to answer any queries you may have what could be better than offering a worthy recommendation to your website visitors and at the same time making a healthy sum on the side for doing so you send them our way well do the rest get in touch with eukhost rogereukhostcom please note  eukhost name and the eukhost logo are registered trademarks of eukhost ltd in england and wales under registration numbers 010730431 and 010730281 eukhost ltd expressly prohibits the use of its registered trademarks without prior written permission from a legallyauthorised representative of eukhost ltd ",
     *  "vasUpgradeType":0,
     *  "isContent":false,
     *  "isInternational":false,
     *  "associatedCategories":[
     *      {"category":{
     *          "id":385,
     *          "type":382,
     *          "suggestedPrice":300.0,
     *          "suggestedNumSponsors":5,
     *          "suggestedAdParentPrice":0.0,
     *          "suggestedAdCatPrice":3600.0,
     *          "vertical":"HOME_AND_BUSINESS_SERVICES"},
     *      "listingType":"CATEGORY",
     *      "advertiserId":3022407,
     *      "status":1,
     *      "significance":1,
     *      "adSpaceSponsorQuarter":0,
     *      "adSpaceSponsorYear":0
     *      }
     * ],
     *      "geographicSource":{"GB":128.169,"IT":3.379,"TR":37.86},
     *      "supportedCurrencies":[],
     *      "advertiserApprovalOdds":"MANUAL_APPROVAL",
     *      "deactivated":false,
     *      "joined":false,
     *      "productEnabled":false,
     *      "newAdvertiser":false
     * }
     */

    private long advertiserId;
    private String advertiserName;
    private String advertiserUrl;
    private int advertiserStatus;
    private boolean hidden;
    private boolean mobileCertified;
    private boolean crossDeviceEnabled;
    private boolean cookielessTrackingEnabled;

    private String functionalCurrency;

    private long logoId;

    private int categoryId;

    private int rating;

    private double threeMonthEpc;

    private double sevenDayEpc;

    private String statuses;

    private String categoryIds;

    private String languageIds;

    private String serviceableAreas;

    private String keywords;

    private String country;

    private String programDescription;

    private int vasUpgradeType;

    private boolean isContent;

    private boolean isInternational;

}
