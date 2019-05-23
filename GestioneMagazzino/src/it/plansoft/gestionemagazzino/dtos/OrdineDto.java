package it.plansoft.gestionemagazzino.dtos;

import java.util.Date;

import it.plansoft.gestionemagazzino.enums.StatoOrdine;
import it.plansoft.gestionemagazzino.enums.TipoBottiglia;

public class OrdineDto {

	public Integer numeroOrdine;
	public Integer cliente;
	public String date;
	public Integer quantita;
	public float prezzoTotale;
	private ArticoloDto articolo;
	private StatoOrdine stato;

	public Integer getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(Integer numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public ArticoloDto getArticolo() {
		return articolo;
	}

	public void setArticolo(ArticoloDto articolo) {
		this.articolo = articolo;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public float getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(float prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	
	public void setStato(StatoOrdine stato) {
		this.stato = stato;
	}
	
	public StatoOrdine getStato() {
		return stato;
	}

}
