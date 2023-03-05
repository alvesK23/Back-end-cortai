package sptech.school.backend.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sptech.school.backend.request.AbilityRequest;
import sptech.school.backend.response.AbilityResponse;
import sptech.school.backend.entities.AbilityEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AbilityMapper {

    private final ModelMapper mapper;

    public AbilityEntity toEntity(AbilityRequest request) {
        return mapper.map(request, AbilityEntity.class);
    }

    public AbilityResponse toResponse(AbilityEntity entity) {
        return mapper.map(entity, AbilityResponse.class);
    }

    public List<AbilityResponse> toResponseList(List<AbilityEntity> list) {
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
