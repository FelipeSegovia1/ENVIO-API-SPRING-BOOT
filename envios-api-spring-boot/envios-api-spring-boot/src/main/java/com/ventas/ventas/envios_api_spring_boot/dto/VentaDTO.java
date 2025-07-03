package com.ventas.ventas.envios_api_spring_boot.dto;

import java.time.LocalDate;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private Integer idVenta;
    private Integer idCliente;
    private Integer idVendedor;
    private LocalDate fechaVenta;

    
}
