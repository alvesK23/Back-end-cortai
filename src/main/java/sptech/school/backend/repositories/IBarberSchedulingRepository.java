package sptech.school.backend.repositories;

import sptech.school.backend.entities.AbilityEntity;
import sptech.school.backend.entities.BarberEntity;
import sptech.school.backend.entities.BarberShopEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IBarberSchedulingRepository {

    String getName();

    String getPhone();

    LocalDateTime getTime();

    AbilityEntity getDescription();
}
