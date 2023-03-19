package sptech.school.backend.business.services.abstractions;

import sptech.school.backend.comunication.request.AddressRequest;
import sptech.school.backend.comunication.response.AddressResponse;

import java.util.List;
import java.util.Optional;

public interface IAddressService {

    AddressResponse save(AddressRequest request);
    Optional<AddressResponse> findById(Integer id);
    Optional<AddressResponse> update(Integer id, AddressRequest request);
    List<AddressResponse> findAllByCity(String city);
    List<AddressResponse> findAllByDistrict(String district);
}
