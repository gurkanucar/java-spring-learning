package org.gucardev.mixed.relationship_cases.user_edu_job.repo;

import org.gucardev.mixed.relationship_cases.user_edu_job.entity.Education;
import org.gucardev.mixed.relationship_cases.user_edu_job.entity.Job;
import org.gucardev.mixed.relationship_cases.user_edu_job.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

