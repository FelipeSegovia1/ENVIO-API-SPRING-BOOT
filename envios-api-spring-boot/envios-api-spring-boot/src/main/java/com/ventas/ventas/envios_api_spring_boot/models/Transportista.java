package com.ventas.ventas.envios_api_spring_boot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transportista {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransportista;

    private String nombre;
}
