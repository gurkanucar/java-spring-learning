package org.gucardev.mixed.relationship_cases.user_edu_job.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = {"user"})
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String degree;
    private String university;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
