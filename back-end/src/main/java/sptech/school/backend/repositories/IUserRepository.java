package sptech.school.backend.repositories;

import java.awt.*;
import java.lang.annotation.Native;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.NamedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sptech.school.backend.entities.User;
import sptech.school.backend.entities.enums.Role;

public interface IUserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

  Optional<User> findByFirstName(String firstName);

  List<User> findAllByRole(Role role);

  @Modifying
  @Transactional
  @Query("DELETE FROM Token t  WHERE t.id =:id")
  void deleteUser(Integer id);
}


