package Test;

import JPA.Guia;
import JPA.Recepcao;
import java.util.Date;
import java.util.Set;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.Test;
import static org.junit.Assert.*;

public class GuiaValidationTest extends TesteGenerico {    
    

    @Test(expected = ConstraintViolationException.class)
    public void persistirGuiaInvalida() {
        Guia guia = null;
        try{
            TypedQuery<Recepcao> queryrecepcao = em.createNamedQuery("Recepcao.PorNumero", Recepcao.class);
            queryrecepcao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
            queryrecepcao.setParameter("numero", 2018045);
            Recepcao recepcao = queryrecepcao.getSingleResult();
            guia = new Guia();
            guia.setNumero(20181545);
            guia.setValor(2558.98);
            guia.setData(new Date());
            em.persist(guia);
            
        }catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            
            constraintViolations.forEach((violation) -> {
                System.out.println(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage());
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(), 
                        CoreMatchers.anyOf(
                                startsWith("class JPA.Guia.recepcao: may not be null")));
            });
            
            assertEquals(1, constraintViolations.size());            
            assertNull(guia.getId());
            throw ex;
        }        
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarGuiaInvalida() {
        TypedQuery<Guia> queryGuia = em.createQuery(
                "SELECT g FROM Guia g WHERE g.numero = ?1",
                Guia.class);
        queryGuia.setParameter(1 , 20180004);
        Guia guia = queryGuia.getSingleResult();
        guia.setValor(0.05);

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {    
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            constraintViolations.forEach((violation) -> {
                assertThat(violation.getMessage(), 
                        CoreMatchers.anyOf(
                                startsWith("não pode estar vazio")));
            });
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
