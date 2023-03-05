package sptech.school.backend.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressEntity {

    @Column(length = 4)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String zip;

    @Column(length = 5)
    private String number;

    @Column(length = 50)
    private String street;

    @Column(length = 50)
    private String district;

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String state;

    @ManyToOne()
    @JoinColumn(name = "FK_bshop")
    private BarberShopEntity barberShop;



    public AddressEntity(String state, String city, String district, String street, String zip, Long id) {
            this.state = state;
            this.city = city;
            this.district = district;
            this.street = street;
            this.zip = zip;
            this.number = null;

    }

}
