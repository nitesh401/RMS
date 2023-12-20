package com.rms.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@NoArgsConstructor
@Data
@Table(name = "rms_rule")
public class RmsRuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "doctor_type")
    private String doctorType;
    @Column(name = "treatment_Type")
    private String treatmentType;
    @Column(name = "benefit_Type")
    private String benefitType;
    @Column(name = "benefit_Value")
    private double benefitValue;
    @Temporal(TemporalType.DATE)
    @Column(name ="applicable_date")
    private Date applicableDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on", nullable = false)
    private Date createdOn=new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", nullable = false)
    private Date updatedOn=new Date();


    public RmsRuleEntity(String doctorType, String treatmentType, String benefitType, double benefitValue, Date applicableDate) {
        this.doctorType = doctorType;
        this.treatmentType = treatmentType;
        this.benefitType = benefitType;
        this.benefitValue = benefitValue;
        this.applicableDate = applicableDate;
    }
}
