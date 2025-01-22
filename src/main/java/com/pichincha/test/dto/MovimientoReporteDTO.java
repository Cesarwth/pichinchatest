package com.pichincha.test.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimientoReporteDTO {

    private LocalDateTime fecha;
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;

    public MovimientoReporteDTO(LocalDateTime fecha, String tipoMovimiento, Double valor, Double saldo) {
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
    }
}
