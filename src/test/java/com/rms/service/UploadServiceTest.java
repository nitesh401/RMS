package com.rms.service;

import com.rms.entity.RmsRuleEntity;
import com.rms.model.RmsRuleResponsePayload;
import com.rms.repository.RmsRuleRepository;
import com.rms.utils.GlobalProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class UploadServiceTest {
    @Mock
    RmsRuleRepository rmsRuleRepository;
    @Mock
    GlobalProperties globalProperties;
    @InjectMocks
    UploadService uploadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadRuleFile() {
        Map<String, Object> result = uploadService.uploadRuleFile(null, "Date");
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetAllRmsRule() {
        List<RmsRuleEntity> result = uploadService.getAllRmsRule();
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetRmsRuleBydoctorTypeAndTreatmentType() {
        when(rmsRuleRepository.findByDoctorTypeAndTreatmentTypeOrderByApplicableDateDesc(anyString(), anyString())).thenReturn(List.of(new RmsRuleEntity("doctorType", "treatmentType", "benefitType", 0d, null)));
        when(globalProperties.get(anyString())).thenReturn("getResponse");
        RmsRuleResponsePayload result = uploadService.getRmsRuleBydoctorTypeAndTreatmentType("drType", "treatmentType");
        Assertions.assertNotNull(result);
    }
}
