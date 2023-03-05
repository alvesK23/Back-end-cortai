package sptech.school.backend.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarberEntity {

    @Column(length = 4)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String surname;

    @Column(length = 50)
    private String email;

    private boolean isSigned = false;

    @ManyToOne()
    @JoinColumn(name = "FK_bshop")
    private BarberShopEntity barberShop;

    @Column(name = "phone", unique = true)
    private String phone;

    private String password;
}
