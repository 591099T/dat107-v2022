package no.hvl.dat107.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

@Entity
@Table(schema = "obligatorisk")
public class Avdeling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String navn;

	@Column(name = "Sjef")
	private int sjef;
	
//	@OneToMany(mappedBy="avdeling")
//	List<Ansatt> ansatte;

	public Avdeling() {
	}

	public Avdeling(int id, String navn, int sjef) {
		this.id = id;
		this.setNavn(navn);
		this.sjef = sjef;
	}

	public int getId() {
		return id;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public int getSjef() {
		return sjef;
	}

	public void setSjef(int sjef) {
		this.sjef = sjef;
	}

}
