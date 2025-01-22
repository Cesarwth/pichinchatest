package com.pichincha.test.dto.request;

import lombok.Data;

@Data
public class ClienteRequestDTO {

    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private String contrasena;
    private Boolean estado;
}
