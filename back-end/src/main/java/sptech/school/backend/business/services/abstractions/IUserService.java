package sptech.school.backend.business.services.abstractions;

import sptech.school.backend.comunication.request.RegisterRequest;
import sptech.school.backend.comunication.response.UserResponse;

import javax.naming.NotContextException;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserResponse> findAll() throws NotContextException;
    Optional<UserResponse> findByCompany(String company);
    Optional<UserResponse> update(Integer id, RegisterRequest request);
    void delete(Integer id);
}
