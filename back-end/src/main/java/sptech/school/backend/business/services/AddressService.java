package sptech.school.backend.business.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sptech.school.backend.business.exceptions.ResourceNotFoundException;
import sptech.school.backend.business.services.abstractions.IAddressService;
import sptech.school.backend.comunication.request.AddressRequest;
import sptech.school.backend.comunication.response.AddressResponse;
import sptech.school.backend.entities.Address;
import sptech.school.backend.repositories.IAddressRepository;

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
        var address = this.mapper.map(request, Address.class);
        this.repository.save(address);

        return this.mapper.map(address, AddressResponse.class);
    }

    @Override
    public Optional<AddressResponse> findById(Integer id) {
        var address = this.repository.findById(id);
        var response =  this.mapper.map(address, AddressResponse.class);

        return Optional.of(response);
    }

    @Override
    public Optional<AddressResponse> update(Integer id, AddressRequest request) {
        var address = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("address not found"));

        this.mapper.map(request, address);
        this.repository.save(address);

        var response = this.mapper.map(address, AddressResponse.class);

        return Optional.of(response);
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
