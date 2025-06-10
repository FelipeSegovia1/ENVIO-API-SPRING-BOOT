package com.ventas.ventas.envios_api_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventas.ventas.envios_api_spring_boot.models.Envio;



public interface EnvioRepository extends JpaRepository<Envio, Integer>{

}
