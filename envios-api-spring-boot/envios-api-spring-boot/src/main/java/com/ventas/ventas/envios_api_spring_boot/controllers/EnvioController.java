package com.ventas.ventas.envios_api_spring_boot.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.ventas.envios_api_spring_boot.dto.EnvioDTO;
import com.ventas.ventas.envios_api_spring_boot.services.EnvioService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; 
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService service;

    // Crear Envío con enlace HATEOAS
    @PostMapping
    public ResponseEntity<EnvioDTO> crearEnvio(@RequestBody EnvioDTO dto) {
        EnvioDTO nuevoEnvio = service.crear(dto);
        nuevoEnvio.add(linkTo(methodOn(EnvioController.class).obtenerHATEOAS(nuevoEnvio.getIdEnvio())).withRel("detalle-envio"));
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEnvio);
    }


    // Obtener un envío con HATEOAS
    @GetMapping("/hateoas/{idEnvio}")
    public ResponseEntity<EnvioDTO> obtenerHATEOAS(@PathVariable Integer idEnvio) {
        Optional<EnvioDTO> opt = service.obtenerPorId(idEnvio);
        if (opt.isPresent()) {
            EnvioDTO dto = opt.get();
            dto.add(linkTo(methodOn(EnvioController.class).obtenerHATEOAS(idEnvio)).withSelfRel());
            dto.add(linkTo(methodOn(EnvioController.class).listarConHATEOAS()).withRel("todos"));
            dto.add(linkTo(methodOn(EnvioController.class).eliminar(idEnvio)).withRel("eliminar"));
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Listar todos los envíos con HATEOAS
    @GetMapping("/hateoas")
    public ResponseEntity<List<EnvioDTO>> listarConHATEOAS() {
        List<EnvioDTO> lista = service.listar();
        for (EnvioDTO dto : lista) {
            dto.add(linkTo(methodOn(EnvioController.class).obtenerHATEOAS(dto.getIdEnvio())).withSelfRel());
        }
        return ResponseEntity.ok(lista);
    }

    // Actualizar estado con HATEOAS
    @PutMapping("/hateoas/{id}/estado")
    public ResponseEntity<EnvioDTO> actualizarEstado(@PathVariable Integer id, @RequestBody EnvioDTO dto) {
        Optional<EnvioDTO> envioOpt = service.actualizarEstado(id, dto.getEstadoEnvio());
        if (envioOpt.isPresent()) {
            EnvioDTO actualizado = envioOpt.get();
            actualizado.add(linkTo(methodOn(EnvioController.class).actualizarEstado(id, dto)).withSelfRel());
            actualizado.add(linkTo(methodOn(EnvioController.class).obtenerHATEOAS(id)).withRel("detalle"));
            actualizado.add(linkTo(methodOn(EnvioController.class).eliminar(id)).withRel("eliminar"));
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        return service.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}