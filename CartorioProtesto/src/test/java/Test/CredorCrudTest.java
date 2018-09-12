package Test;

import JPA.Credor;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CredorCrudTest extends TesteGenerico {

    @Test
    public void e1persistirCredor() {
        logger.info("Executando persistirCredor()");
        Credor credor = new Credor();
        credor.setNome("Beltrano");
        credor.setEmail("beltrano@gmail.com");
        credor.setTelefone("3344-5566");
        credor.setCpf("332.321.321-11");
        em.persist(credor);
        em.flush();
        assertNotNull(credor.getId());
    }
    
    @Test
    public void e2atualizarCredor(){
        logger.info("Executando persistirCredor()");
        TypedQuery<Credor> query = em.createNamedQuery("Credor.PorNome", Credor.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("nome", "Beltrano");
        Credor credor = query.getSingleResult();
        assertNotNull(credor);
        credor.setNome("Fulano");
        em.flush();
        assertEquals(0, query.getResultList().size());
        query.setParameter("nome", "Fulano");
        credor = query.getSingleResult();
        assertNotNull(credor);
    }
    
    @Test
    public void e3atualizarCredorMerge(){
        logger.info("Executando atualizarCredorMerge()");
        TypedQuery<Credor> query = em.createNamedQuery("Credor.PorNome", Credor.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("nome", "Fulano");
        Credor credor = query.getSingleResult();
        assertNotNull(credor);
        credor.setNome("Fulano de Tal");
        em.clear();        
        em.merge(credor);
        em.flush();
        assertEquals(0, query.getResultList().size());
    }
    
    @Test
    public void e4removerCredor() {
        logger.info("Executando removerCredor()");
        TypedQuery<Credor> query = em.createNamedQuery("Credor.PorNome", Credor.class);
        query.setParameter("nome", "Fulano de Tal");
        Credor credor = query.getSingleResult();
        assertNotNull(credor);
        em.remove(credor);
        em.flush();
        assertEquals(0, query.getResultList().size());
    }
}
