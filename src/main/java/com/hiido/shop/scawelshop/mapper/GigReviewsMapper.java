package com.hiido.shop.scawelshop.mapper;


import com.hiido.shop.scawelshop.po.GigReviewPo;
import com.hiido.shop.scawelshop.po.ReviewPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GigReviewsMapper {
    // int batchSaveGigReview(List<ReviewPo> list);

    int batchSaveGigReviews(GigReviewPo gigReviewPo);
}
