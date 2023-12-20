package com.rms.repository;
import com.rms.entity.RmsRuleEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface RmsRuleRepository extends JpaRepository<RmsRuleEntity,Long> {
    List<RmsRuleEntity> findByDoctorTypeAndTreatmentTypeOrderByApplicableDateDesc(String drType, String treatmentType);
}
