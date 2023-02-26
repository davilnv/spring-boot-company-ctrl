package br.com.davilnv.companyctrl.models.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER")
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;
    @Column(name = "LASTNAME", nullable = false)
    private String lastName;
    @Email
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

}
