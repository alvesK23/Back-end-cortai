package sptech.school.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.school.backend.entities.AbilityEntity;
import sptech.school.backend.exception.BusinessException;
import sptech.school.backend.repositories.IAbilityRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AbilityService {

    private final IAbilityRepository repository;

    public AbilityEntity save(AbilityEntity entity) {
        return repository.save(entity);
    }

    public AbilityEntity update(Long id, AbilityEntity entity) {
        Optional<AbilityEntity> opt = repository.findById(id);

        if (opt.isEmpty())
            throw new BusinessException("Serviço não cadastrado");

        entity.setId(id);
        return save(entity);
    }

    public List<AbilityEntity> findAll() {
        return repository.findAll();
    }

    public Optional<AbilityEntity> findByid(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
