package tn.esprit.rh.achat;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.*;
import tn.esprit.rh.achat.services.FactureServiceImpl;
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@WebMvcTest 
@Slf4j
public class FacturetestMokito {
	@Mock
	FactureRepository facturerepo;

   @InjectMocks
   FactureServiceImpl factureService;
 
    @Test
    public void retrieveFacture(){
        Facture f = new Facture(1L,233,11,null,null,true,null,null,null);

        when(facturerepo.findById(1L)).thenReturn(Optional.of(f));
        Facture Facture= factureService.retrieveFacture((long) 1);
        Assertions.assertNotNull(Facture);
        log.info("get ==>"+ Facture.toString());
    }
    @Test
    public void addFacture(){
    	Facture f = new Facture(2L,233,11,null,null,true,null,null,null);
        f.setIdFacture(2L);
        factureService.addFacture(f);
        verify(facturerepo, times(1)).save(f);
        System.out.println(f);
        log.info("add ==>"+ f.toString());
    }

    @Test
    public void retrieveAllFactures()
    {
        List<Facture> Lf = new ArrayList<Facture>() {
			private static final long serialVersionUID = 1L;

			{
                add(new Facture(3L,233,11,null,null,true,null,null,null));
                add(new Facture(4L,233,11,null,null,true,null,null,null));
                add(new Facture(5L,233,11,null,null,true,null,null,null));
            }};

        when(factureService.retrieveAllFactures()).thenReturn(Lf);
        //test
        List<Facture> factureList = factureService.retrieveAllFactures();
        assertEquals(3, factureList.size());
        log.info("retrieve all ==>"+ factureList.toString());

    }

    @Test
    public void cancelFacture() {
    	Facture f = new Facture(6L,233,11,null,null,true,null,null,null);

        facturerepo.save(f);
        factureService.cancelFacture(f.getIdFacture());
        verify(facturerepo, times(1)).deleteById(f.getIdFacture());
        log.info("delete ==>"+ f.toString());

    }
}
