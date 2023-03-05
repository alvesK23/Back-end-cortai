package sptech.school.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.backend.request.AddressRequest;
import sptech.school.backend.response.AddressResponse;
import sptech.school.backend.mapper.AddressMapper;
import sptech.school.backend.response.SimpleAddressResponse;
import sptech.school.backend.services.AddressService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService service;
    private final AddressMapper mapper;

    @PostMapping
    public ResponseEntity<AddressResponse> save(@Valid @RequestBody AddressRequest request) {
        Optional<AddressResponse> opt = Stream.of(request)
                .map(mapper::toEntity)
                .map(service::save)
                .map(mapper::toResponse)
                .findFirst();
        return ResponseEntity.status(HttpStatus.CREATED).body(opt.get());
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> findAll() {
        List<AddressResponse> barbersResponses = service.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return barbersResponses.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.OK).body(barbersResponses);
    }

    @GetMapping("/simple")
    public ResponseEntity<List<SimpleAddressResponse>> findAllSimple() {
        List<SimpleAddressResponse> barbersResponses = service.findAll()
                .stream()
                .map(mapper::toSimpleResponse)
                .collect(Collectors.toList());
        return barbersResponses.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.OK).body(barbersResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> findById(@PathVariable Long id) {
        return service.findByid(id)
                .map(mapper::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/simple/{id}")
    public ResponseEntity<SimpleAddressResponse> findSimpleById(@PathVariable Long id) {
        return service.findByid(id)
                .map(mapper::toSimpleResponse)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> update(@PathVariable Long id,
                                                  @Valid @RequestBody AddressRequest request) {
        var address = service.findByid(id);
        if (address.stream().noneMatch(x -> x.getId().equals(id)))
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
        var address = service.findByid(id);
        if (address.stream().noneMatch(x -> x.getId().equals(id)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
