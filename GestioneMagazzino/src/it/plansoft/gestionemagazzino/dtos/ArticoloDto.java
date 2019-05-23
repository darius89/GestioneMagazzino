package it.plansoft.gestionemagazzino.dtos;

import it.plansoft.gestionemagazzino.enums.TipoBottiglia;

public class ArticoloDto {

	private String nomeArticolo;
	private float prezzo;
	private Integer disponibilita;
	private TipoBottiglia tipoBottiglia;

	public String getNomeArticolo() {
		return nomeArticolo;
	}

	public void setNomeArticolo(String nomeArticolo) {
		this.nomeArticolo = nomeArticolo;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(Integer disponibilita) {
		this.disponibilita = disponibilita;
	}

	public TipoBottiglia getTipoBottiglia() {
		return tipoBottiglia;
	}

	public void setTipoBottiglia(TipoBottiglia tipoBottiglia) {
		this.tipoBottiglia = tipoBottiglia;
	}

}
