package sptech.school.backend.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sptech.school.backend.request.BarberShopRequest;
import sptech.school.backend.response.BarberShopResponse;
import sptech.school.backend.entities.BarberShopEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BarberShopMapper {

    private final ModelMapper mapper;

    public BarberShopEntity toEntity(BarberShopRequest request) {
        return mapper.map(request, BarberShopEntity.class);
    }

    public BarberShopResponse toResponse(BarberShopEntity entity) {
        return mapper.map(entity, BarberShopResponse.class);
    }

    public List<BarberShopResponse> toResponseList(List<BarberShopEntity> list) {
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
