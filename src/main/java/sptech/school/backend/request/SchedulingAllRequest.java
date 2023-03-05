package sptech.school.backend.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import sptech.school.backend.entities.AbilityEntity;
import sptech.school.backend.entities.BarberEntity;
import sptech.school.backend.entities.BarberShopEntity;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingAllRequest {

    @NotNull
    private BigDecimal price;

    @NotNull
    private String cutName;

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime time;

    @NotNull
    private String name;
}
