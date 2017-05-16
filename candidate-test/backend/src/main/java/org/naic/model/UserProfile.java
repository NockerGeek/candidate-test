/*
 * Copyright (c) 2017. National Association of Insurance Commissioners.
 */

package org.naic.model;

import lombok.*;

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
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile implements Serializable
{

    @NotNull
    private String firstName;   // required

    private String middleInit;

    @NotNull
    private String lastName;    // required

    @NotNull
    @Pattern(regexp="^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", message="Invalid email")
    private String email;       // required
    private String address1;
    private String address2;
    private String city;
    private String state;

    @Pattern(regexp="^\\d{5}(?:[-\\s]\\d{4})?$", message="Invalid zip code")
    private String zip;

    @Pattern(regexp="^\\d{10}$", message="Invalid phone number")
    private String phone;       // form (###) ###-#### but save a numeric

}
