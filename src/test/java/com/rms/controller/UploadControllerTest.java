package com.rms.controller;

import com.rms.entity.RmsRuleEntity;
import com.rms.model.IApiResponse;
import com.rms.model.RmsRuleResponsePayload;
import com.rms.service.UploadService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

class UploadControllerTest {
    @Mock
    UploadService uploadService;
    @InjectMocks
    UploadController uploadController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpload() {
        when(uploadService.uploadRuleFile(any(), anyString())).thenReturn(Map.of("String", "uploadRuleFileResponse"));

        ResponseEntity<?> result = uploadController.upload(null, "applicableDate");
        Assertions.assertNotNull( result);
    }

    @Test
    void testGetAllProduct() {
        when(uploadService.getAllRmsRule()).thenReturn(List.of(new RmsRuleEntity("doctorType", "treatmentType", "benefitType", 0d, new GregorianCalendar(2023, Calendar.MAY, 4, 15, 55).getTime())));

        List<RmsRuleEntity> result = uploadController.getAllProduct();
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetRmsRuleBydoctorTypeAndTreatmentTypeList() {
        when(uploadService.getRmsRuleBydoctorTypeAndTreatmentType(anyString(), anyString())).thenReturn(new RmsRuleResponsePayload());

        ResponseEntity<IApiResponse> result = uploadController.getRmsRuleBydoctorTypeAndTreatmentTypeList("doctorType", "treatmentType");
        Assertions.assertNotNull(result);
    }
}
