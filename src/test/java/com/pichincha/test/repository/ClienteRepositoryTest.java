package com.pichincha.test.repository;

import com.pichincha.test.model.Cliente;
import com.pichincha.test.model.Cuenta;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testGuardarYBuscarCliente() {
        // Crear un nuevo Cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Pérez");
        cliente.setGenero("Masculino");
        cliente.setEdad(30);
        cliente.setIdentificacion("1987654301");
        cliente.setDireccion("Av. Siempre Viva 123");
        cliente.setTelefono("0987654321");
        cliente.setContrasena("password123");
        cliente.setEstado(true);

        Cliente clienteGuardado = clienteRepository.save(cliente);

        assertNotNull(String.valueOf(clienteGuardado.getClienteId()));

        Cliente clienteRecuperado = clienteRepository.findById(clienteGuardado.getClienteId()).orElse(null);

        assertNotNull(String.valueOf(clienteRecuperado));
    }
}
