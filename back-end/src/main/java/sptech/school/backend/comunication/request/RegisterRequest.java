package sptech.school.backend.comunication.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  @NotBlank(message = "Password is required")
  @Min(value = 3, message = "min 6 characters required")
  private String firstName;

  @NotBlank(message = "Password is required")
  @Min(value = 3, message = "min 6 characters required")
  private String lastName;

  @NotBlank(message = "Email is required")
  @Email(message = "invalid email address")
  private String email;

  @NotBlank(message = "Password is required")
  @Min(value = 6, message = "min 6 characters required")
  private String password;
}
