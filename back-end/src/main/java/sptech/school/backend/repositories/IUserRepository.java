package sptech.school.backend.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.backend.entities.User;
import sptech.school.backend.entities.enums.Role;

public interface IUserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByCompany(String company);

  List<User> findAllByRole(Role role);
}


