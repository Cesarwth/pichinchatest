package com.pichincha.test.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovimientoRequestDTO {

    @NotNull(message = "El ID de la cuenta no puede estar vacío")
    private Long cuentaId;

    @NotNull(message = "El valor del movimiento no puede estar vacío")
    private Double valor;

}
