package sptech.school.backend.comunication.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    private Integer id;
    @NotBlank
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "insert a valid zip")
    private String zip;
    private String number;
    private String street;
    private String district;
    private String city;

}


