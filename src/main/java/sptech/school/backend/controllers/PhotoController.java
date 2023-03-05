package sptech.school.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.backend.controllers.resource.PhotoResource;
import sptech.school.backend.request.PhotoRequest;
import sptech.school.backend.response.PhotoResponse;
import sptech.school.backend.entities.PhotoEntity;
import sptech.school.backend.repositories.PhotoRepository;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@Component
@RequiredArgsConstructor
public class PhotoController implements PhotoResource {

    //não precisa de autowired se fizer assim
    private final PhotoRepository repository;

    @Override
    public void save(PhotoRequest request) {
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setFiletype(request.getFile());
        photoEntity.setFilename(request.getFileName());
        repository.save(photoEntity);
    }

    @Override
    public PhotoResponse getOne(Long id) {
        PhotoEntity photoEntity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new PhotoResponse(photoEntity.getFiletype(), photoEntity.getFilename());
    }

    @Override
    public List<PhotoResponse> getAll() {
        return repository.findAll().stream().map(
                photoEntity -> new PhotoResponse(photoEntity.getFiletype(), photoEntity.getFilename())).collect(Collectors.toList());
    }
}
