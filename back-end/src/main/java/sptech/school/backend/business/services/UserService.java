package sptech.school.backend.business.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import sptech.school.backend.business.services.abstractions.IUserService;
import sptech.school.backend.comunication.request.RegisterRequest;
import sptech.school.backend.comunication.response.UserResponse;
import sptech.school.backend.entities.User;
import sptech.school.backend.repositories.IUserRepository;

import javax.naming.NotContextException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final IUserRepository repository;
    private final AuthenticationManager authenticationManager;

    @Override
    public List<UserResponse> findAll() throws NotContextException {
        var users = repository.findAll();

        if (users.isEmpty()) {
            throw new NotContextException("Not users are found");
        }

        return toResponseList(users);
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
    public User save(RegisterRequest request) {
        var user = modelMapper.map(request, User.class);
        return repository.save(user);
    }

    @Override
    public UserResponse findById(Integer id) {
        var user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        var response = modelMapper.map(user, UserResponse.class);

        return response;
    }

    @Override
    public User update(Integer id, RegisterRequest request) {
        var user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        modelMapper.map(request, User.class);

        return repository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        var user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        repository.deleteById(user.getId());
    }
}
