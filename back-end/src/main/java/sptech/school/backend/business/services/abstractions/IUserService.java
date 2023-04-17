package sptech.school.backend.business.services.abstractions;

import sptech.school.backend.comunication.request.RegisterRequest;
import sptech.school.backend.comunication.request.UpdateRequest;
import sptech.school.backend.comunication.response.UpdateResponse;
import sptech.school.backend.comunication.response.UserResponse;

import javax.naming.NotContextException;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<UserResponse> register(RegisterRequest request);
    List<UpdateResponse> findAll() throws NotContextException;
    Optional<UpdateResponse> findByCompany(String company);
    Optional<UpdateResponse> findByCity(String city);
    Optional<UpdateResponse> findByDistrict(String district);
    Optional<UpdateResponse> findById(Integer id);
    Optional<UpdateResponse> update(Integer id, UpdateRequest request);
    void delete(Integer id);
}
