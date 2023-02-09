package com.hiido.shop.scawelshop.po;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GigReviewPo {
    int id;
    String sellerFrom;
    String sellerRatingCount;
    String reviewsCount;
    String reviewsCountryName;
    String gigDescription;
    String url;
    String freelanceId;
    String buildingTool;

    String sellerName;

    String sellerScore;
}
