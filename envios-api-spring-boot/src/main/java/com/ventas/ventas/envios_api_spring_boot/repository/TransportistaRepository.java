package com.ventas.ventas.envios_api_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.ventas.ventas.envios_api_spring_boot.models.Transportista;

public interface TransportistaRepository extends JpaRepository<Transportista, Integer> {

}
