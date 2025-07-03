package com.ventas.ventas.envios_api_spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.ventas.ventas.envios_api_spring_boot.models.Estado;
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

}
