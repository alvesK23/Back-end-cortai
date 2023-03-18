package sptech.school.backend.repositories;


import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.school.backend.entities.User;
import sptech.school.backend.entities.enums.Role;

public interface IUserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  Optional<User> findByCompany(String company);

  List<User> findAllByRole(Role role);

  @Modifying
  @Transactional
  @Query("DELETE FROM Token t  WHERE t.id =:id")
  void deleteUser(Integer id);
}


