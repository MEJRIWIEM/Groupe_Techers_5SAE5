

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;

@ExtendWith(MockitoExtension.class)
class ReglementServiceImpTest {
	
	private static final Logger l = LogManager.getLogger(ReglementServiceImpTest.class);

	@InjectMocks
	private ReglementServiceImpl reglementServiceImpl;
	@Mock
	private ReglementRepository reglementRepository;
	
	

	@Test
	void addReglement() {
		l.debug("Test méthode addReglement");
		try {
		Reglement reglement = new Reglement();
		when(reglementRepository.save(reglement)).thenReturn(reglement);
		assertEquals(reglement, reglementServiceImpl.addReglement(reglement));
		l.info("Reglement Added succesfully");
		} catch (Exception e) {
			l.error("méthode addReglement ERROR: "+e);
		}
		}

	@Test
	void retrieveReglementByIdTest() {
		l.debug("Test méthode ReglementById");
		try {
		Reglement reglement = new Reglement();
		when(reglementRepository.findById(reglement.getIdReglement())).thenReturn(java.util.Optional.of(reglement));
		assertEquals(reglement.getIdReglement(),
				reglementServiceImpl.retrieveReglement(reglement.getIdReglement()).getIdReglement());
		l.info("Reglement retrieved by id succesfully");
		} catch (Exception e) {
			l.error("méthode ReglementById ERROR: "+e);
		}
	}

	@Test
	void retrieveAllReglementTest() {
		l.debug("Test méthode retrieveAllReglement");
		try {
		when(reglementRepository.findAll())
				.thenReturn(Stream.of(new Reglement(), new Reglement(), new Reglement()).collect(Collectors.toList()));
		assertEquals(3, reglementServiceImpl.retrieveAllReglements().size());
		l.info("List Reglement retrieved succesfully");
		} catch (Exception e) {
			l.error("méthode retrieveAllReglement ERROR: "+e);
		}
	}

	@Test
	void retrieveReglementByFactureTest() {
		l.debug("Test méthode retrieveReglementByFacture");
		try {
		Reglement reglement = new Reglement();
		Facture facture = new Facture();
		reglement.setFacture(facture);

		when(reglementRepository.retrieveReglementByFacture(reglement.getFacture().getIdFacture()))
				.thenReturn(Stream.of(new Reglement(), new Reglement(), new Reglement()).collect(Collectors.toList()));
		assertEquals(3, reglementServiceImpl.retrieveReglementByFacture(reglement.getFacture().getIdFacture()).size());
		l.info(" Reglement retrievedby facture succesfully");
		} catch (Exception e) {
			l.error("méthode retrieveReglementByFacture ERROR: "+e);
		}
	}
	
	@Test
	void getChiffreAffaireEntreDeuxDate() throws ParseException { 
		l.debug("Test méthode getChiffreAffaireEntreDeuxDate");
		
		String sDate1="31/12/2021";  
	    Date d1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
	    
	    String sDate2="31/12/2022";  
	    Date d2=new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);  
	    
	    String sDate3="31/12/2023";  
	    Date d3=new SimpleDateFormat("dd/MM/yyyy").parse(sDate3);  
		
		Reglement r1 = new Reglement(10,10,true,d2);
		Reglement r2 = new Reglement(10,10,true,d2);
		
		reglementRepository.save(r1);
		reglementRepository.save(r2);

		
		try {
			when(reglementRepository.getChiffreAffaireEntreDeuxDate(d1, d3)).thenReturn((float) 20);
			assertEquals(20,reglementServiceImpl.getChiffreAffaireEntreDeuxDate(d1, d3),0);
			
			l.info(" Reglement retrievedby deux date succesfully");
		} catch (Exception e) {
			l.error("méthode getChiffreAffaireEntreDeuxDate ERROR: "+e);
		}
	}

}
