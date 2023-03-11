package sptech.school.backend.business.services.abstractions;

import sptech.school.backend.comunication.request.RegisterRequest;
import sptech.school.backend.comunication.response.UserResponse;
import sptech.school.backend.entities.User;

import javax.naming.NotContextException;
import java.util.List;

public interface IUserService {

    List<UserResponse> findAll() throws NotContextException;
    User save(RegisterRequest request);
    UserResponse findById(Integer id);
    User update(Integer id, RegisterRequest request);
    void deleteById(Integer id);
}
