package sptech.school.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.backend.entities.PhotoEntity;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {
}
