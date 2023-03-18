package sptech.school.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.backend.entities.Address;

import java.util.List;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findAllByCity(String city);
    List<Address> findAllByDistrict(String district);
}
