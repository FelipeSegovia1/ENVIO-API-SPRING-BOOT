package com.ventas.ventas.envios_api_spring_boot.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ventas")
@Data
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;

    @Column(name =  "id_cliente" )
    private Integer idCliente;

    @Column(name = "id_vendedor")
    private Integer idVendedor;


    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

}

    
