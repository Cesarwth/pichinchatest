package com.pichincha.test.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReporteRequestDTO {

    @NotNull(message = "El ID del cliente no puede ser nulo.")
    private Long clienteId;

    @NotBlank(message = "La fecha de inicio no puede estar vacía.")
    private String fechaInicio;

    @NotBlank(message = "La fecha de fin no puede estar vacía.")
    private String fechaFin;

}
