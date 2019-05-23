package it.plansoft.gestionemagazzino;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import it.plansoft.gestionemagazzino.dtos.ArticoloDto;
import it.plansoft.gestionemagazzino.dtos.OrdineDto;
import it.plansoft.gestionemagazzino.models.Articolo;
import it.plansoft.gestionemagazzino.models.Ordine;

public class DtoUtility {

	public OrdineDto entityToDto(Ordine o) {
		OrdineDto dto = new OrdineDto();
		Articolo art = o.getArticolo();
		ArticoloDto artDto = new ArticoloDto();
		
		artDto.setNomeArticolo(art.getNomeArticolo());
		artDto.setDisponibilita(art.getDisponibilita());
		artDto.setPrezzo(art.getPrezzo());
		artDto.setTipoBottiglia(art.getTipoBottiglia());
		
		dto.setArticolo(artDto);
		dto.setCliente(o.getCliente().getId());
		dto.setPrezzoTotale(o.getPrezzoTotale());
		dto.setQuantita(o.getQuantita());
		dto.setNumeroOrdine(o.getNumeroOrdine());
		dto.setStato(o.getStato());
		
		return dto;
	}

}
