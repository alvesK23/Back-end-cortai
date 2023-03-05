package sptech.school.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.backend.exception.BadRequestException;
import sptech.school.backend.request.LoginRequest;
import sptech.school.backend.entities.BarberEntity;
import sptech.school.backend.exception.BusinessException;
import sptech.school.backend.repositories.IBarberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BarberService {

    private final IBarberRepository repository;

    public BarberEntity save(BarberEntity entity) {

        if (entity.getName().length() < 3)
            throw new BadRequestException("Nome deve ter pelo menos 5 caracteres.");


        if (repository.existsByEmail(entity.getEmail()))
            throw new BadRequestException("Esse e-mail já existe.");


        if (repository.existsByPhone(entity.getPhone()))
            throw new BadRequestException("Esse telefone já está cadastrado.");

        return repository.save(entity);
    }

    public BarberEntity update(Long id, BarberEntity entity) {
        Optional<BarberEntity> opt = repository.findById(id);

        if (opt.isEmpty())
            throw new BusinessException("Barbearia não cadastrada");

        entity.setId(id);
        return save(entity);
    }

    public List<BarberEntity> findAll() {
        return repository.findAll();
    }

    public Optional<BarberEntity> findById(Long id) {
        return repository.findById(id);
    }

    public Boolean isSigned(LoginRequest request) {
        var user = repository.findOneByEmailIgnoreCaseAndPassword(
                request.getEmail(), request.getPassword());

        if (user.isEmpty())
            throw new BusinessException("Dados incorretos");

        user.get().setSigned(true);
        return true;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
