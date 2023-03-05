package sptech.school.backend.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SchedulingEntity {

    @Column(length = 4)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_service")
    private AbilityEntity service;

    private LocalDateTime scheduledDate;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "FK_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "FK_bshop")
    private BarberShopEntity barberShop;

    @ManyToOne
    @JoinColumn(name = "FK_barber")
    private BarberEntity barber;
}
