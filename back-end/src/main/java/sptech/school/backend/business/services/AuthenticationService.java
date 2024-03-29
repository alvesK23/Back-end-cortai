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
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final IUserRepository repository;

    @Override
    public Optional<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        var user = modelMapper.map(request, User.class);

        var optUser = repository.findByEmail(user.getEmail());

        if (optUser.isEmpty()) {
            throw new NotFoundException("Usuário não encontrado");
        }

        String encodedPassword = Base64.getEncoder().encodeToString(request.getPassword().getBytes(StandardCharsets.UTF_8));
        String encodedCurrentPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes(StandardCharsets.UTF_8));

        if (encodedPassword.equals(encodedCurrentPassword)) {
            var response = modelMapper.map(optUser.get(), AuthenticationResponse.class);
            return Optional.of(response);
        }

        throw new BadRequestException("Usuário ou senha incorretos");
    }
}
