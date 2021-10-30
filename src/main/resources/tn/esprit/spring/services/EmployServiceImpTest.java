package tn.esprit.spring.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import tn.esprit.spring.entities.Employe;

public class EmployServiceImpTest {
	private EmployServiceImp employServ;
	
	@Test
	public void testAjoutEmploye() {
		Employe empl = new Employe("houda", "bettayeb", "houda.bettayeb@esprit.tn", true, null);
		int emplId = employServ.ajouterEmploye(empl);
		assertThat(emplId).isNotNull();
		
	}
	
	
	@Test
	public void testEmail () {
		Employe empl = new Employe("houda", "bettayeb", "houda.bettayeb@esprit.tn", true, null);
		int emplId = employServ.ajouterEmploye(empl);
		employServ.mettreAjourEmailByEmployeId("houda.betayeb@esprit.tn", emplId);
		List<Employe> listEmploy = employServ.getAllEmployes(); 
		Employe emplMiseAjour = listEmploy.get(0);
		assertThat(emplMiseAjour.getEmail()).isEqualTo("houda.bettayeb@esprit.tn");
	
	}
	
	
	@Test
	public void testGetAllEmployes () {
		Employe empl1 = new Employe("houda", "bettayeb", "houda.bettayeb@esprit.tn", true, null);
		Employe empl2 = new Employe("x", "y", "c.y@esprit.tn", true, null);

		int emplId1 = employServ.ajouterEmploye(empl1);
		int emplId2 = employServ.ajouterEmploye(empl2);

		employServ.mettreAjourEmailByEmployeId("houda.betayeb@esprit.tn", emplId);
		List<Employe> listEmploy = employServ.getAllEmployes(); 
		assertThat(listEmploy.size()).isEqualTo("2");
	
	}

}
