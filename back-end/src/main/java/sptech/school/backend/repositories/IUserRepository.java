package sptech.school.backend.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.backend.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
