package sptech.school.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.backend.request.AbilityRequest;
import sptech.school.backend.response.AbilityResponse;
import sptech.school.backend.mapper.AbilityMapper;
import sptech.school.backend.obj.PilhaObj;
import sptech.school.backend.services.AbilityService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/skills")
public class AbilityController {

    private final AbilityService service;
    private final AbilityMapper mapper;

    @PostMapping
    public ResponseEntity<AbilityResponse> save(@Valid @RequestBody AbilityRequest request) {
        Optional<AbilityResponse> opt = Stream.of(request)
                .map(mapper::toEntity)
                .map(service::save)
                .map(mapper::toResponse)
                .findAny();
        return ResponseEntity.status(HttpStatus.CREATED).body(opt.get());
    }

    @GetMapping
    public ResponseEntity<List<AbilityResponse>> findAll() {
        List<AbilityResponse> barbersResponses = service.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return barbersResponses.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.OK).body(barbersResponses);
    }



    @GetMapping("/{id}")
    public ResponseEntity<AbilityResponse> findById(@PathVariable Long id) {
        return service.findByid(id)
                .map(mapper::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbilityResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody AbilityRequest request) {
        var ability = service.findByid(id);
        if (ability.stream().noneMatch(x -> x.getId().equals(id)))
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
        var ability = service.findByid(id);
        if (ability.stream().noneMatch(x -> x.getId().equals(id)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
