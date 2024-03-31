package com.gucardev.springlearning.relationship_cases.user_edu_job.repo;

import com.gucardev.springlearning.relationship_cases.user_edu_job.entity.Education;
import com.gucardev.springlearning.relationship_cases.user_edu_job.entity.Job;
import com.gucardev.springlearning.relationship_cases.user_edu_job.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

