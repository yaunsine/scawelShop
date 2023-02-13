package com.hiido.shop.scawelshop.mapper;


import com.hiido.shop.scawelshop.po.GigReviewPo;
import com.hiido.shop.scawelshop.po.ReviewPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @description: TODO 爬取Gig评论数据
 * @author YSLin
 * @date 2023/2/10 17:01
 * @deprecated 已改用GigReviews
 */
@Mapper
public interface GigReviewMapper {
    int batchSaveGigReview(List<ReviewPo> list);

    int batchSaveGigReviews(GigReviewPo gigReviewPo);
}
