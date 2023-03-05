package sptech.school.backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingResponse {

    private Long id;

    private LocalDateTime time;

    private AbilityResponse service;

    private UserResponse user;
}
