package org.gucardev.entityauditing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;



@Audited
@Entity
@Getter
@Setter
public class Address extends BaseEntity {

  private String street;
  private String city;
  private String country;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
}
