package it.plansoft.gestionemagazzino.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.plansoft.gestionemagazzino.models.Ordine;

public interface OrdiniRepository extends JpaRepository<Ordine,Integer> {
	

}
