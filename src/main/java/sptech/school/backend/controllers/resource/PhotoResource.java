package sptech.school.backend.controllers.resource;

import org.springframework.web.bind.annotation.*;
import sptech.school.backend.request.PhotoRequest;
import sptech.school.backend.response.PhotoResponse;

import java.util.List;

@RestController
@RequestMapping("/photos")
public interface PhotoResource {

    @PostMapping
    void save(@RequestBody PhotoRequest request);

    @GetMapping("/{id}")
    PhotoResponse getOne(@PathVariable Long id);

    @GetMapping
    List<PhotoResponse> getAll();
}
