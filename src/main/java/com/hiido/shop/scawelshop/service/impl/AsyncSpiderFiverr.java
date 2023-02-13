package com.hiido.shop.scawelshop.service.impl;

import com.hiido.shop.scawelshop.mapper.FreelanceMapper;
import com.hiido.shop.scawelshop.mapper.ProgramTechMapper;
import com.hiido.shop.scawelshop.po.DeveloperPo;
import com.hiido.shop.scawelshop.po.ProgramTechPo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AsyncSpiderFiverr {

    @Resource
    private FreelanceMapper freelanceMapper;

    @Resource
    private ProgramTechMapper programTechMapper;

    @Resource
    private FreelanceSiteProcessor freelanceSiteProcessor;

    public void batchRequestsAsync() {
        List<ProgramTechPo> programTechPoList = programTechMapper.listProgramTechs();

        for (ProgramTechPo programTechPo: programTechPoList) {
            List<DeveloperPo> developerPoList = freelanceMapper.listFreelanceById(programTechPo.getId());
            for (DeveloperPo developerPo: developerPoList) {
                if (developerPo.getId() <= 923) {
                    continue;
                }
                freelanceSiteProcessor.seleniumStart(developerPo.getId(), developerPo.getLink());
            }
        }
    }
}
