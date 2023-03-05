package sptech.school.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.backend.request.BarberShopRequest;
import sptech.school.backend.request.LoginRequest;
import sptech.school.backend.response.BarberShopResponse;
import sptech.school.backend.mapper.BarberShopMapper;
import sptech.school.backend.repositories.IBarberShopRepository;
import sptech.school.backend.services.BarberShopService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/barber-shops")
public class BarberShopController {

    private final BarberShopService service;
    private final BarberShopMapper mapper;

    @PostMapping
    public ResponseEntity<BarberShopResponse> save(@Valid @RequestBody BarberShopRequest request) {
        Optional<BarberShopResponse> optShop = Stream.of(request)
                .map(mapper::toEntity)
                .map(service::save)
                .map(mapper::toResponse)
                .findFirst();
        return ResponseEntity.status(HttpStatus.CREATED).body(optShop.get());
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {
        service.isSigned(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<BarberShopResponse>> findAll() {
        List<BarberShopResponse> shopsResponses = service.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return shopsResponses.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.OK).body(shopsResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarberShopResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(mapper::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarberShopResponse> update(@PathVariable Long id,
                                                     @Valid @RequestBody BarberShopRequest request) {
        var shop = service.findById(id);
        if (shop.stream().noneMatch(x -> x.getId().equals(id)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return Stream.of(request)
                .map(mapper::toEntity)
                .map(bshop -> service.update(id, bshop))
                .map(mapper::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .findFirst()
                .get();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var shop = service.findById(id);
        if (shop.stream().noneMatch(x -> x.getId().equals(id)))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
