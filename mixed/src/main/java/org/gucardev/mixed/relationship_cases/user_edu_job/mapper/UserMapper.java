package org.gucardev.mixed.relationship_cases.user_edu_job.mapper;

import com.gucardev.springlearning.relationship_cases.user_edu_job.dto.UserDTO;
import com.gucardev.springlearning.relationship_cases.user_edu_job.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Dto dan Entity e cevirme

    @Mapping(target = "id", ignore = true)
        // bu seylere hiccc gerek yok
//    @Mapping(target = "educations", source = "educations")
//    @Mapping(target = "jobs", source = "jobs")
    User userDTOToUser(UserDTO userDTO);

    // bu kesinlikle olmali referanslar icin
    @AfterMapping
    default void setUserBackReference(@MappingTarget User user) {
        if (user.getEducations() != null) {
            user.getEducations().forEach(education -> education.setUser(user));
        }
        if (user.getJobs() != null) {
            user.getJobs().forEach(job -> job.setUser(user));
        }
    }

    // bu seylere hiccc gerek yok
//    @IterableMapping(qualifiedByName = "educationDTOToEducation")
//    List<Education> educationDTOsToEducations(List<UserDTO.EducationDTO> educationDTOs);
//
//    @IterableMapping(qualifiedByName = "jobDTOToJob")
//    List<Job> jobDTOsToJobs(List<UserDTO.JobDTO> jobDTOs);
//
//    @Named("educationDTOToEducation")
//    Education educationDTOToEducation(UserDTO.EducationDTO educationDTO);
//
//    @Named("jobDTOToJob")
//    Job jobDTOToJob(UserDTO.JobDTO jobDTO);


    // Entity den Dto ya cevirme
    UserDTO toDTO(User user);

}
