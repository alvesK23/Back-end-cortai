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
public class UpdateResponse {

    private Integer id;
    private String name;
    private String phone;
    private AddressResponse address;
    private String email;
    private Role role;
}
