package com.eazybyte.springschoolproject.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class Contact {
    @NotBlank(message = "Name must no to be blank")
    @Size(min = 3, message = "Name must to be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must no to be blank")
@Pattern(regexp="(^$|[0-9]{11})",message = "Mobile number must be 11 digits")
    private String mobileNum;
    @NotBlank(message = "Email must no to be blank")
    @Email(message = "Please provide e valid email address")
    private String email;
    @NotBlank(message = "Subject must no to be blank")
    @Size(min = 3, message = "Subject must to be at least 3 characters long")
    private String subject;
    @NotBlank(message = "Message must no to be blank")
    @Size(min = 5,message = "Message must to be at least 5 characters long")
    private String message;
}
