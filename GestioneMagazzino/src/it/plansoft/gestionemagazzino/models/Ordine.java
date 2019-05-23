package it.plansoft.gestionemagazzino.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import it.plansoft.gestionemagazzino.enums.StatoOrdine;

@Entity
@Table(name = "ordini")
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numero_ordine")
	private Integer numeroOrdine;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "cliente")
	private Cliente cliente;
	@Enumerated(EnumType.STRING)
	private StatoOrdine stato;
	@Column(name = "prezzo_totale")
	private float prezzoTotale;
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name = "id_articolo")
	private Articolo articolo;
	@Column(name = "quantita")
	private Integer quantita;
	@Column(name = "data")
	@Temporal(TemporalType.DATE)
	private Date data;

	public Integer getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(Integer numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public StatoOrdine getStato() {
		return stato;
	}

	public void setStato(StatoOrdine stato) {
		this.stato = stato;
	}

	public float getPrezzoTotale() {

		return articolo.getPrezzo() * quantita;

	}

	public void setPrezzoTotale(float prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public void esegui() {
		articolo.scarica(quantita);
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	
	public Integer getQuantita() {
		return quantita;
	}

}
