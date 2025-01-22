package com.pichincha.test.repository;

import com.pichincha.test.model.Cliente;
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
    public void testGuardarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Perez");
        cliente.setGenero("Masculino");
        cliente.setEdad(30);
        cliente.setIdentificacion("1234567890");
        cliente.setDireccion("Calle Ficticia 123");
        cliente.setTelefono("0999999999");
        cliente.setContrasena("password123");
        cliente.setEstado(true);

        Cliente clienteGuardado = clienteRepository.save(cliente);

        assertNotNull(clienteGuardado);
        assertNotNull(clienteGuardado.getClienteId());
        assertEquals("Juan Perez", clienteGuardado.getNombre());
    }
}
