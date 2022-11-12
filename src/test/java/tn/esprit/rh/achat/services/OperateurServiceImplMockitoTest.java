package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OperateurServiceImplMockitoTest {
/*
    @InjectMocks
    private OperateurServiceImpl OperateurService;

     @Mock
     private OperateurRepository operateurRepository;

    @Test
    void retrieveAllOperateurs() {
        List<Operateur> Operateurs = new ArrayList<Operateur>(){
            {
                add(new Operateur(null,"test1","test1","test1",null));
                add(new Operateur(null,"test2","test2","test2",null));
                add(new Operateur(null,"test3","test3","test3",null));
            }
        };
        when(OperateurService.retrieveAllOperateurs()).thenReturn(Operateurs);
        List<Operateur> opList = OperateurService.retrieveAllOperateurs();
        assertEquals(3, opList.size());
    }

    @Test
    void addOperateur() {
        Operateur op = new Operateur(null,"test","test","test",null);
        op.setIdOperateur(2L);
        OperateurService.addOperateur(op);
        verify(operateurRepository, times(1)).save(op);
    }

    @Test
    void deleteOperateur() {
        Operateur op = new Operateur(null,"test","test","test",null);
        op.setIdOperateur(10L);
        Mockito.lenient().when(operateurRepository.findById(op.getIdOperateur())).thenReturn(Optional.of(op));
        OperateurService.deleteOperateur(10L);
        verify(operateurRepository).deleteById(op.getIdOperateur());
    }

    @Test
    void updateOperateur() {
        Operateur op = new Operateur(null,"test","test","test",null);
        when(operateurRepository.save(op)).thenReturn(op);
        assertEquals(op, OperateurService.updateOperateur(op));
    }

    @Test
    void retrieveOperateur() {
        Operateur op = new Operateur(1L,"test","test","test",null);
        op.setIdOperateur(1L);
        Mockito.when(operateurRepository.findById(1L)).thenReturn(Optional.of(op));
        OperateurService.retrieveOperateur(1L);
        Assertions.assertNotNull(op);

    }*/
}