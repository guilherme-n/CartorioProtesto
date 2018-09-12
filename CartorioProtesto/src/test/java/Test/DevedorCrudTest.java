package Test;

import JPA.Devedor;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DevedorCrudTest extends TesteGenerico {

    @Test
    public void e1persistirDevedor() {
        logger.info("Executando persistirDevedor()");
        Devedor devedor = new Devedor();
        devedor.setNome("Beltrano");
        devedor.setCpf("447.859.659-89");
        em.persist(devedor);
        em.flush();
        assertNotNull(devedor.getId());
    }
    
    @Test
    public void e2atualizarDevedor(){
        logger.info("Executando persistirDevedor()");
        TypedQuery<Devedor> query = em.createNamedQuery("Devedor.PorNome", Devedor.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("nome", "Beltrano");
        Devedor devedor = query.getSingleResult();
        assertNotNull(devedor);
        devedor.setNome("Fulano");
        em.flush();
        assertEquals(0, query.getResultList().size());
        query.setParameter("nome", "Fulano");
        devedor = query.getSingleResult();
        assertNotNull(devedor);
    }
    
    @Test
    public void e3atualizarDevedorMerge(){
        logger.info("Executando atualizarDevedorMerge()");
        TypedQuery<Devedor> query = em.createNamedQuery("Devedor.PorNome", Devedor.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("nome", "Fulano");
        Devedor devedor = query.getSingleResult();
        assertNotNull(devedor);
        devedor.setNome("Fulano de Tal");
        em.clear();        
        em.merge(devedor);
        em.flush();
        assertEquals(0, query.getResultList().size());
    }
    
    @Test
    public void e4removerDevedor() {
        logger.info("Executando removerDevedor()");
        TypedQuery<Devedor> query = em.createNamedQuery("Devedor.PorNome", Devedor.class);
        query.setParameter("nome", "Fulano de Tal");
        Devedor devedor = query.getSingleResult();
        assertNotNull(devedor);
        em.remove(devedor);
        em.flush();
        assertEquals(0, query.getResultList().size());
    }
}
