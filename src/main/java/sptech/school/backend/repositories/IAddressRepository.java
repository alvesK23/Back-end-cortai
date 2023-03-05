package sptech.school.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.backend.entities.AddressEntity;

@Repository
public interface IAddressRepository extends JpaRepository<AddressEntity, Long> {
}
