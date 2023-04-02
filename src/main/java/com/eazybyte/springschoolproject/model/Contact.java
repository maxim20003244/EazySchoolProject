package com.eazybyte.springschoolproject.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class Contact {
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
}
