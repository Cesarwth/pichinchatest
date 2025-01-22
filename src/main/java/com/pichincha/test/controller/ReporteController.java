package com.pichincha.test.controller;

import com.pichincha.test.dto.request.ReporteRequestDTO;
import com.pichincha.test.dto.response.ReporteEstadoCuentaDTO;
import com.pichincha.test.dto.response.ReporteResposeDTO;
import com.pichincha.test.service.ReporteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/reportes")
    public ResponseEntity<ReporteEstadoCuentaDTO> obtenerReporteEstadoCuenta(
            @Valid @RequestBody ReporteRequestDTO reporteRequestDTO) {
        ReporteEstadoCuentaDTO reporte = reporteService.generarReporte(reporteRequestDTO);
        return ResponseEntity.ok(reporte);
    }
}
