package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.rh.achat.entities.Facture;

import tn.esprit.rh.achat.repositories.FactureRepository;

import tn.esprit.rh.achat.services.FactureServiceImpl;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
 class FactureServiceImpTest {
	@InjectMocks
	private FactureServiceImpl factureServiceImpl;
	@Mock
	private FactureRepository factureRepository;
	@Test
	public void retrieveAllFactures() {
		 when(factureRepository.findAll()).thenReturn(Stream.of(new Facture(),new Facture(),new Facture()).collect(Collectors.toList())) ; 
		 assertEquals(3,factureServiceImpl.retrieveAllFactures().size());
	}

	@Test
	public void addFacture() {
		Facture facture=new Facture();
		when(factureRepository.save(facture)).thenReturn(facture);
		assertEquals(facture, factureServiceImpl.addFacture(facture));
	}


	   @Test
	    public void retrieveFactureTest(){
	        Facture facture = new Facture();
	        when(factureRepository.findById(facture.getIdFacture())).thenReturn(java.util.Optional.of(facture));
	        assertEquals(facture.getIdFacture(),factureServiceImpl.retrieveFacture(facture.getIdFacture()).getIdFacture());
	    }
	@Test
	public void cancelFacture() {
		Facture facture = new Facture();
		Facture factureSecond=new Facture();
		factureServiceImpl.cancelFacture(facture.getIdFacture());
		factureServiceImpl.cancelFacture(factureSecond.getIdFacture());
		verify(factureRepository,times(2)).updateFacture(facture.getIdFacture());
	}
	}

