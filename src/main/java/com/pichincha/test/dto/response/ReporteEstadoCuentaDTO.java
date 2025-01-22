package com.pichincha.test.dto.response;

import com.pichincha.test.dto.CuentaReporteDTO;
import lombok.Data;

import java.util.List;

@Data
public class ReporteEstadoCuentaDTO {

    private Long clienteId;
    private List<CuentaReporteDTO> cuentas;

    public ReporteEstadoCuentaDTO(Long clienteId, List<CuentaReporteDTO> cuentas) {
        this.clienteId = clienteId;
        this.cuentas = cuentas;
    }
}
