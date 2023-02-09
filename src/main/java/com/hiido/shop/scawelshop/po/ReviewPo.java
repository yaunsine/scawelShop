package com.hiido.shop.scawelshop.po;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewPo {
    int id;
    String imgSrc;
    String username;
    String countryName;
    String ratingScore;
    String time;
    String websiteType;
    String platFormAndTool;
    String score;
    String url;
    int freelanceId;
    String websiteFeature;
}
