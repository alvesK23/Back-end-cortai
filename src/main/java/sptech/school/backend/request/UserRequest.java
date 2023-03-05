package sptech.school.backend.request;

import lombok.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "valor não pode ser nulo ou vazio")
    private String name;

    private String surname;

    @Email(message = "Esse email não é vállido")
    private String email;

    @Size(min = 9, max = 11, message = "Telefone inválido")
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "(^\\d{8,11}$)",
            message = "Indique um telefone válido")
    private String phone;

    @NotBlank
    @Size(min = 8, message = "A senha deve conter pelo menos 15 caracteres.")
    @Pattern(regexp = "^([a-zA-Z0-9@*#]{8,15})$", message = "A senha deve ter entre 8 e 15 caracteres, " +
            "conter pelo menos um caractérer numérico e um especial")
    private String password;
}
