package sptech.school.backend.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AbilityEntity {

    @Column(length = 4)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 10)
    private BigDecimal price;

    @Column(length = 15)
    private String duration;

    @ManyToOne()
    @JoinColumn(name = "FK_barber")
    private BarberEntity barber;
}
