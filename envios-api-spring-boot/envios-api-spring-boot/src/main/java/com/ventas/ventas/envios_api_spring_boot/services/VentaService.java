package com.ventas.ventas.envios_api_spring_boot.services;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.ventas.envios_api_spring_boot.dto.VentaDTO;
import com.ventas.ventas.envios_api_spring_boot.models.Venta;
import com.ventas.ventas.envios_api_spring_boot.repository.VentaRepository;

@Service
public class VentaService {


    @Autowired
    private VentaRepository repository;



    public VentaDTO guardar(VentaDTO dto){
        Venta venta = toEntity(dto);
        Venta saved = repository.save(venta);
        return toDTO(saved);
    }

    public List<VentaDTO> listar(){
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<VentaDTO> obtenerPorId(Integer id){
        return repository.findById(id)
                .map(this::toDTO);
    }

    public Optional<VentaDTO> actualizar(Integer id, VentaDTO dto){
        return repository.findById(id).map(venta ->{
            venta.setIdCliente(dto.getIdCliente());
            venta.setIdVendedor(dto.getIdVendedor());
            venta.setFechaVenta(dto.getFechaVenta());
            return toDTO(repository.save(venta));
        });
    }

    public boolean eliminar(Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private VentaDTO toDTO(Venta venta){
        VentaDTO dto = new VentaDTO();
        dto.setIdVenta(venta.getIdVenta());
        dto.setIdCliente(venta.getIdCliente());
        dto.setIdVendedor(venta.getIdVendedor());
        dto.setFechaVenta(venta.getFechaVenta());

        return dto;
    }

    private Venta toEntity(VentaDTO dto){
        Venta venta = new Venta();
        venta.setIdVenta(dto.getIdVenta());
        venta.setIdCliente(dto.getIdCliente());
        venta.setIdVendedor(dto.getIdVendedor());
        venta.setFechaVenta(dto.getFechaVenta());

        return venta;
    }

    
    
}
