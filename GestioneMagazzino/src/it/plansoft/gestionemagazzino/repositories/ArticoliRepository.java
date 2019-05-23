package it.plansoft.gestionemagazzino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.plansoft.gestionemagazzino.enums.TipoBottiglia;
import it.plansoft.gestionemagazzino.models.Articolo;

public interface ArticoliRepository extends JpaRepository<Articolo,Integer> {
	
	@Query("Select a from Articolo a where a.nomeArticolo = :nome and a.tipoBottiglia = :tipo")
	Articolo findByDetails(@Param("nome") String nome, @Param("tipo") TipoBottiglia tipo);

}
