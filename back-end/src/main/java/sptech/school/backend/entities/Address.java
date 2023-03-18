package sptech.school.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_address")
public class Address {

    @Id
    @GeneratedValue
    private Integer id;
    private String zip;
    private String number;
    private String street;
    private String district;
    private String city;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
