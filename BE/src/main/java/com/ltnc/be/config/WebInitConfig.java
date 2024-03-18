package com.ltnc.be.config;

import com.lgsi.lgsibe.dto.companyProfile.request.CompanyProfileRequest;
import com.lgsi.lgsibe.dto.financialInfo.request.FinancialInfoDetailUpsertRequest;
import com.lgsi.lgsibe.dto.financialInfo.request.FinancialInfoUpsertRequest;
import com.lgsi.lgsibe.dto.financialSummary.request.FinancialSummaryRequest;
import com.lgsi.lgsibe.dto.ourServiceCategory.request.OurServiceCategoryRequest;
import com.lgsi.lgsibe.dto.user.request.SaveUserRequest;
import com.lgsi.lgsibe.entity.enums.CompanyProfileType;
import com.lgsi.lgsibe.entity.enums.FinancialInfoType;
import com.lgsi.lgsibe.entity.enums.FinancialSummaryType;
import com.lgsi.lgsibe.service.admin.*;
import com.lgsi.lgsibe.service.common.UserService;
import com.lgsi.lgsibe.util.InitUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Profile(value = {"local", "dev", "client"})
@RequiredArgsConstructor
public class WebInitConfig {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.createAdminUser();
        initService.createCompanyProfile();
        initService.createServiceCategory();
        initService.createFinancialSummary();
        initService.createServiceSpaceProduct();
        initService.createFinancialInfo();
    }

    @Service
    @RequiredArgsConstructor
    @Transactional(readOnly = true)
    static class InitService {

        private final UserService userService;
        private final AdminCompanyProfileService adminCompanyProfileService;
        private final AdminOurServiceCategoryService adminOurServiceCategoryService;
        private final AdminServiceSpaceService adminServiceSpaceService;
        private final AdminServiceProductService adminServiceProductService;
        private final AdminFinancialSummaryService adminFinancialSummaryService;
        private final AdminFinancialInfoService adminFinancialInfoService;
        private final InitUtil initUtil;

        @Transactional
        public void createAdminUser() {
            if (!userService.existAdminUser()) {
                userService.saveUser(new SaveUserRequest("admin", "admin1234!"));
            }
        }

        @Transactional
        public void createCompanyProfile() {
            adminCompanyProfileService.upsertCompanyProfile(
                    CompanyProfileRequest.builder()
                            .type(CompanyProfileType.COMPANY_PROFILE)
                            .build());

            adminCompanyProfileService.upsertCompanyProfile(
                    CompanyProfileRequest.builder()
                            .type(CompanyProfileType.CI)
                            .build());

            adminCompanyProfileService.upsertCompanyProfile(
                    CompanyProfileRequest.builder()
                            .type(CompanyProfileType.BI)
                            .build());
        }

        @Transactional
        public void createServiceCategory() {
            if (adminOurServiceCategoryService.findAll().isEmpty()) {
                List<OurServiceCategoryRequest> requests = new ArrayList<>();
                requests.add(new OurServiceCategoryRequest("빌딩"));
                requests.add(new OurServiceCategoryRequest("매장"));
                requests.add(new OurServiceCategoryRequest("공장"));
                requests.add(new OurServiceCategoryRequest("병원"));
                requests.add(new OurServiceCategoryRequest("연구소"));
                requests.add(new OurServiceCategoryRequest("전산센터"));
                requests.forEach(adminOurServiceCategoryService::save);
            }
        }

        @Transactional
        public void createFinancialSummary() {
            adminFinancialSummaryService.upsertFinancialSummary(
                    FinancialSummaryRequest.builder()
                            .type(FinancialSummaryType.TOTAL_ASSETS)
                            .value("")
                            .build());

            adminFinancialSummaryService.upsertFinancialSummary(
                    FinancialSummaryRequest.builder()
                            .type(FinancialSummaryType.TAKE)
                            .value("")
                            .build());

            adminFinancialSummaryService.upsertFinancialSummary(
                    FinancialSummaryRequest.builder()
                            .type(FinancialSummaryType.NET_INCOME)
                            .value("")
                            .build());
        }

        @Transactional
        public void createFinancialInfo() {
            if (adminFinancialInfoService.countAll() == 0) {
                List<FinancialInfoUpsertRequest> request = initUtil.generateInitFinancialInfoRequest();
                adminFinancialInfoService.upsertFinancialInfos(request);
            }
        }

        @Transactional
        public void createServiceSpaceProduct() {
            adminServiceProductService.save();
            adminServiceSpaceService.save();
        }
    }

}
