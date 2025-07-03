package com.ventas.ventas.envios_api_spring_boot.dto;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import lombok.*;


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class EnvioDTO extends RepresentationModel<EnvioDTO>{
    private Integer idEnvio;
    private Integer idVenta;
    private String direccionEnvio;
    private String estadoEnvio;
    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;
}

