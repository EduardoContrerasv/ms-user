package cl.duoc.ms_user.model;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime registerDate;

    @Column(name = "account_status", nullable = false)
    private String accountStatus = "ACTIVE";

    @Column(name = "accoount_level", nullable = false)
    private int accountLevel = 1;

    @Column(name = "gold", nullable = false)
    private int gold = 0;
}
