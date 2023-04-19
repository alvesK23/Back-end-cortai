package sptech.school.backend.business.services.abstractions;

import sptech.school.backend.comunication.request.AuthenticationRequest;
import sptech.school.backend.comunication.response.AuthenticationResponse;

import java.util.Optional;

public interface IAuthenticationService {

    Optional<AuthenticationResponse> authenticate(AuthenticationRequest request);
}
