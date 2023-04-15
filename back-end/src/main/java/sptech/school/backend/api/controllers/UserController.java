package sptech.school.backend.api.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.backend.business.services.abstractions.IUserService;
import sptech.school.backend.comunication.request.RegisterRequest;
import sptech.school.backend.comunication.request.UpdateRequest;
import sptech.school.backend.comunication.response.UpdateResponse;
import sptech.school.backend.comunication.response.UserResponse;

import javax.naming.NotContextException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth/users")
public class UserController {

    private final IUserService service;


    @GetMapping("/barbers")
    ResponseEntity<List<UserResponse>> findAll() throws NotContextException {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAll());
    }

    @GetMapping("/barbers/{name}")
    ResponseEntity<Optional<UserResponse>> findById(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByCompany(name));
    }

    @PutMapping("/{id}")
    ResponseEntity<Optional<UpdateResponse>> update(@PathVariable Integer id, @RequestBody UpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.update(id, request));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<UserResponse>> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findById(id));
    }

    @PostMapping
    ResponseEntity<Optional<UserResponse>> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.register(request));
    }
}
