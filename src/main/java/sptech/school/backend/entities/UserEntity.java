package sptech.school.backend.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Column(length = 4)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String surname;

    private boolean isSigned = false;

    @Column(length = 50)
    private String email;

    @Column(unique = true, length = 50)
    private String phone;

    @Column(length = 50)
    private String password;
}
