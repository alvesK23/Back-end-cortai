package sptech.school.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.backend.obj.PilhaObj;
import sptech.school.backend.request.LoginRequest;
import sptech.school.backend.request.UserRequest;
import sptech.school.backend.response.AbilityResponse;
import sptech.school.backend.response.UserResponse;
import sptech.school.backend.mapper.UserMapper;
import sptech.school.backend.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest request) {
        Optional<UserResponse> opt = Stream.of(request)
                .map(mapper::toEntity)
                .map(service::save)
                .map(mapper::toResponse)
                .findFirst();
        return ResponseEntity.status(HttpStatus.CREATED).body(opt.get());
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> barbersResponses = service.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return barbersResponses.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.OK).body(barbersResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {
        service.isSigned(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                               @Valid @RequestBody UserRequest request) {
        var user = service.findById(id);
        if (user.stream().noneMatch(x -> x.getId().equals(id)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return Stream.of(request)
                .map(mapper::toEntity)
                .map(brbr -> service.update(id, brbr))
                .map(mapper::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .findFirst()
                .get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var user = service.findById(id);
        if (user.stream().noneMatch(x -> x.getId().equals(id)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/stack")
    public ResponseEntity<String[]> loggingUsers() {

        List<UserResponse> usersResponses = service.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

        PilhaObj<String> stackLoggingUsers = new PilhaObj<>(usersResponses.size());


        for (int i = 0; i < usersResponses.size(); i++) {

            if (usersResponses.get(i).isSigned() == true) {

                stackLoggingUsers.push(usersResponses.get(i).getEmail());
            }
        }

        String[] loggingUsers = new String[stackLoggingUsers.getTopo()+1];

        int i = 0;
        while (!stackLoggingUsers.isEmpty()) {

            loggingUsers[i++] = stackLoggingUsers.pop();
        }


        return ResponseEntity.status(200).body(loggingUsers);
    }
}
