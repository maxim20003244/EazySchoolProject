package com.eazybyte.springschoolproject.model;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
@Getter
public class Holiday  {

    private final String day;
    private final String reason;
    private final Type type;

    public enum Type{
        FESTIVAL,FEDERAL
    }

    public Holiday(String day, String reason, Type type) {
        this.day = day;
        this.reason = reason;
        this.type = type;
    }
}
