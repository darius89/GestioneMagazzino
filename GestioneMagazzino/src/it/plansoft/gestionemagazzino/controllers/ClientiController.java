package it.plansoft.gestionemagazzino.controllers;

import java.lang.module.FindException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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

import it.plansoft.gestionemagazzino.DtoUtility;
import it.plansoft.gestionemagazzino.dtos.OrdineDto;
import it.plansoft.gestionemagazzino.enums.StatoOrdine;
import it.plansoft.gestionemagazzino.exceptions.ResourceNotFoundException;
import it.plansoft.gestionemagazzino.models.Articolo;
import it.plansoft.gestionemagazzino.models.Cliente;
import it.plansoft.gestionemagazzino.models.Ordine;
import it.plansoft.gestionemagazzino.repositories.ArticoliRepository;
import it.plansoft.gestionemagazzino.repositories.ClientiRepository;
import it.plansoft.gestionemagazzino.repositories.OrdiniRepository;

@RestController
@RequestMapping("/clienti")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ClientiController {

	@Autowired
	private ClientiRepository repository;
	@Autowired
	private ArticoliRepository articoliRepository;
	@Autowired
	private OrdiniRepository ordinirepository;
	@Autowired
	private DtoUtility dtoUtility;

	@GetMapping("/list")
	public List<Cliente> getClienti() {
		return repository.findAll();
	}

	@PostMapping("/cliente")
	public Cliente save(@Valid @RequestBody Cliente cliente) throws Exception {

//		for (Ordine o : cliente.getOrdini()) {
//			if (o.getArticolo().getDisponibilita() == 0 || o.getArticolo().getDisponibilita() < o.getQuantita())
//				throw new Exception(
//						"\"attenzione,la quantita richiesta per l'articolo \" + o.getArticolo().getNomeArticolo() + \" non e' disponibile");
//
//		}
		return repository.save(cliente);
	}

	@PutMapping("/cliente")
	public Cliente update(@Valid @RequestBody Cliente dettagliCliente) throws Exception {

		Cliente cliente = repository.findById(dettagliCliente.getId())
				.orElseThrow(() -> new ResourceNotFoundException("cliente", "id", dettagliCliente.getId()));
		cliente.setCf(dettagliCliente.getCf());
		cliente.setNome(dettagliCliente.getNome());
		cliente.setCognome(dettagliCliente.getCognome());
		cliente.setIndirizzo(dettagliCliente.getIndirizzo());
		cliente.setOrdini(dettagliCliente.getOrdini());
//
//		for (Ordine o : cliente.getOrdini()) {
//			if (o.getArticolo().getDisponibilita() == 0 || o.getArticolo().getDisponibilita() < o.getQuantita())
//				throw new Exception(
//						"attenzione,la quantita richiesta per l'articolo " + o.getArticolo().getNomeArticolo() + " non e' disponibile");
//
//		}
		return repository.save(cliente);

	}

	@DeleteMapping("/cliente/{id}")
	public Cliente delete(@PathVariable(value = "id") Integer id) {

		Cliente cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", id));
		repository.delete(cliente);
		return cliente;

	}

	@GetMapping("/{id}/ordini")
	public List<OrdineDto> getOrdini(@PathVariable(value = "id") Integer id,
			@RequestParam(name = "myDate") String myDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Cliente cliente = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", id));
		List<Ordine> lista = cliente.getOrdini().stream()
				.filter(ordine -> format.format(ordine.getData()).indexOf(myDate) != -1).collect(Collectors.toList());
		System.out.println(myDate);

		return lista.stream().map(ordine -> dtoUtility.entityToDto(ordine)).collect(Collectors.toList());
	}

	@PostMapping("/{id}/ordine")
	public Cliente aggiungiOrdine(@PathVariable Integer id, @RequestBody OrdineDto dto) throws Exception {

		Articolo art = articoliRepository.findByDetails(dto.getArticolo().getNomeArticolo(),dto.getArticolo().getTipoBottiglia());
		Cliente c = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", id));
		Ordine o = new Ordine();
		if (art.getDisponibilita() == 0 || art.getDisponibilita() < dto.getQuantita())
			throw new Exception(
					"attenzione,la quantita richiesta per l'articolo " + art.getNomeArticolo() + " non e' disponibile");
		art.scarica(dto.getQuantita());
		o.setArticolo(art);
		o.setCliente(c);
		o.setData(new Date());
		o.setQuantita(dto.getQuantita());
		o.setPrezzoTotale(o.getPrezzoTotale());
		o.setStato(StatoOrdine.PRESO_IN_CARICO);
		
		c.getOrdini().add(o);

		return repository.save(c);
	}

	@DeleteMapping("/{idCliente}/ordine/{numeroOrdine}")
	public Cliente cancellaOrdine(@PathVariable(value = "idCliente") Integer idCliente,
			@PathVariable(value = "numeroOrdine") Integer numeroOrdine) {

		Cliente c = repository.findById(idCliente)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", idCliente));
		for (Ordine o : c.getOrdini()) {
			if (o.getNumeroOrdine().compareTo(numeroOrdine) == 0) {

				c.getOrdini().remove(o);
				ordinirepository.delete(o);
				break;
			}
		}

		return repository.save(c);

	}

	@PutMapping("/{idCliente}/ordine")
	public Cliente modificaOrdine(@PathVariable Integer idCliente, @RequestBody OrdineDto ordine) throws Exception {
		Articolo art = articoliRepository.findByDetails(ordine.getArticolo().getNomeArticolo(),ordine.getArticolo().getTipoBottiglia());
		Cliente c = repository.findById(idCliente)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", idCliente));
		if (art.getDisponibilita() == 0 || art.getDisponibilita() < ordine.getQuantita())
			throw new Exception(
					"attenzione,la quantita richiesta per l'articolo " + art.getNomeArticolo() + " non e' disponibile");
		
		art.scarica(ordine.getQuantita());
		
		for (Ordine o : c.getOrdini()) {
			if (o.getNumeroOrdine().compareTo(ordine.getNumeroOrdine()) == 0) {

				
				o.setArticolo(art);
				o.setCliente(c);
				o.setData(new Date());
				o.setQuantita(ordine.getQuantita());
				o.setPrezzoTotale(o.getPrezzoTotale());
				
				break;
			}
		}
		return repository.save(c);

	}

}
