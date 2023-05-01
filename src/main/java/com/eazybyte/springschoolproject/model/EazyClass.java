package com.eazybyte.springschoolproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "class")
public class EazyClass extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private int classId;

    @NotBlank(message = "Name must to be nut blank")
    @Size(min = 3, message = "Name must to be at least 3 characters long")
    private String  name;
    @OneToMany(mappedBy = "eazyClass",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,
    targetEntity = Person.class)
    private Set<Person> persons;
}
