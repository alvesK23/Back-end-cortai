package sptech.school.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private Long id;

    private String zip;

    private String number;

    private String street;

    private String district;

    private String city;

    private String state;

    private BarberShopResponse barberShop;
}
