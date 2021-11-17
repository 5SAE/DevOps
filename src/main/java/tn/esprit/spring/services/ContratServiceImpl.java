package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.ContratTest;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;

@Service
public class ContratServiceImpl implements IContratService {


	@Autowired
	ContratRepository contratRepository;
	private static final Logger l = LogManager.getLogger(ContratServiceImpl.class);
	@Override
	public List<Contrat> getAllContrats() {
		l.info("entring to test getAllContrats");
		return (List<Contrat>) contratRepository.findAll();
	}
	@Override
	public int ajouterContrat(Contrat contrat) {
		contratRepository.save(contrat);
		l.info("test add contrat success");
		 return contrat.getReference();
	}
	@Override
	public void deleteContratById(int contratId) {
		if(contratRepository.existsById(contratId)) {
			Contrat contratManagedEntity = contratRepository.findById(contratId).orElseThrow(NullPointerException::new);
			contratRepository.delete(contratManagedEntity);
		}
	}

	@Override
	public Contrat getById(int id) {
		return contratRepository.findById(id).orElse(null);
	}

	@Override
	public long nombreDeContrats() {
		return contratRepository.count();
	}

	@Override
	public Contrat findContratById(int id) {
			return contratRepository.findById(id).orElseThrow(NullPointerException::new);
	}


}
