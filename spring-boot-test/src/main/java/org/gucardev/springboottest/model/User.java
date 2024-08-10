package org.gucardev.springboottest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user_table")
public class User extends BaseEntity {

  @Column(unique = true)
  private String username;

  private String email;

  private String name;

  @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
  private List<Address> addresses;
}
