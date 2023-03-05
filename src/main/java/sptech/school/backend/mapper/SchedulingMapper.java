package sptech.school.backend.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sptech.school.backend.request.SchedulingAllRequest;
import sptech.school.backend.request.SchedulingRequest;
import sptech.school.backend.response.SchedulingResponse;
import sptech.school.backend.entities.SchedulingEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SchedulingMapper {

    private final ModelMapper mapper;

    public SchedulingEntity toEntity(SchedulingRequest request) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(request, SchedulingEntity.class);
    }

    public SchedulingEntity toEntityAll(SchedulingAllRequest request) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(request, SchedulingEntity.class);
    }

    public SchedulingResponse toResponse(SchedulingEntity entity) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(entity, SchedulingResponse.class);
    }

    public List<SchedulingResponse> toResponseList(List<SchedulingEntity> list) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
