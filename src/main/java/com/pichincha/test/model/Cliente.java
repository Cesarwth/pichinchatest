package com.pichincha.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "clientes", uniqueConstraints = @UniqueConstraint(columnNames = "identificacion"))
public class Cliente extends Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long clienteId;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private Boolean estado;

    public Cliente(String nombre, String genero, Integer edad, String identificacion,
                   String direccion, String telefono, String contrasena, Boolean estado) {
        super(nombre, genero, edad, identificacion, direccion, telefono);
        this.contrasena = contrasena;
        this.estado = estado;
    }
}
