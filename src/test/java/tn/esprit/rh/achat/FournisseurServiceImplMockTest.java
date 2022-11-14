package tn.esprit.rh.achat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import org.mockito.junit.jupiter.MockitoExtension;


import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

import java.util.*;



@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplMockTest {

    @Mock
    FournisseurRepository fournisseurRepository;

    @Mock
    DetailFournisseurRepository detailFournisseurRepository;

    @InjectMocks
    FournisseurServiceImpl fournisseurService;


    Fournisseur fournisseur = new Fournisseur(1L,"F1CODE","Je suis F1", CategorieFournisseur.ORDINAIRE,null,null,null);

    Fournisseur fournisseur4 = new Fournisseur(4L,"F4CODE","Je suis F4", CategorieFournisseur.ORDINAIRE,null,null,null);

    @Test
    public void retrieveAllFournisseursTest() {
        List<Fournisseur> liste = new ArrayList<Fournisseur>();
        liste.add(new Fournisseur(2L,"F2CODE","Je suis F2", CategorieFournisseur.ORDINAIRE,null,null,null));
        liste.add(new Fournisseur(3L,"F3CODE","Je suis F3", CategorieFournisseur.ORDINAIRE,null,null,null));
        Mockito.when(fournisseurRepository.findAll()).thenReturn(liste);
        List<Fournisseur> listFournisseurs = fournisseurService.retrieveAllFournisseurs();
        Assertions.assertEquals(liste.size(), listFournisseurs.size());

    }

    @Test
    public void retrieveFournisseurTest() {
        Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur));
        Fournisseur fournisseur1 = fournisseurService.retrieveFournisseur(1L);
        Assertions.assertNotNull(fournisseur1);
    }

    @Test
    public void addFournisseurTest() {
        // Add fournisseur
        Mockito.when(fournisseurRepository.save(ArgumentMatchers.any(Fournisseur.class))).thenReturn(fournisseur);
        Fournisseur addedFournisseur = fournisseurService.addFournisseur(fournisseur4);
        Assertions.assertEquals(fournisseur4.getIdFournisseur(),addedFournisseur.getIdFournisseur());
    }

    @Test
    public void modifyFournisseurTest() {
        Fournisseur f = new Fournisseur(1L,"F1CODE","Je suis F1", CategorieFournisseur.ORDINAIRE,null,null,null);
        // detail fournisseur
        DetailFournisseur df= new DetailFournisseur();
        df.setAdresse("Adresse f1");
        df.setMatricule("F1-MAT");
        df.setEmail("f1@esprit.tn");
        df.setDateDebutCollaboration(new Date());
        //
        f.setDetailFournisseur(df);
        Mockito.when(detailFournisseurRepository.save(df)).thenReturn(df);
        detailFournisseurRepository.save(df);
        Mockito.when(fournisseurRepository.save(f)).thenReturn(f);
        Assertions.assertEquals(f, fournisseurService.updateFournisseur(f));
    }



    @Test
    public void deleteFournisseurTest() {
        Mockito.lenient().when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur4));
        fournisseurService.deleteFournisseur(4L);
        verify(fournisseurRepository).deleteById(fournisseur4.getIdFournisseur());

    }






}
