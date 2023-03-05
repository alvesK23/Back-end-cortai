package sptech.school.backend.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sptech.school.backend.request.BarberRequest;
import sptech.school.backend.response.BarberResponse;
import sptech.school.backend.entities.BarberEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BarberMapper {

    private final ModelMapper mapper;

    public BarberEntity toEntity(BarberRequest request) {
        return mapper.map(request, BarberEntity.class);
    }

    public BarberResponse toResponse(BarberEntity entity) {
        return mapper.map(entity, BarberResponse.class);
    }

    public List<BarberResponse> toResponseList(List<BarberEntity> list) {
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
