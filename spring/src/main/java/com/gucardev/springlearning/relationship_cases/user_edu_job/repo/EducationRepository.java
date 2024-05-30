package com.gucardev.springlearning.relationship_cases.user_edu_job.repo;

import com.gucardev.springlearning.relationship_cases.user_edu_job.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}
