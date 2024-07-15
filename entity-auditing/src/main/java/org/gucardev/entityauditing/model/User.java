package org.gucardev.entityauditing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import jakarta.persistence.*;
import java.util.List;

@Audited
@Entity
@Getter
@Setter
@Table(name = "`user`")
@NoArgsConstructor
public class User extends BaseEntity {

  private String name;
  private String username;

  @NotAudited
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Address> addresses;

}
