package sptech.school.backend.comunication.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @NotBlank
    @Pattern(regexp = "(^\\d{2}[.]?\\d{3})[-]?(\\d{3}$)", message = "insert a valid zip")
    private String zip;
    private String number;
    private String street;
    private String district;
    private String city;
}
