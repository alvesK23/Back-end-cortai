package sptech.school.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.backend.business.services.abstractions.IAddressService;
import sptech.school.backend.comunication.request.AddressRequest;
import sptech.school.backend.comunication.response.AddressResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth/addresses")
@AllArgsConstructor
public class AddressController {

    private final IAddressService service;

    @PostMapping
    ResponseEntity<AddressResponse> save(@RequestBody AddressRequest request) {
        var address = this.service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @GetMapping("/cities/{city}")
    ResponseEntity<List<AddressResponse>> findAllByCity(@PathVariable String city) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAllByCity(city));
    }

    @GetMapping("/neighborhoods/{district}")
    ResponseEntity<List<AddressResponse>> findAllByDistrict(@PathVariable String district) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findAllByDistrict(district));
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<AddressResponse>> findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Optional<AddressResponse>> updateById(@PathVariable Integer id,
                                                         @RequestBody AddressRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.update(id, request));
    }
}
