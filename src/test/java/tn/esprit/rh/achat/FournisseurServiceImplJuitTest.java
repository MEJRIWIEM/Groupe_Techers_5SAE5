package tn.esprit.rh.achat;

import java.util.Date;
import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.services.IFournisseurService;
import tn.esprit.rh.achat.services.ISecteurActiviteService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class FournisseurServiceImplJuitTest {

    @Autowired
    IFournisseurService fs;

    @Autowired
    ISecteurActiviteService sa;


    @Test
    @Order(1)
    public void testRetrieveAllFournisseurs() {
        List<Fournisseur> listFournisseurs = fs.retrieveAllFournisseurs();
        Assertions.assertEquals(0, listFournisseurs.size());
    }

    @Test
    @Order(2)
    public void testaddFournisseur() {
        // detail fournisseur
        DetailFournisseur df= new DetailFournisseur();
        df.setAdresse("Adresse f1");
        df.setMatricule("F1-MAT");
        df.setEmail("f1@esprit.tn");
        df.setDateDebutCollaboration(new Date());
        // fournisseur
        Fournisseur f = new Fournisseur();
        f.setDetailFournisseur(df);
        f.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        f.setCode("TEST-F1");
        f.setLibelle("Je suis fournisseur 1");
        Fournisseur addedFournisseur = fs.addFournisseur(f);
        // Fournisseur verification
        Assertions.assertEquals(f.getCode(), addedFournisseur.getCode());

    }

    @Test
    @Order(3)
    public void testAssignSecteurActiviteToFournisseur() {
        // create SecteurActivite
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setCodeSecteurActivite("SECTEUR1");
        secteurActivite.setLibelleSecteurActivite("Libelle secteur activite 1");
        SecteurActivite addedSecteurActivite = sa.addSecteurActivite(secteurActivite);
        //
        fs.assignSecteurActiviteToFournisseur(addedSecteurActivite.getIdSecteurActivite(),1L);
        Assertions.assertEquals(1, fs.retrieveFournisseur(1L).getSecteurActivites().size());

    }

    @Test
    @Order(4)
    public void testModifyFournisseur() {
        DetailFournisseur df= new DetailFournisseur();
        df.setDateDebutCollaboration(new Date());
        Fournisseur f = fs.retrieveFournisseur(1L);
        f.getDetailFournisseur().setMatricule("F1-MAT-UPDATED");
        Fournisseur updatedFournisseur = fs.updateFournisseur(f);
        Assertions.assertEquals(f.getDetailFournisseur().getMatricule(), updatedFournisseur.getDetailFournisseur().getMatricule());
    }

    @Test
    @Order(5)
    public void testRetrieveFournisseur() {
        Fournisseur fournisseurRetrieved = fs.retrieveFournisseur(1L);
        Assertions.assertEquals(1L, fournisseurRetrieved.getIdFournisseur());
    }


    @Test
    @Order(6)
    public void testDeleteFournisseur() {
        fs.deleteFournisseur(1L);
        Assertions.assertNull(fs.retrieveFournisseur(1L));
    }



}
