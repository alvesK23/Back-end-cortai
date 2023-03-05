package sptech.school.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.backend.entities.BarberEntity;
import sptech.school.backend.entities.UserEntity;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findOneByEmailIgnoreCaseAndPassword(String email, String password);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);
}
