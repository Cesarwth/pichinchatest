package com.pichincha.test.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimientoResponseDTO {

    private LocalDateTime fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
}
