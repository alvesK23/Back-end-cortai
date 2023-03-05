package sptech.school.backend.request;


import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbilityRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @DecimalMin(value = "10.0")
    private Double price;

    private String duration;

    private Long barberId;
}
