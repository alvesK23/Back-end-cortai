package sptech.school.backend.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import sptech.school.backend.entities.BarberEntity;
import sptech.school.backend.entities.BarberShopEntity;
import sptech.school.backend.entities.UserEntity;

import javax.persistence.Column;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingRequest {

    @NotNull
    private Long serviceId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime time;

    @NotNull
    private Long barberId;

    @NotNull
    private Long barberShopId;

    @NotNull
    private Long userId;
}
