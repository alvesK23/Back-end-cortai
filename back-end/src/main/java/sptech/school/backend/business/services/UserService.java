package sptech.school.backend.business.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sptech.school.backend.business.services.abstractions.IUserService;
import sptech.school.backend.comunication.request.RegisterRequest;
import sptech.school.backend.comunication.request.UpdateRequest;
import sptech.school.backend.comunication.response.UpdateResponse;
import sptech.school.backend.comunication.response.UpdateResponse;
import sptech.school.backend.comunication.response.UserResponse;
import sptech.school.backend.entities.User;
import sptech.school.backend.entities.enums.Role;
import sptech.school.backend.infrastructure.exceptions.NotFoundException;
import sptech.school.backend.repositories.IUserRepository;

import javax.naming.NotContextException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final IUserRepository repository;

    @Override
    public Optional<UserResponse> register(RegisterRequest request) {
        var user = modelMapper.map(request, User.class);

        var password = user.getPassword();
        var passwordEncoder = Base64.getEncoder().encode(password.getBytes());
        user.setPassword(new String(passwordEncoder));

        repository.save(user);

        var response = modelMapper.map(user, UserResponse.class);

        return Optional.of(response);
    }

    @Override
    public List<UpdateResponse> findAll() throws NotContextException {
        var users = this.repository.findAllByRole(Role.BARBER);

        if (users.isEmpty()) {
            throw new NotContextException("Not users are found");
        }

        return toResponseList(users);
    }

    @Override
    public Optional<UpdateResponse> findByCompany(String company) {
        var user = this.repository.findByCompany(company);

        var response = this.modelMapper.map(user, UpdateResponse.class);

        return Optional.of(response);
    }

    @Override
    public Optional<UpdateResponse> findByCity(String city) {
        var user = this.repository.findAllByAddress_City(city);

        var response = this.modelMapper.map(user, UpdateResponse.class);

        return Optional.of(response);
    }

    @Override
    public Optional<UpdateResponse> findByDistrict(String district) {
        var user = this.repository.findAllByAddress_District(district);

        var response = this.modelMapper.map(user, UpdateResponse.class);

        return Optional.of(response);
    }

    @Override
    public Optional<UpdateResponse> findById(Integer id) {
        var user = this.repository.findById(id);

        var response = this.modelMapper.map(user, UpdateResponse.class);

        return Optional.of(response);
    }

    @Override
    public Optional<UpdateResponse> update(Integer id, UpdateRequest request) {
        var user = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        this.modelMapper.map(request, user);
        this.repository.save(user);

        var response = this.modelMapper.map(user, UpdateResponse.class);

        return Optional.of(response);
    }

    @Override
    public void delete(Integer id) {
        var user = this.repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        this.repository.deleteById(id);
    }

    private UpdateResponse toResponse(User entity) {
        return modelMapper.map(entity, UpdateResponse.class);
    }

    private List<UpdateResponse> toResponseList(List<User> list) {
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
