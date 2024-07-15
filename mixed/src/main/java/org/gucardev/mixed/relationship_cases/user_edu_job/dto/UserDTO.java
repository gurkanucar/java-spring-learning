package org.gucardev.mixed.relationship_cases.user_edu_job.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private List<EducationDTO> educations;
    private List<JobDTO> jobs;

    @Data
    public static class EducationDTO {
        private Long id;
        private String degree;
        private String university;
    }

    @Data
    public static class JobDTO {
        private Long id;
        private String title;
        private String company;
    }
}
