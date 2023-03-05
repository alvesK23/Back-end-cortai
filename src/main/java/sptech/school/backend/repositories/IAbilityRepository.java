package sptech.school.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.backend.entities.AbilityEntity;
import sptech.school.backend.entities.SchedulingEntity;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface IAbilityRepository extends JpaRepository<AbilityEntity, Long> {
}
