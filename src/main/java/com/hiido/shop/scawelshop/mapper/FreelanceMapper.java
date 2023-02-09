package com.hiido.shop.scawelshop.mapper;


import com.hiido.shop.scawelshop.po.DeveloperPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface FreelanceMapper {
    int batchSaveFreelance(List<DeveloperPo> list);

    @Select("select * from fiverr_freelance")
    List<DeveloperPo> listFreelance();

    @Select("select * from fiverr_freelance where program_tech_id = #{program_tech_id}")
    List<DeveloperPo> listFreelanceById(@Param("program_tech_id") int programTechId);
}
