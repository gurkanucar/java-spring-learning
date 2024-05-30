package com.gucardev.springlearning.relationship_cases.user_edu_job.mapper;

import com.gucardev.springlearning.relationship_cases.user_edu_job.dto.UserDTO;
import com.gucardev.springlearning.relationship_cases.user_edu_job.dto.UserDTO.EducationDTO;
import com.gucardev.springlearning.relationship_cases.user_edu_job.dto.UserDTO.JobDTO;
import com.gucardev.springlearning.relationship_cases.user_edu_job.entity.Education;
import com.gucardev.springlearning.relationship_cases.user_edu_job.entity.Job;
import com.gucardev.springlearning.relationship_cases.user_edu_job.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T21:47:10+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setName( userDTO.getName() );
        user.setEducations( educationDTOListToEducationList( userDTO.getEducations() ) );
        user.setJobs( jobDTOListToJobList( userDTO.getJobs() ) );

        setUserBackReference( user );

        return user;
    }

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setEducations( educationListToEducationDTOList( user.getEducations() ) );
        userDTO.setJobs( jobListToJobDTOList( user.getJobs() ) );

        return userDTO;
    }

    protected Education educationDTOToEducation(EducationDTO educationDTO) {
        if ( educationDTO == null ) {
            return null;
        }

        Education education = new Education();

        education.setId( educationDTO.getId() );
        education.setDegree( educationDTO.getDegree() );
        education.setUniversity( educationDTO.getUniversity() );

        return education;
    }

    protected List<Education> educationDTOListToEducationList(List<EducationDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Education> list1 = new ArrayList<Education>( list.size() );
        for ( EducationDTO educationDTO : list ) {
            list1.add( educationDTOToEducation( educationDTO ) );
        }

        return list1;
    }

    protected Job jobDTOToJob(JobDTO jobDTO) {
        if ( jobDTO == null ) {
            return null;
        }

        Job job = new Job();

        job.setId( jobDTO.getId() );
        job.setTitle( jobDTO.getTitle() );
        job.setCompany( jobDTO.getCompany() );

        return job;
    }

    protected List<Job> jobDTOListToJobList(List<JobDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Job> list1 = new ArrayList<Job>( list.size() );
        for ( JobDTO jobDTO : list ) {
            list1.add( jobDTOToJob( jobDTO ) );
        }

        return list1;
    }

    protected EducationDTO educationToEducationDTO(Education education) {
        if ( education == null ) {
            return null;
        }

        EducationDTO educationDTO = new EducationDTO();

        educationDTO.setId( education.getId() );
        educationDTO.setDegree( education.getDegree() );
        educationDTO.setUniversity( education.getUniversity() );

        return educationDTO;
    }

    protected List<EducationDTO> educationListToEducationDTOList(List<Education> list) {
        if ( list == null ) {
            return null;
        }

        List<EducationDTO> list1 = new ArrayList<EducationDTO>( list.size() );
        for ( Education education : list ) {
            list1.add( educationToEducationDTO( education ) );
        }

        return list1;
    }

    protected JobDTO jobToJobDTO(Job job) {
        if ( job == null ) {
            return null;
        }

        JobDTO jobDTO = new JobDTO();

        jobDTO.setId( job.getId() );
        jobDTO.setTitle( job.getTitle() );
        jobDTO.setCompany( job.getCompany() );

        return jobDTO;
    }

    protected List<JobDTO> jobListToJobDTOList(List<Job> list) {
        if ( list == null ) {
            return null;
        }

        List<JobDTO> list1 = new ArrayList<JobDTO>( list.size() );
        for ( Job job : list ) {
            list1.add( jobToJobDTO( job ) );
        }

        return list1;
    }
}
