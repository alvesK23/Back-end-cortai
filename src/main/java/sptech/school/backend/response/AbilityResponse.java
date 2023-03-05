package sptech.school.backend.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbilityResponse {

    private Long id;

    private String name;

    private Double price;

    private String duration;

    private BarberResponse barber;
}
