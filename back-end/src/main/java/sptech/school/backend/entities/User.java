package sptech.school.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sptech.school.backend.entities.enums.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User  {

  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private String phone;
  private String email;
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role = Role.USER;

  @Embedded
  private Address address;
}
