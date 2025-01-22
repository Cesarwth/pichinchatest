package com.pichincha.test.controller.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pichincha.test.dto.request.MovimientoRequestDTO;
import com.pichincha.test.model.Cliente;
import com.pichincha.test.model.Cuenta;
import com.pichincha.test.repository.ClienteRepository;
import com.pichincha.test.repository.CuentaRepository;
import com.pichincha.test.repository.MovimientosRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovimientosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MovimientosRepository movimientosRepository;

    private Long cuentaId;

    @Before
    public void setup() {
        // Limpiar datos anteriores
        movimientosRepository.deleteAll();
        cuentaRepository.deleteAll();
        clienteRepository.deleteAll();

        // Crear cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Jose Lema");
        cliente.setGenero("Masculino");
        cliente.setEdad(35);
        cliente.setIdentificacion("1234567890");
        cliente.setDireccion("Otavalo sn y principal");
        cliente.setTelefono("098254785");
        cliente.setContrasena("1234");
        cliente.setEstado(true);

        Cliente clienteGuardado = clienteRepository.save(cliente);

        // Crear cuenta asociada al cliente
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("1234567890");
        cuenta.setTipoCuenta("Ahorros");
        cuenta.setSaldoInicial(1000.0);
        cuenta.setEstado(true);
        cuenta.setCliente(clienteGuardado);

        Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
        cuentaId = cuentaGuardada.getId();
    }

    @Test
    public void registrarMovimiento_Exito() throws Exception {
        MovimientoRequestDTO request = new MovimientoRequestDTO();
        request.setCuentaId(cuentaId);
        request.setValor(500.0);

        mockMvc.perform(post("/movimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.valor").value(500.0))
                .andExpect((ResultMatcher) jsonPath("$.saldo").value(1500.0));
    }

    @Test
    public void registrarMovimiento_SaldoInsuficiente() throws Exception {
        MovimientoRequestDTO request = new MovimientoRequestDTO();
        request.setCuentaId(1L);
        request.setValor(-1500.0);

        mockMvc.perform(post("/movimientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
