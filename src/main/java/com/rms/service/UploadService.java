package com.rms.service;

import com.rms.entity.RmsRuleEntity;
import com.rms.exception.CommonsException;
import com.rms.model.RmsRuleResponsePayload;
import com.rms.repository.RmsRuleRepository;
import com.rms.utils.ConstantUtils;
import com.rms.utils.GlobalProperties;
import com.rms.utils.ReadExcelFileToList;
import com.rms.utils.Utils;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UploadService {
    @Autowired
    private RmsRuleRepository rmsRuleRepository;
    @Autowired
    private GlobalProperties globalProperties;

    public Map<String, Object> uploadRuleFile(MultipartFile file, String Date) {
        Map<String, Object> responseMessage = new HashMap<>();
        try {
            Date applicableDate = Utils.getDateFromString(Date, "yyyy-MM-dd");
            Set<RmsRuleEntity> rules = ReadExcelFileToList.readExcelData(file, applicableDate);
            List<String> rowWithEmptyCells = rules.stream().filter(rule ->
                            StringUtils.isEmpty(rule.getDoctorType()) ||
                                    StringUtils.isEmpty(rule.getTreatmentType()) ||
                                    StringUtils.isEmpty(rule.getBenefitType()) ||
                                    rule.getBenefitValue() == 0.0).map(rule -> rule.getDoctorType())
                    .collect(Collectors.toList());
            if (!rowWithEmptyCells.isEmpty() && rowWithEmptyCells.size() > 0) {
                responseMessage.put("ERROR", "Some of the mandatory fileds are missing in the excel, please connect the same");
                return responseMessage;
            }
            this.rmsRuleRepository.saveAll(rules);
            return new HashMap<>();
        } catch(CommonsException cex) {
            responseMessage.put("ERROR", cex.getMessage());
            return responseMessage;
        }
    }
    public List<RmsRuleEntity> getAllRmsRule() {
        return this.rmsRuleRepository.findAll();
    }

    public RmsRuleResponsePayload getRmsRuleBydoctorTypeAndTreatmentType(String drType, String treatmentType) {
      RmsRuleResponsePayload model = new RmsRuleResponsePayload();
        try {
            Optional<RmsRuleEntity> rmsRuleOpt = rmsRuleRepository.findByDoctorTypeAndTreatmentTypeOrderByApplicableDateDesc(drType, treatmentType).stream().findFirst();
            if (rmsRuleOpt.isEmpty()) {
                throw new CommonsException(globalProperties.get(ConstantUtils.RMSRULE_NOTEXIST_ID));
            }
            if (!rmsRuleOpt.get().getDoctorType().isEmpty() && !rmsRuleOpt.get().getTreatmentType().isEmpty()) {
                model.setDoctorType(rmsRuleOpt.get().getDoctorType());
                model.setTreatmentType(rmsRuleOpt.get().getTreatmentType());
                model.setBenefitType(rmsRuleOpt.get().getBenefitType());
                model.setBenefitValue(rmsRuleOpt.get().getBenefitValue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

}
