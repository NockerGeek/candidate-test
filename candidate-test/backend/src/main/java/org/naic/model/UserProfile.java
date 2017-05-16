/*
 * Copyright (c) 2017. National Association of Insurance Commissioners.
 */

package org.naic.model;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * User's basic profile
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserProfile implements Serializable
{

    public UserProfile(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @NotNull
    private String username;
    @Column
    @NotNull
    private String password;

    @Transient
    public String getPasswordHash() {
        return new BCryptPasswordEncoder().encode(this.password);
    }

    @Column
    @NotNull
    private String firstName;   // required

    @Column
    private String middleInit;

    @Column
    @NotNull
    private String lastName;    // required

    @Column
    @NotNull
    @Pattern(regexp="^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", message="Invalid email")
    private String email;       // required

    @Column
    private String address1;

    @Column
    private String address2;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    @Pattern(regexp="^\\d{5}(?:[-\\s]\\d{4})?$", message="Invalid zip code")
    private String zip;

    @Column
    @Pattern(regexp="^\\d{10}$", message="Invalid phone number")
    private String phone;       // form (###) ###-#### but save a numeric

}
