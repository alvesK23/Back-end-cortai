package sptech.school.backend.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.backend.entities.User;
import sptech.school.backend.entities.enums.Role;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  Optional<User> findAllByAddress_City(String city);

  Optional<User> findAllByAddress_District(String district);

  Optional<User> findByName(String name);

  List<User> findAllByRole(Role role);
}


