package sptech.school.backend.business.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sptech.school.backend.business.services.abstractions.IAddressService;
import sptech.school.backend.comunication.request.AddressRequest;
import sptech.school.backend.comunication.response.AddressResponse;
import sptech.school.backend.entities.Address;
import sptech.school.backend.repositories.IAddressRepository;

import javax.naming.NotContextException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {

    private final ModelMapper mapper = new ModelMapper();
    private final IAddressRepository repository;

    @Override
    public AddressResponse save(AddressRequest request) {
        var address = mapper.map(request, Address.class);
        this.repository.save(address);

        return mapper.map(address, AddressResponse.class);
    }

    @Override
    public Optional<AddressResponse> update(Integer id, AddressRequest request) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<AddressResponse> findAll() {
        return null;
    }

    @Override
    public List<AddressResponse> findAllByCity(String city) {
        var addresses = this.repository.findAllByCity(city);

        return toResponseList(addresses);
    }

    @Override
    public List<AddressResponse> findAllByDistrict(String district) {
        var addresses = this.repository.findAllByDistrict(district);

        return toResponseList(addresses);
    }

    private AddressResponse toResponse(Address entity) {
        return mapper.map(entity, AddressResponse.class);
    }

    private List<AddressResponse> toResponseList(List<Address> list) {
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }


}
