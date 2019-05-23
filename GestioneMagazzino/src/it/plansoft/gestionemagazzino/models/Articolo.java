package it.plansoft.gestionemagazzino.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


import it.plansoft.gestionemagazzino.enums.TipoBottiglia;

@Entity
@Table(name = "articoli")
public class Articolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "nome_articolo")
	private String nomeArticolo;
	@Column(name = "tipo_bottiglia")
	@Enumerated(EnumType.STRING)
	private TipoBottiglia tipoBottiglia;
	@Column(name = "prezzo")
	private float prezzo;
	@Column(name = "disponibilita")
	private Integer disponibilita;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeArticolo() {
		return nomeArticolo;
	}

	public void setNomeArticolo(String nomeArticolo) {
		this.nomeArticolo = nomeArticolo;
	}

	public TipoBottiglia getTipoBottiglia() {
		return tipoBottiglia;
	}

	public void setTipoBottiglia(TipoBottiglia tipoBottiglia) {
		this.tipoBottiglia = tipoBottiglia;
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

	public boolean scarica(Integer quantita) {
		if ((quantita > 0) && (disponibilita >= quantita)) {
			disponibilita -= quantita;
			return true;
		} else
			return false;
	}

	public boolean carica(Integer quantita) {
		if (quantita > 0) {
			disponibilita += quantita;
			return true;
		} else
			return false;
	}

}
