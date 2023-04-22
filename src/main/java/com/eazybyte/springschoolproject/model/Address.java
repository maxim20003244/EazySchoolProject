package com.eazybyte.springschoolproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.parameters.P;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy= "native")
    private int addressId;


    private String address1;


    private String address2;
    @NotBlank(message = "City must to be not blank")
    @Size(min = 5,message = "City must to be at least 5 characters long")
    private String city;
    @NotBlank(message = "State must to be not blank")
    @Size(min = 5,message = "State must to be at least 5 characters long")
    private  String state;
    @NotBlank(message = "Zip Code must to be not blank")
    @Pattern(regexp = "(^$|[0-9]{5})", message = "Zip Code must be 5 digits")
    private int zipCode;

}
