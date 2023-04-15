package sptech.school.backend.comunication.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sptech.school.backend.entities.enums.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {

    @NotBlank(message = "name is required")
    @Size(min = 3, message = "min 3 characters required")
    private String company;

    @NotBlank(message = "phone is required")
    @Size(min = 9, message = "min 9 characters required")
    @Size(max = 14, message = "max 18 characters required")
    @Pattern(regexp = "^\\d{2} \\d \\d{4}-\\d{4}$", message = "invalid phone")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "invalid email address")
    private String email;

    @NotBlank
    private AddressRequest address;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "min 6 characters required")
    private String password;

    @NotBlank
    private Role role;
}
