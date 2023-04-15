package com.eazybyte.springschoolproject.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Holiday extends BaseEntity {

    private  String day;
    private  String reason;
    private  Type type;

    public enum Type{
        FESTIVAL,FEDERAL
    }


}
