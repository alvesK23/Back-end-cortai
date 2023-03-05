package sptech.school.backend.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sptech.school.backend.request.AddressRequest;
import sptech.school.backend.response.AddressResponse;
import sptech.school.backend.entities.AddressEntity;
import sptech.school.backend.response.SimpleAddressResponse;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AddressMapper {

    private final ModelMapper mapper;

    public AddressEntity toEntity(AddressRequest request) {
        return mapper.map(request, AddressEntity.class);
    }

    public AddressResponse toResponse(AddressEntity entity) {
        return mapper.map(entity, AddressResponse.class);
    }

    public SimpleAddressResponse toSimpleResponse(AddressEntity entity) {
        return mapper.map(entity, SimpleAddressResponse.class);
    }

    public List<SimpleAddressResponse> toResponseListSimple(List<AddressEntity> list) {
        return list.stream()
                .map(this::toSimpleResponse)
                .collect(Collectors.toList());
    }

    public List<AddressResponse> toResponseList(List<AddressEntity> list) {
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
