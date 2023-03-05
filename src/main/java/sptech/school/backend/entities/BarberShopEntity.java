package sptech.school.backend.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarberShopEntity {

    @Column(length = 4)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String company;

    @Column(length = 50)
    private String cnpj;

    private boolean isSigned = false;

    @Column(length = 50)
    private String phone;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String password;


    public BarberShopEntity(String company, String cnpj, String phone, String email) {

        this.company = company;
        this.cnpj = cnpj;
        this.phone = phone;
        this.email = email;
        this.password = null;
    }
}
