package sptech.school.backend.business.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sptech.school.backend.business.services.abstractions.IAuthenticationService;
import sptech.school.backend.comunication.request.AuthenticationRequest;
import sptech.school.backend.comunication.response.AuthenticationResponse;
import sptech.school.backend.entities.User;
import sptech.school.backend.infrastructure.exceptions.BadRequestException;
import sptech.school.backend.infrastructure.exceptions.NotFoundException;
import sptech.school.backend.repositories.IUserRepository;

import javax.swing.text.html.Option;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final IUserRepository repository;

<<<<<<< HEAD
    @Override
    public Optional<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        var user = modelMapper.map(request, User.class);
=======
  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
            .company(request.getCompany())
        .phone(request.getPhone())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
>>>>>>> d5a90a1f1c6fc7d45f5bb3fd49cc34d4d83ccb4d

        var optUser= repository.findByEmail(user.getEmail());
        var response = modelMapper.map(optUser, AuthenticationResponse.class);

        if (!optUser.isPresent()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        if (optUser.isPresent() && optUser.get().getPassword()
                .equals(Base64.getEncoder().encodeToString(request.getPassword().getBytes()))) {
            return Optional.of(response);
        }

        throw new BadRequestException("Usuário ou senha incorretos");
    }
}
