package com.ventas.ventas.envios_api_spring_boot.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.ventas.envios_api_spring_boot.dto.EnvioDTO;
import com.ventas.ventas.envios_api_spring_boot.models.Envio;
import com.ventas.ventas.envios_api_spring_boot.models.Venta;
import com.ventas.ventas.envios_api_spring_boot.repository.EnvioRepository;
import com.ventas.ventas.envios_api_spring_boot.repository.VentaRepository;
@Service
public class EnvioService {

    @Autowired
    private EnvioRepository repository;
    @Autowired
    private VentaRepository ventaRepository;

    public EnvioDTO guardar(EnvioDTO dto){
        Envio envio = toEntity(dto);
        Envio saved = repository.save(envio);
        return toDTO(saved);
    }

    public List<EnvioDTO> listar(){
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EnvioDTO> obtenerPorId(Integer id){
        return repository.findById(id)
                .map(this::toDTO);
    }

    public Optional<EnvioDTO> actualizar(Integer id, EnvioDTO dto){
        return repository.findById(id).map(envio -> {
            // Relación Venta
            if(dto.getIdVenta() != null) {
                Venta venta = ventaRepository.findById(dto.getIdVenta())
                    .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
                envio.setVenta(venta);
            }
            envio.setDireccionEnvio(dto.getDireccionEnvio());
            envio.setEstadoEnvio(dto.getEstadoEnvio());
            envio.setFechaEnvio(dto.getFechaEnvio());
            envio.setFechaEntrega(dto.getFechaEntrega());
            return toDTO(repository.save(envio));
        });
    }

    public boolean eliminar(Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private EnvioDTO toDTO(Envio envio){
        EnvioDTO dto = new EnvioDTO();
        dto.setIdEnvio(envio.getIdEnvio());
        dto.setIdVenta(envio.getVenta() != null ? envio.getVenta().getIdVenta() : null);
        dto.setDireccionEnvio(envio.getDireccionEnvio());
        dto.setEstadoEnvio(envio.getEstadoEnvio());
        dto.setFechaEnvio(envio.getFechaEnvio());
        dto.setFechaEntrega(envio.getFechaEntrega());
        return dto;
    }

    private Envio toEntity(EnvioDTO dto){
        Envio envio = new Envio();
        envio.setIdEnvio(dto.getIdEnvio());
        // Relación Venta
        if(dto.getIdVenta() != null) {
            Venta venta = ventaRepository.findById(dto.getIdVenta())
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
            envio.setVenta(venta);
        }
        envio.setDireccionEnvio(dto.getDireccionEnvio());
        envio.setEstadoEnvio(dto.getEstadoEnvio());
        envio.setFechaEnvio(dto.getFechaEnvio());
        envio.setFechaEntrega(dto.getFechaEntrega());
        return envio;
    }
}
