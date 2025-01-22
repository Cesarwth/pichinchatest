package com.pichincha.test.service;

import com.pichincha.test.model.Cuenta;
import com.pichincha.test.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta update(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public void delete(Long id) {
        cuentaRepository.deleteById(id);
    }

    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> findById(Long id) {
        return cuentaRepository.findById(id);
    }
}
