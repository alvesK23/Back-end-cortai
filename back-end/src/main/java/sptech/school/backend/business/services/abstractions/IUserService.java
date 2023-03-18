package sptech.school.backend.business.services.abstractions;

import sptech.school.backend.comunication.request.RegisterRequest;
import sptech.school.backend.comunication.response.UserResponse;

import javax.naming.NotContextException;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserResponse> findAll() throws NotContextException;
<<<<<<< HEAD
    Optional<UserResponse> findByCompany(String company);
=======
    Optional<UserResponse> findByFirstName(String firstName);
>>>>>>> ea52407318cc7f4be8ae116b977f313211f3ca6b
    Optional<UserResponse> update(Integer id, RegisterRequest request);
    void delete(Integer id);
}
