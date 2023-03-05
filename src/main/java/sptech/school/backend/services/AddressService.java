package sptech.school.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.backend.entities.AddressEntity;
import sptech.school.backend.exception.BusinessException;
import sptech.school.backend.repositories.IAddressRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {

    private final IAddressRepository repository;

    public AddressEntity save(AddressEntity entity) {
        return repository.save(entity);
    }

    public AddressEntity update(Long id, AddressEntity entity) {
        Optional<AddressEntity> opt = repository.findById(id);

        if (opt.isEmpty())
            throw new BusinessException("Endereço não cadastrado");

        entity.setId(id);
        return save(entity);
    }

    public List<AddressEntity> findAll() {
        return repository.findAll();
    }

    public Optional<AddressEntity> findByid(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
