package com.hiido.shop.scawelshop.mapper;

import com.hiido.shop.scawelshop.po.ProgramTechPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ProgramTechMapper {
    @Select("select * from fiverr_program_tech")
    List<ProgramTechPo> listProgramTechs();
}
