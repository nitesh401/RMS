package com.rms.model;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RmsRuleResponsePayload {
    private String doctorType;
    private String treatmentType;
    private String benefitType;
    private double benefitValue;
    private Date applicableDate;

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getBenefitType() {
        return benefitType;
    }

    public void setBenefitType(String benefitType) {
        this.benefitType = benefitType;
    }

    public double getBenefitValue() {
        return benefitValue;
    }

    public void setBenefitValue(double benefitValue) {
        this.benefitValue = benefitValue;
    }

    public Date getApplicableDate() {
        return applicableDate;
    }

    public void setApplicableDate(Date applicableDate) {
        this.applicableDate = applicableDate;
    }
}
