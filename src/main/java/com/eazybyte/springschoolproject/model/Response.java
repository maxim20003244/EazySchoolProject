package com.eazybyte.springschoolproject.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String statusCode;
    private String statusMsg;
}
