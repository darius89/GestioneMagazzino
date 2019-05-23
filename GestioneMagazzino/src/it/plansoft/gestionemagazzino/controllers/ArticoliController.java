package it.plansoft.gestionemagazzino.controllers;

import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.plansoft.gestionemagazzino.exceptions.ResourceNotFoundException;
import it.plansoft.gestionemagazzino.models.Articolo;
import it.plansoft.gestionemagazzino.models.Ordine;
import it.plansoft.gestionemagazzino.repositories.ArticoliRepository;

@RestController
@RequestMapping("/articoli")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ArticoliController {

	@Autowired
	private ArticoliRepository articoliRepository;

	@GetMapping("/list")
	public List<Articolo> getArticoli() {

		return articoliRepository.findAll();
	}

	@PostMapping("/articolo")
	public Articolo save(@Valid @RequestBody Articolo articolo) {

		return articoliRepository.save(articolo);
	}

	@PutMapping("/articolo")
	public Articolo update( @Valid @RequestBody Articolo dettagliArticolo) {

		Articolo articolo = articoliRepository.findById(dettagliArticolo.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Articolo", "id", dettagliArticolo.getId()));
		articolo.setNomeArticolo(dettagliArticolo.getNomeArticolo());
		articolo.setTipoBottiglia(dettagliArticolo.getTipoBottiglia());
		articolo.setPrezzo(dettagliArticolo.getPrezzo());
		articolo.setDisponibilita(dettagliArticolo.getDisponibilita());
		return articoliRepository.save(articolo);
	}

	@DeleteMapping("/articolo/{id}")
	public Articolo delete(@PathVariable(value = "id") Integer id) {

		Articolo articolo = articoliRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Articolo", "id", id));
		articoliRepository.delete(articolo);
		return articolo;
	}

	@PutMapping("/carica")
	public Articolo caricaArticolo(@RequestParam Integer id, @RequestParam Integer quantita) throws Exception {
		if (quantita < 0)
			throw new Exception("Movimento entrata: numero di pezzi negativo");
		Articolo articolo = articoliRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Articolo", "id", id));

		articolo.carica(quantita);
		return articoliRepository.save(articolo);

	}

	@PutMapping("/scarica")
	public Articolo scaricaArticolo(@RequestParam Integer id, @RequestParam Integer quantita) throws Exception {
		if (quantita < 0)
			throw new Exception("Movimento entrata: numero di pezzi negativo");
		Articolo articolo = articoliRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Articolo", "id", id));

		if (!articolo.scarica(quantita))
			throw new Exception("Movimento uscita: numero di pezzi eccessivo");
		return articoliRepository.save(articolo);

	}

}
