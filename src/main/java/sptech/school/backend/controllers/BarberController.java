package sptech.school.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.backend.obj.PilhaObj;
import sptech.school.backend.request.BarberRequest;
import sptech.school.backend.request.LoginRequest;
import sptech.school.backend.response.BarberResponse;
import sptech.school.backend.mapper.BarberMapper;
import sptech.school.backend.response.UserResponse;
import sptech.school.backend.services.BarberService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/barbers")
public class BarberController {

    private final BarberService service;
    private final BarberMapper mapper;

    @PostMapping
    public ResponseEntity<BarberResponse> save(@Valid @RequestBody BarberRequest request) {
        Optional<BarberResponse> optBarber = Stream.of(request)
                .map(mapper::toEntity)
                .map(service::save)
                .map(mapper::toResponse)
                .findFirst();
        return ResponseEntity.status(HttpStatus.CREATED).body(optBarber.get());
    }

    @GetMapping
    public ResponseEntity<List<BarberResponse>> findAll() {
        List<BarberResponse> barbersResponses = service.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return barbersResponses.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.OK).body(barbersResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarberResponse> findById(@PathVariable Long id) {
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
    public ResponseEntity<BarberResponse> update(@PathVariable Long id,
                                                 @Valid @RequestBody BarberRequest request) {
        var barber = service.findById(id);
        if (barber.stream().noneMatch(x -> x.getId().equals(id)))
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
        var barber = service.findById(id);
        if (barber.stream().noneMatch(x -> x.getId().equals(id)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/stack")
    public ResponseEntity<String[]> loggingUsers() {

        List<BarberResponse> barbersResponses = service.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

        PilhaObj<String> stackLoggingBarbers = new PilhaObj<>(barbersResponses.size());


        for (int i = 0; i < barbersResponses.size(); i++) {

            if (barbersResponses.get(i).isSigned() == true) {

                stackLoggingBarbers.push(barbersResponses.get(i).getEmail());
            }
        }

        String[] loggingBarbers = new String[stackLoggingBarbers.getTopo()+1];

        int i = 0;
        while (!stackLoggingBarbers.isEmpty()) {

            loggingBarbers[i++] = stackLoggingBarbers.pop();
        }


        return ResponseEntity.status(200).body(loggingBarbers);
    }
}
