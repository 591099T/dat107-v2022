package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.time.LocalDate;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;
import no.hvl.dat107.dao.ProsjektDAO;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Prosjekt;

public class Main {
	private static AnsattDAO ansattdao = new AnsattDAO();
	private static ProsjektDAO prosjektdao = new ProsjektDAO();
	private static AvdelingDAO avdelingdao = new AvdelingDAO();

	public static void main(String[] args) {

		ansattdao.leggTilNyAnsatt(new Ansatt("per", "Perkor", "Janus", LocalDate.of(2022, Month.MARCH, 1),
				"IT konsulent", new BigDecimal("23020"), 1));
		System.out.println("Oppretta bruker Perkor");
		
		Ansatt z1 = ansattdao.finnAnsattMedId(6);
		z1.skrivUt();
		System.out.println("Sjekker at Perkor ikkje har nokre prosjekter");
		
		z1.skrivUtMedProsjekter();
		Prosjekt p1 = prosjektdao.finnProsjektMedId(2);
		System.out.println("Legger Perkor til i eit prosjekt:");
		
		ansattdao.registrerProsjektdeltagelse(z1, p1);
		z1.skrivUtMedProsjekter();
		System.out.println(":  Ser at han ikkje har noko timar i prosjektet så han kan fjernest fra prosjektet");
		
		ansattdao.slettProsjektdeltagelse(z1, p1);
		//Ansatt z3 = ansattdao.finnAnsattMedBrukernavn("per");
		z1.skrivUtMedProsjekter();
		//ansattdao.sletteAnsatt(z3.getId());

		AnsattDAO ansattDAO = new AnsattDAO();
		ProsjektDAO prosjektDAO = new ProsjektDAO();

		Ansatt a2 = ansattDAO.finnAnsattMedId(2);
		a2.skrivUtMedProsjekter();
		a2.skrivUt();

		Prosjekt p2 = prosjektDAO.finnProsjektMedId(2);
		p2.skrivUtMedAnsatte();

		Prosjekt p3 = prosjektDAO.finnProsjektMedId(1);
		p3.skrivUtMedAnsatte();

		ansattDAO.registrerProsjektdeltagelse(a2, p3);
		a2 = ansattDAO.finnAnsattMedId(2);
		p3 = prosjektDAO.finnProsjektMedId(3);
		a2.skrivUtMedProsjekter();
		p3.skrivUtMedAnsatte();

		ansattDAO.slettProsjektdeltagelse(a2, p3);
		a2 = ansattDAO.finnAnsattMedId(2);
		p3 = prosjektDAO.finnProsjektMedId(3);
		a2.skrivUtMedProsjekter();
		p3.skrivUtMedAnsatte();

		a2.skrivUt();

	}
//    private static void skrivUt(String tekst) {
//		List<Ansatt> personer = AnsattDAO.finnAlleAnsatte();
//		System.out.println("\n--- "+ tekst +" ---");
//		personer.forEach(System.out::println);		
//	}

	private static void skrivUte(String tekst) {
		List<Ansatt> personer = ansattdao.finnAlleAnsatte();
		System.out.println("\n--- " + tekst + " ---");
		personer.forEach(System.out::println);
	}

}
