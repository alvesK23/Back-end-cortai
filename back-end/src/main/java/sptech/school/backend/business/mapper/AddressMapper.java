package sptech.school.backend.business.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import sptech.school.backend.comunication.request.AddressRequest;
import sptech.school.backend.comunication.response.AddressResponse;
import sptech.school.backend.comunication.response.UserResponse;
import sptech.school.backend.entities.Address;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class AddressMapper {

    private ModelMapper mapper;

    public Address toEntity(AddressRequest request, Address address) {
        return mapper.map(request, Address.class);
    }

    public AddressResponse toResponse(Address entity, AddressResponse addressResponse) {
        return mapper.map(entity, AddressResponse.class);
    }

    public AddressResponse requestToResponse(AddressRequest request, AddressResponse addressResponse) {
        return mapper.map(request, AddressResponse.class);
    }

    public List<AddressResponse> toResponseList(List<Address> list) {
        var response = new AddressResponse();
        return list.stream()
                .map(entity -> toResponse(entity, response))
                .collect(Collectors.toList());
    }
}