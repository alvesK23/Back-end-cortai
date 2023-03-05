package sptech.school.backend.request;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {


    @NotBlank(message = "Address is required")
    @Pattern(regexp = "[0-9]{5}[0-9]{3}")
    private String zip;

    private String number;

    private String street;

    private String district;

    private String city;

    private String state;

    private Long barberShopId;

}
