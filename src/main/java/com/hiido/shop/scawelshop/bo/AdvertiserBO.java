package com.hiido.shop.scawelshop.bo;


import com.hiido.shop.scawelshop.model.ActionNode;
import lombok.Data;

import java.util.List;

/**
 * @author YSLin
 * 业务对象，用于映射爬取的advertiser对象
 */
@Data
public class AdvertiserBO {
    private int id;
    private int advertiserId;
    private String accountStatus;
    private String sevenDayEpc;
    private String threeMonthEpc;
    private String language;
    private String advertiserName;
    private String programUrl;
    private String relationshipStatus;
    private int mobileTrackingCertified;
    private int cookielessTrackingCertified;
    private String networkRank;
    private String primaryCategoryParent;
    private String primaryCategoryChild;

    private int performanceIncentives;
    private String actions;
    private String linkTypes;

}
