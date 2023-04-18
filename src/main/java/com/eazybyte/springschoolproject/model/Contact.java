package com.eazybyte.springschoolproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "id")
    private int contactId;

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

    private String status;
}