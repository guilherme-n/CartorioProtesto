package Test;

import JPA.Guia;
import java.util.Date;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GuiaCrudTest extends TesteGenerico {

    @Test
    public void e1persistirGuia() {
        logger.info("Executando persistirGuia()");
        Guia guia = new Guia();
        guia.setNumero(141519);
        guia.setValor(2548.69);
        guia.setData(new Date());
        em.persist(guia);
        em.flush();
        assertNotNull(guia.getId());
    }

    @Test
    public void e2atualizarGuia() {
        logger.info("Executando persistirGuia()");
        TypedQuery<Guia> query = em.createNamedQuery("Guia.PorNumero", Guia.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("numero", 141519);
        Guia guia = query.getSingleResult();
        assertNotNull(guia);
        guia.setNumero(141518);
        em.flush();
        assertEquals(0, query.getResultList().size());
        query.setParameter("numero", 141518);
        guia = query.getSingleResult();
        assertNotNull(guia);
    }

    @Test
    public void e3atualizarGuiaMerge() {
        logger.info("Executando atualizarGuiaMerge()");
        TypedQuery<Guia> query = em.createNamedQuery("Guia.PorNumero", Guia.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("numero", 141518);
        Guia guia = query.getSingleResult();
        assertNotNull(guia);
        guia.setNumero(141517);
        em.clear();
        em.merge(guia);
        em.flush();
        assertEquals(0, query.getResultList().size());
        query.setParameter("numero", 141517);
        guia = query.getSingleResult();
        assertNotNull(guia);
    }

    @Test
    public void e4removerGuia() {
        logger.info("Executando removerGuia()");
        TypedQuery<Guia> query = em.createNamedQuery("Guia.PorNumero", Guia.class);
        query.setParameter("numero", 141517);
        Guia guia = query.getSingleResult();
        assertNotNull(guia);
        em.remove(guia);
        em.flush();
        assertEquals(0, query.getResultList().size());
    }
}
