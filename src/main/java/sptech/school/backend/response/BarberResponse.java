package sptech.school.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BarberResponse {

    private Long id;

    private String name;

    private String surname;

    private boolean isSigned;

    private String email;

    private String phone;

    private BarberShopResponse barberShop;
}
