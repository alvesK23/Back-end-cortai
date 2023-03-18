package sptech.school.backend.comunication.request;

<<<<<<< HEAD
import jakarta.validation.constraints.*;
=======
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
>>>>>>> ea52407318cc7f4be8ae116b977f313211f3ca6b
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

<<<<<<< HEAD
  @NotBlank(message = "name is required")
  @Min(value = 3, message = "min 3 characters required")
  private String company;

  @NotBlank(message = "phone is required")
  @Min(value = 9, message = "min 9 characters required")
  @Max(value = 11, message = "max 11 characters required")
  @Pattern(regexp = "^\\(?\\d{2}\\)?[\\s-]?[\\s9]?\\d{4}-?\\d{4}$", message = "invalid phone")
  private String phone;
=======
  @NotBlank(message = "Password is required")
  @Min(value = 3, message = "min 6 characters required")
  private String firstName;

  @NotBlank(message = "Password is required")
  @Min(value = 3, message = "min 6 characters required")
  private String lastName;
>>>>>>> ea52407318cc7f4be8ae116b977f313211f3ca6b

  @NotBlank(message = "Email is required")
  @Email(message = "invalid email address")
  private String email;

  @NotBlank(message = "Password is required")
  @Min(value = 6, message = "min 6 characters required")
  private String password;
}
