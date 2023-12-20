package com.rms.controller;

import com.rms.entity.RmsRuleEntity;
import com.rms.model.ApiResponse;
import com.rms.model.IApiResponse;
import com.rms.model.RmsRuleResponsePayload;
import com.rms.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("applicableDate") String applicableDate) {
        Map<String, Object> response = this.uploadService.uploadRuleFile(file,applicableDate);
        if(response.isEmpty()) {
            return ResponseEntity.ok().body(new ApiResponse(response, true,
                    " Data Uploaded successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(response.get("ERROR"),true,"Unable to Fetch the details"));
    }

    @GetMapping("/rule")
    public List<RmsRuleEntity> getAllProduct() {
        return this.uploadService.getAllRmsRule();
    }


    @GetMapping(value = "/rules")
    public ResponseEntity<IApiResponse> getRmsRuleBydoctorTypeAndTreatmentTypeList(@RequestParam(value = "doctorType") String doctorType,
                                                                                   @RequestParam(value = "treatmentType") String treatmentType) {
        RmsRuleResponsePayload response;
        if (doctorType != null) {
            response = uploadService.getRmsRuleBydoctorTypeAndTreatmentType(doctorType,treatmentType);
        } else {
         response= uploadService.getRmsRuleBydoctorTypeAndTreatmentType(doctorType,treatmentType);
        }
        return ResponseEntity.ok().body(new ApiResponse(response, true,
                "details fetched successfully"));
    }
}
