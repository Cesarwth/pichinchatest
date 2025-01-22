package com.pichincha.test.controller;

import com.pichincha.test.model.Cuenta;
import com.pichincha.test.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public List<Cuenta> getAll() {
        return cuentaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getById(@PathVariable Long id) {
        return cuentaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cuenta> createAccount(@RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.save(cuenta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> update(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        cuenta.setId(id);
        return ResponseEntity.ok(cuentaService.update(cuenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cuentaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
