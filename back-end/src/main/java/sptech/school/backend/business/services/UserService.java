package sptech.school.backend.business.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sptech.school.backend.business.exceptions.ResourceNotFoundException;
import sptech.school.backend.business.services.abstractions.IUserService;
import sptech.school.backend.comunication.request.RegisterRequest;
import sptech.school.backend.comunication.response.UserResponse;
import sptech.school.backend.entities.User;
import sptech.school.backend.entities.enums.Role;
import sptech.school.backend.repositories.IUserRepository;

import javax.naming.NotContextException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final IUserRepository repository;

    @Override
    public List<UserResponse> findAll() throws NotContextException {
        var users = this.repository.findAllByRole(Role.ADMIN);

        if (users.isEmpty()) {
            throw new NotContextException("Not users are found");
        }

        return toResponseList(users);
    }

    @Override
<<<<<<< HEAD
    public Optional<UserResponse> findByCompany(String company) {
        var user = this.repository.findByCompany(company);
=======
    public Optional<UserResponse> findByFirstName(String firstName) {
        var user = this.repository.findByFirstName(firstName);
>>>>>>> ea52407318cc7f4be8ae116b977f313211f3ca6b

        var response = modelMapper.map(user, UserResponse.class);

        return Optional.of(response);
    }

    private UserResponse toResponse(User entity) {
        return modelMapper.map(entity, UserResponse.class);
    }

    private List<UserResponse> toResponseList(List<User> list) {
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserResponse> update(Integer id, RegisterRequest request) {
        var user = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        modelMapper.map(request, user);
        repository.save(user);

        var response = modelMapper.map(user, UserResponse.class);

        return Optional.of(response);
    }

    @Override
    public void delete(Integer id) {
        var user = this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        this.repository.deleteUser(id);
        this.repository.deleteById(id);
    }
}
