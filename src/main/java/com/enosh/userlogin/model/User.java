package com.enosh.userlogin.model;

import com.enosh.userlogin.controllers.ControllerUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Length(min = 2, max = 30)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Length(min = 2, max = 30)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Length(min = 5, max = 30)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Length(min = 5)
    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    private Address address;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
