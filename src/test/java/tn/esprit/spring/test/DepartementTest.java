package tn.esprit.spring.test;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.DepartementServiceImpl;
import tn.esprit.spring.services.EntrepriseServiceImpl;


@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartementTest {
	

@Autowired
private  EntrepriseServiceImpl entrepriseservice ;
@Autowired
private  DepartementServiceImpl departementservice ;
@Autowired
private DepartementRepository departmentRep;
private static final Logger l = LogManager.getLogger(DepartementTest.class);

	 @Test
	  public  void Add_Departement() 
	 {
	        Departement dep = new Departement("dev");
	        departementservice.ajouterDepartement(dep);
	        departementservice.deleteDepartementById(dep.getId());
	        l.info("your departement added avec succes");
	        
	  }
	 
	 @Test
	  public void affecterDepartementAEntreprise()
	  {
	     Departement dep = new Departement("dev");
	     departementservice.ajouterDepartement(dep);
	     Entreprise e = new Entreprise("entreprise test","Consulting");
	     entrepriseservice.ajouterEntreprise(e);
	     departementservice.affecterDepartementAEntreprise(dep.getId(), e.getId());
	     l.info("your departement with id ",dep.getId()," added avec succes to this entreprise ",e.getId());
	     entrepriseservice.deleteEntrepriseById(e.getId());
	     departementservice.deleteDepartementById(dep.getId());
	  }
	 @Test
	  public void getAllDepartementsNamesByEntreprise()
	  {
	     Departement dep = new Departement("dev");
	     Departement dep1 = new Departement("dev1");
	     Departement dep2 = new Departement("dev2");
	     departementservice.ajouterDepartement(dep);
	     departementservice.ajouterDepartement(dep1);
	     departementservice.ajouterDepartement(dep2);
	     Entreprise e = new Entreprise("entreprise test","Consulting");
	     entrepriseservice.ajouterEntreprise(e);
	     departementservice.affecterDepartementAEntreprise(dep.getId(), e.getId());
	     departementservice.affecterDepartementAEntreprise(dep1.getId(), e.getId());
	     departementservice.affecterDepartementAEntreprise(dep2.getId(), e.getId());
	     List<String> lr= departementservice.getAllDepartementsNamesByEntreprise(e.getId());
	     if(lr.size() ==3)
	     {
	     l.info("list de departement name ",lr);
	     }else
	     {
	     l.info("test failed ",lr);
	     }
	     entrepriseservice.deleteEntrepriseById(e.getId());
	  }
	 
	 
	


}
