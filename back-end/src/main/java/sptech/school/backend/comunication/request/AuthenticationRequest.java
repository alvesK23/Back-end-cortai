package sptech.school.backend.comunication.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

  private Integer id;

  @NotBlank(message = "Email is required")
  @Email(message = "invalid email address")
  private String email;

  private String password;
}
