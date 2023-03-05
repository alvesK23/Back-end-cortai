package sptech.school.backend.request;

import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarberShopRequest {

    @NotBlank(message = "Company is required")
    private String company;

    @NotBlank
    @CNPJ(message = "Esse CNPJ não é válido")
    private String cnpj;

    @Size(min = 9, max = 11, message = "Telefone inválido")
    @NotBlank(message = "phone is required")
    @Pattern(regexp = "(^\\d{8,11}$)")
    private String phone;

    @Email
    private String email;

    @NotBlank
    @Size(min = 8, message = "A senha deve conter pelo menos 15 caracteres.")
    @Pattern(regexp = "^([a-zA-Z0-9@*#]{8,15})$", message = "A senha deve ter entre 8 e 15 caracteres, " +
            "conter pelo menos um caractérer numérico e um especial")
    private String password;
}
