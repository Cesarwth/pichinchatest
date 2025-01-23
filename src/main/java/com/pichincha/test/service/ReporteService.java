package com.pichincha.test.service;

import com.pichincha.test.dto.CuentaReporteDTO;
import com.pichincha.test.dto.MovimientoReporteDTO;
import com.pichincha.test.dto.request.ReporteRequestDTO;
import com.pichincha.test.dto.response.ReporteEstadoCuentaDTO;
import com.pichincha.test.dto.response.ReporteResposeDTO;
import com.pichincha.test.model.Cuenta;
import com.pichincha.test.model.Movimientos;
import com.pichincha.test.repository.CuentaRepository;
import com.pichincha.test.repository.MovimientosRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private final CuentaRepository cuentaRepository;
    private final MovimientosRepository movimientosRepository;

    public ReporteService(CuentaRepository cuentaRepository, MovimientosRepository movimientosRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientosRepository = movimientosRepository;
    }

    public ReporteEstadoCuentaDTO generarReporte(ReporteRequestDTO reporteRequestDTO) {
        LocalDateTime inicio = LocalDateTime.parse(reporteRequestDTO.getFechaInicio(), DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime fin = LocalDateTime.parse(reporteRequestDTO.getFechaFin(), DateTimeFormatter.ISO_DATE_TIME);

        List<Cuenta> cuentas = cuentaRepository.findByClienteClienteId(reporteRequestDTO.getClienteId());

        List<CuentaReporteDTO> cuentasReporte = cuentas.stream().map(cuenta -> {
            List<Movimientos> movimientos = movimientosRepository.findByCuentaIdAndFechaBetween(
                    cuenta.getId(), inicio, fin);

            List<MovimientoReporteDTO> movimientosReporte = movimientos.stream().map(mov -> new MovimientoReporteDTO(
                            mov.getFecha(), mov.getTipoMovimiento(), mov.getValor(), mov.getSaldo()))
                    .collect(Collectors.toList());

            return new CuentaReporteDTO(cuenta.getNumeroCuenta(), cuenta.getSaldoInicial(), movimientosReporte);
        }).collect(Collectors.toList());

        return new ReporteEstadoCuentaDTO(reporteRequestDTO.getClienteId(), cuentasReporte);
    }
}
