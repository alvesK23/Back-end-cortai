package sptech.school.backend.repositories;


import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
=======
import jakarta.transaction.Transactional;
>>>>>>> d5a90a1f1c6fc7d45f5bb3fd49cc34d4d83ccb4d
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.backend.entities.User;
import sptech.school.backend.entities.enums.Role;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

<<<<<<< HEAD
  Optional<User> findAllByAddress_City(String city);

  Optional<User> findAllByAddress_District(String district);

=======
>>>>>>> d5a90a1f1c6fc7d45f5bb3fd49cc34d4d83ccb4d
  Optional<User> findByCompany(String company);

  List<User> findAllByRole(Role role);
}


