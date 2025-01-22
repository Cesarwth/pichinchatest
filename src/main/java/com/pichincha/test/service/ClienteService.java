package com.pichincha.test.service;

import com.pichincha.test.dto.request.ClienteRequestDTO;
import com.pichincha.test.model.Cliente;
import com.pichincha.test.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente save(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente(
                clienteRequestDTO.getNombre(),
                clienteRequestDTO.getGenero(),
                clienteRequestDTO.getEdad(),
                clienteRequestDTO.getIdentificacion(),
                clienteRequestDTO.getDireccion(),
                clienteRequestDTO.getTelefono(),
                clienteRequestDTO.getContrasena(),
                clienteRequestDTO.getEstado()
        );
        // Guardar el cliente en la base de datos
        return clienteRepository.save(cliente);

    }

    public Cliente update(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long clienteId) {
        return clienteRepository.findById(clienteId);
    }

}
