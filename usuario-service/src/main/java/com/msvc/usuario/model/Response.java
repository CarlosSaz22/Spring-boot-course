package com.msvc.usuario.model;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    private String message;
    private boolean success;
    private HttpStatus status;
}
