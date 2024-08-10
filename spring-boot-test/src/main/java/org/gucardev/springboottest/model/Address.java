package org.gucardev.springboottest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Address extends BaseEntity {

  @Column(nullable = false)
  private String title;

  private String detail;

  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;
}
