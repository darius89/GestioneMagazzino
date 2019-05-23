package it.plansoft.gestionemagazzino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.plansoft.gestionemagazzino.models.Cliente;

public interface ClientiRepository extends JpaRepository<Cliente,Integer> {

}
