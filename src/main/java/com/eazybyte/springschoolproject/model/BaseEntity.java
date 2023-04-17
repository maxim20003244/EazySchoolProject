package com.eazybyte.springschoolproject.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity {
    private LocalDateTime createAt;
    private String createBY;
    private LocalDateTime updateAt;
    private String updateBy;
}
