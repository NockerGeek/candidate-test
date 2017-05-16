/*
 * Copyright (c) 2017. National Association of Insurance Commissioners.
 */

package org.naic;

import lombok.*;

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

    private String firstName;   // required
    private String middleInit;
    private String lastName;    // required
    private String email;       // required
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;       // form (###) ###-#### but save a numeric

}
