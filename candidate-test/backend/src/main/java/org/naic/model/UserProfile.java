/*
 * Copyright (c) 2017. National Association of Insurance Commissioners.
 */

package org.naic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * User's basic profile
 */
@Getter
@Setter
@ToString(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserProfile implements Serializable {

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
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Transient
    @JsonIgnore
    public String getPasswordHash() {
        return new BCryptPasswordEncoder().encode(this.password);
    }

    @Column
    @NotNull
    @Size(max = 50)
    private String firstName;   // required

    @Column
    @Size(max = 1)
    private String middleInit;

    @Column
    @NotNull
    @Size(max = 50)
    private String lastName;    // required

    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", message = "Invalid email")
    @Size(max = 100)
    private String email;       // required

    @Column
    @Size(max = 100)
    private String address1;

    @Column
    @Size(max = 100)
    private String address2;

    @Column
    @Size(max = 100)
    private String city;

    @Column
    @Size(max = 2)
    private String state;

    @Column
    @Pattern(regexp = "^\\d{5}(?:[-\\s]\\d{4})?$", message = "Invalid zip code")
    @Size(max = 10)
    private String zip;

    @Column
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    @Size(max = 10)
    private String phone;       // form (###) ###-#### but save a numeric

}
