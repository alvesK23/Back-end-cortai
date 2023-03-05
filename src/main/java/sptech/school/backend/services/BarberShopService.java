package sptech.school.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.backend.entities.BarberShopEntity;
import sptech.school.backend.exception.BadRequestException;
import sptech.school.backend.exception.BusinessException;
import sptech.school.backend.repositories.IBarberShopRepository;
import sptech.school.backend.request.LoginRequest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BarberShopService {

    private final IBarberShopRepository repository;

    public BarberShopEntity save(BarberShopEntity entity) {
        boolean existsCnpj = false;

        Optional<BarberShopEntity> optShop = repository.findByCnpj(entity.getCnpj());

        if (optShop.isPresent())
            if (!optShop.get().getId().equals(entity.getId()))
                existsCnpj = true;

        if (entity.getCompany().length() < 3)
            throw new BadRequestException("Nome deve ter pelo menos 5 caracteres.");


        if (repository.existsByEmail(entity.getEmail()))
            throw new BadRequestException("Esse e-mail já existe.");


        if (repository.existsByPhone(entity.getPhone()))
            throw new BadRequestException("Esse telefone já está cadastrado.");

        if (existsCnpj)
            throw new BusinessException("CNPJ já cadastrado!");

        return repository.save(entity);
    }

    public Boolean isSigned(LoginRequest request) {
        var user = repository.findOneByEmailIgnoreCaseAndPassword(
                request.getEmail(), request.getPassword());

        if (user.isEmpty())
            throw new BusinessException("Dados incorretos");

        user.get().setSigned(true);
        return true;
    }

    public BarberShopEntity update(Long id, BarberShopEntity entity) {
        Optional<BarberShopEntity> optShop = this.findById(id);

        if (optShop.isEmpty())
            throw new BusinessException("Barbearia não cadastrada!");

        entity.setId(id);
        return save(entity);
    }

    public Optional<BarberShopEntity> findById(Long id) {
        return repository.findById(id);
    }

    public List<BarberShopEntity> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
