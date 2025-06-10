package com.ventas.ventas.envios_api_spring_boot.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EnvioDTO {
    private Integer idEnvio;
    private Integer idVenta;
    private String direccionEnvio;
    private String estadoEnvio;
    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;
}

