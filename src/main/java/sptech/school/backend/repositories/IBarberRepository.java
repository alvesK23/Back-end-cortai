package sptech.school.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.backend.entities.BarberEntity;

import java.util.Optional;

@Repository
public interface IBarberRepository extends JpaRepository<BarberEntity, Long> {

    Optional<BarberEntity> findOneByEmailIgnoreCaseAndPassword(String email, String password);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);
}
