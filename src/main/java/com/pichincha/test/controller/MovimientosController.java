package com.pichincha.test.controller;

import com.pichincha.test.dto.request.MovimientoRequestDTO;
import com.pichincha.test.dto.response.MovimientoResponseDTO;
import com.pichincha.test.service.MovimientosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimientos")
public class MovimientosController {

    private final MovimientosService movimientosService;

    public MovimientosController(MovimientosService movimientosService) {
        this.movimientosService = movimientosService;
    }

    @PostMapping("/registrar-movimiento")
    public ResponseEntity<MovimientoResponseDTO> registrarMovimiento(@Valid @RequestBody MovimientoRequestDTO movimientoRequestDTO) {
        MovimientoResponseDTO movimiento = movimientosService.registrarMovimiento(movimientoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimiento);
    }
}
