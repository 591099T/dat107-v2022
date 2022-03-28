package no.hvl.dat107.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.eclipse.persistence.internal.jpa.parsing.TemporalLiteralNode.TemporalType;

@Entity
@Table(schema = "obligatorisk")
public class Ansatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String fornavn;
	private String etternavn;

	private String brukernavn;

	private String stilling;
	@Column(name = "maaneds_lonn")
	private BigDecimal lonn;

	@Column(name = "avdeling")
	private int avdeling;

	@Column(name = "Ansettelses_Dato")
	private LocalDate dato;

	@OneToMany(mappedBy = "ansatt")
	private List<Prosjektdeltagelse> deltagelser;
	
//	@ManyToOne
//	@JoinColumn(name="AvdelingId", referencedColumnName="Id")
//	private Avdeling avdeling;

//	@OneToOne
//	@JoinColumn(name="avdeling", referencedColumnName="avdeling")
//	private int avdeling;

//	@OneToMany(mappedBy="ansatt")
//	private List<Avdeling> avdeling1;

	public Ansatt() {
	}

	public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate dato, String stilling, BigDecimal lonn,
			int avdeling) {

		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.dato = dato;
		this.stilling = stilling;
		this.lonn = lonn;
		this.avdeling = avdeling;
	}

	public Ansatt(int id, String brukernavn, String fornavn, String etternavn, LocalDate dato, String stilling,
			BigDecimal lonn, int avdeling) {
		this.id = id;
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.dato = dato;
		this.stilling = stilling;
		this.lonn = lonn;
		this.avdeling = avdeling;
	}

	public void skrivUt(String innrykk) {
		System.out.printf("%sAnsatt nr %d: %s %s", innrykk, id, fornavn, etternavn);
	}

	public void skrivUtMedProsjekter() {
		System.out.println();
		skrivUt("");
		deltagelser.forEach(p -> p.skrivUt("\n   "));
	}

	public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
		deltagelser.add(prosjektdeltagelse);
	}

	public void fjernProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
		deltagelser.remove(prosjektdeltagelse);
	}

	public int getId() {
		return id;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public BigDecimal getLonn() {
		return lonn;
	}

	public void setLonn(BigDecimal lonn) {
		this.lonn = lonn;
	}

	public LocalDate getDate() {
		return dato;
	}

	public void setDate(LocalDate date) {
		this.dato = date;
	}

	public int getAvdeling() {
		return avdeling;
	}

	public void setAvdeling(int avdeling) {
		this.avdeling = avdeling; 
	}

	public List<Prosjektdeltagelse> getDeltagelser() {
		return deltagelser;
	}

	public void skrivUt() {
		System.out.println();
		System.out.println("Ansatt nr " + id + ": " + "Brukernavn: " + brukernavn + "\n" + ", Fornavn: " + fornavn
				+ ", Etternavn: " + etternavn + ", Ansettelsesdato: " + dato + ", Stilling: " + stilling
				+ ", Månedslønn: " + lonn + ", Avdeling: " + avdeling);

	}

}
