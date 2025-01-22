package com.pichincha.test.exception.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String mensaje;
    private int codigo;
    private LocalDateTime timestamp;

    public ErrorResponse(String mensaje, int codigo) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.timestamp = LocalDateTime.now();
    }
}
