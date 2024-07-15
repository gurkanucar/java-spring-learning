package org.gucardev.mixed.relationship_cases.user_edu_job.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = {"user"})
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String company;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
