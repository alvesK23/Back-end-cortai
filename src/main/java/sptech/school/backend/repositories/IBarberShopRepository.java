package sptech.school.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.backend.entities.BarberEntity;
import sptech.school.backend.entities.BarberShopEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBarberShopRepository extends JpaRepository<BarberShopEntity, Long> {

    Optional<BarberShopEntity> findOneByEmailIgnoreCaseAndPassword(String email, String password);

    Optional<BarberShopEntity> findByCnpj(String cnpj);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);
}
