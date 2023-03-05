package sptech.school.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarberShopResponse {

    private Long id;

    private String company;

    private String cnpj;

    private boolean isSigned;

    private String phone;

    private String email;
}
