package sptech.school.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.backend.entities.SchedulingEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ISchedulingRepository extends JpaRepository<SchedulingEntity, Long> {

    Optional<SchedulingEntity> findByTime(LocalDateTime time);

    SchedulingEntity findByBarberShopId(Long id);

    List<SchedulingEntity> findAllByBarberShopIdOrderByTimeDesc(Long id);

    List<SchedulingEntity> findAllByTime(LocalDateTime date);
}
