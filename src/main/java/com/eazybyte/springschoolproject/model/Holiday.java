package com.eazybyte.springschoolproject.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    private  String day;
    private  String reason;

    @Enumerated(EnumType.STRING)
    private  Type type;

    public enum Type{
        FESTIVAL,FEDERAL
    }


}