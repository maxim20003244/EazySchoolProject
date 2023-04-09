package com.eazybyte.springschoolproject.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    private LocalDateTime createAt;
    private String createBY;
    private LocalDateTime updateAt;
    private String updateBy;
}
