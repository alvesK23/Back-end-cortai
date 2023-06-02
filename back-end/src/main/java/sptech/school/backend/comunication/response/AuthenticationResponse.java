package sptech.school.backend.comunication.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sptech.school.backend.entities.enums.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private Integer id;
    private Role role;
    private String name;
    private String phone;
    private String email;
    private AddressResponse address;

}
