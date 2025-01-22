package com.pichincha.test.repository;

import com.pichincha.test.model.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {

    List<Movimientos> findByCuentaIdAndFechaBetween(Long cuentaId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
