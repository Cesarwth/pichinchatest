package com.pichincha.test.dto;

import lombok.Data;

import java.util.List;

@Data
public class CuentaReporteDTO {

    private String numeroCuenta;
    private Double saldoInicial;
    private List<MovimientoReporteDTO> movimientos;

    public CuentaReporteDTO(String numeroCuenta, Double saldoInicial, List<MovimientoReporteDTO> movimientos) {
        this.numeroCuenta = numeroCuenta;
        this.saldoInicial = saldoInicial;
        this.movimientos = movimientos;
    }
}
