package it.prova.myebay.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "utente")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "creditoResiduo")
	private Integer creditoResiduo;
	
	@Column(name = "dateCreated")
	private LocalDate dataCreated;
	
	@Enumerated(EnumType.STRING)
	private StatoUtente stato;
	

	@ManyToMany
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private Set<Ruolo> ruoli = new HashSet<>(0);


	public Utente() {
		
	}	
	
	public Utente(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Utente(Long id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}


	public Utente(String username, String password, String nome, String cognome, Integer creditoResiduo,
			LocalDate dataCreated) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.creditoResiduo = creditoResiduo;
		this.dataCreated = dataCreated;
	}


	public Utente(String username, String password, String nome, String cognome, Integer creditoResiduo,
			LocalDate dataCreated, StatoUtente stato, Set<Ruolo> ruoli) {
		super();
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.creditoResiduo = creditoResiduo;
		this.dataCreated = dataCreated;
		this.stato = stato;
		this.ruoli = ruoli;
	}

	
	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public Integer getCreditoResiduo() {
		return creditoResiduo;
	}


	public void setCreditoResiduo(Integer creditoResiduo) {
		this.creditoResiduo = creditoResiduo;
	}


	public LocalDate getDataCreated() {
		return dataCreated;
	}


	public void setDataCreated(LocalDate dataCreated) {
		this.dataCreated = dataCreated;
	}


	public StatoUtente getStato() {
		return stato;
	}


	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}


	public Set<Ruolo> getRuoli() {
		return ruoli;
	}


	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

}




