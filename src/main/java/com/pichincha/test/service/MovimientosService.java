package com.pichincha.test.service;

import com.pichincha.test.constants.MensajeError;
import com.pichincha.test.constants.TipoMovimiento;
import com.pichincha.test.dto.request.MovimientoRequestDTO;
import com.pichincha.test.dto.response.MovimientoResponseDTO;
import com.pichincha.test.exception.CuentaException;
import com.pichincha.test.exception.SaldoException;
import com.pichincha.test.model.Cuenta;
import com.pichincha.test.model.Movimientos;
import com.pichincha.test.repository.CuentaRepository;
import com.pichincha.test.repository.MovimientosRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class MovimientosService {

    private final MovimientosRepository movimientosRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientosService(MovimientosRepository movimientosRepository, CuentaRepository cuentaRepository) {
        this.movimientosRepository = movimientosRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public Cuenta obtenerCuentaPorId(Long cuentaId) {
        log.debug("Obteniendo cuenta por ID: Cuenta ID={}", cuentaId);
        return cuentaRepository.findById(cuentaId)
                .orElseThrow(() -> {
                    log.error("Cuenta no encontrada: Cuenta ID={}", cuentaId);
                    return new CuentaException(MensajeError.CUENTA_NO_EXISTE.getMensaje());
                });
    }

    @Transactional
    public MovimientoResponseDTO registrarMovimiento(MovimientoRequestDTO movimientoRequestDTO) {
        log.info("Iniciando registro de movimiento: Cuenta ID={}, Valor={}", movimientoRequestDTO.getCuentaId(), movimientoRequestDTO.getValor());
        Double valor = movimientoRequestDTO.getValor();
        Cuenta cuenta = validarCuentaExistente(movimientoRequestDTO);
        validarSaldoDisponible(valor, cuenta.getSaldoInicial());
        Double saldoNuevo = actualizarSaldoCuenta(cuenta, valor);
        Movimientos movimiento = crearMovimiento(valor, saldoNuevo, cuenta);
        return mapToResponse(movimiento);
    }

    private Cuenta validarCuentaExistente(MovimientoRequestDTO movimientoRequestDTO) {
        log.debug("Validando existencia de cuenta: Cuenta ID={}", movimientoRequestDTO.getCuentaId());
        return cuentaRepository.findById(movimientoRequestDTO.getCuentaId())
                .orElseThrow(() -> {
                    log.error("Cuenta no encontrada: Cuenta ID={}", movimientoRequestDTO.getCuentaId());
                    return new CuentaException(MensajeError.CUENTA_NO_EXISTE.getMensaje());
                });
    }

    private void validarSaldoDisponible(Double valor, Double saldo) {
        log.debug("Validando saldo disponible: Valor={}, Saldo Actual={}", valor, saldo);
        if (esRetiroSinSaldo(valor, saldo)) {
            log.error("Saldo insuficiente: Valor={}, Saldo Actual={}", valor, saldo);
            throw new SaldoException(MensajeError.SALDO_NO_DISPONIBLE.getMensaje());
        }
    }

    private boolean esRetiroSinSaldo(Double valor, Double saldo) {
        return valor < 0 && saldo + valor < 0;
    }

    private Double actualizarSaldoCuenta(Cuenta cuenta, Double valor) {
        double saldoDisponible = calcularSaldoNuevo(cuenta.getSaldoInicial(), valor);
        log.info("Actualizando saldo de cuenta: Cuenta ID={}, Saldo Anterior={}, Valor={}, Saldo Nuevo={}",
                cuenta.getId(), cuenta.getSaldoInicial(), valor, saldoDisponible);
        cuenta.setSaldoInicial(saldoDisponible);
        cuentaRepository.save(cuenta);
        return saldoDisponible;
    }

    private Double calcularSaldoNuevo(Double saldoActual, Double valor) {
        return saldoActual + valor;
    }

    private Movimientos crearMovimiento(Double valor, Double saldoNuevo, Cuenta cuenta) {
        TipoMovimiento tipoMovimiento = valor > 0 ? TipoMovimiento.DEPOSITO : TipoMovimiento.RETIRO;
        Movimientos movimiento = new Movimientos();
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setTipoMovimiento(tipoMovimiento.name());
        movimiento.setValor(valor);
        movimiento.setSaldo(saldoNuevo);
        movimiento.setCuenta(cuenta);

        return movimientosRepository.save(movimiento);
    }

    private MovimientoResponseDTO mapToResponse(Movimientos movimiento) {
        MovimientoResponseDTO responseDTO = new MovimientoResponseDTO();
        responseDTO.setFecha(movimiento.getFecha());
        responseDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
        responseDTO.setValor(movimiento.getValor());
        responseDTO.setSaldo(movimiento.getSaldo());
        return responseDTO;
    }
}
