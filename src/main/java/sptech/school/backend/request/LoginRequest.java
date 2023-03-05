package sptech.school.backend.request;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Email(message = "Esse email não é vállido")
    @NotBlank
    public String email;

    @NotBlank
    public String password;
}
