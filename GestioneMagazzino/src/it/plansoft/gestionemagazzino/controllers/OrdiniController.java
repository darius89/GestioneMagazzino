package it.plansoft.gestionemagazzino.controllers;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.plansoft.gestionemagazzino.enums.StatoOrdine;
import it.plansoft.gestionemagazzino.exceptions.ResourceNotFoundException;
import it.plansoft.gestionemagazzino.models.Articolo;
import it.plansoft.gestionemagazzino.models.Cliente;
import it.plansoft.gestionemagazzino.models.Ordine;
import it.plansoft.gestionemagazzino.repositories.ArticoliRepository;
import it.plansoft.gestionemagazzino.repositories.OrdiniRepository;

@RestController
@RequestMapping("/ordini")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class OrdiniController {

	@Autowired
	private OrdiniRepository ordiniRepository;

	@GetMapping("/list")
	public List<Ordine> getOrdini() {
		return ordiniRepository.findAll();
	}

	@PostMapping("/ordine")
	public Ordine save(@Valid @RequestBody Ordine ordine) throws Exception {

		ordine.setPrezzoTotale(ordine.getPrezzoTotale());
		if (ordine.getStato().equals(StatoOrdine.SPEDITO)) {
			ordine.esegui();
		}

		
		return ordiniRepository.save(ordine);

	}

	@PutMapping("/ordine/{id}")
	public Ordine update(@PathVariable(value = "id") Integer id, @Valid @RequestBody Ordine dettagliOrdine)
			throws Exception {

		Ordine ordine = ordiniRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ordine", "id", id));
		ordine.setStato(dettagliOrdine.getStato());
		ordine.setArticolo(dettagliOrdine.getArticolo());
        ordine.setQuantita(dettagliOrdine.getQuantita());
		ordine.setPrezzoTotale(ordine.getPrezzoTotale());

		if (ordine.getStato().equals(StatoOrdine.SPEDITO)) {
			ordine.esegui();
		}

		return ordiniRepository.save(ordine);

	}

	@DeleteMapping("/ordine/{id}")
	public Ordine delete(@PathVariable(value = "id") Integer id) {

		Ordine ordine = ordiniRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Ordine", "id", id));
		ordiniRepository.delete(ordine);
		return ordine;

	}

}
