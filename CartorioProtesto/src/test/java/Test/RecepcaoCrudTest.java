package Test;

import JPA.Recepcao;
import JPA.Titulo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecepcaoCrudTest extends TesteGenerico {

    @Test
    public void e1persistirRecepcao() {
        logger.info("Executando persistirRecepcao()");
        
        Recepcao recepcao = new Recepcao();
        recepcao.setNumero(2018005);
        recepcao.setData(new Date());
        Titulo titulo = new Titulo();
        titulo.setNatureza("Cheque");
        titulo.setValor("2548,56");
        List <Titulo> titulos = new ArrayList();
        titulos.add(titulo);
        recepcao.setTitulo(titulos);        
        em.persist(recepcao);
        em.flush();
        assertNotNull(recepcao.getId());
    }
    
    @Test
    public void e2atualizarRecepcao(){
        logger.info("Executando persistirRecepcao()");
        TypedQuery<Recepcao> query = em.createNamedQuery("Recepcao.PorNumero", Recepcao.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("numero", 2018005);
        Recepcao recepcao = query.getSingleResult();
        assertNotNull(recepcao);
        recepcao.setNumero(2018006);
        em.flush();
        assertEquals(0, query.getResultList().size());
        query.setParameter("numero", 2018006);
        recepcao = query.getSingleResult();
        assertNotNull(recepcao);
    }
    
    @Test
    public void e3atualizarRecepcaoMerge(){
        logger.info("Executando atualizarRecepcaoMerge()");
        TypedQuery<Recepcao> query = em.createNamedQuery("Recepcao.PorNumero", Recepcao.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("numero", 2018006);
        Recepcao recepcao = query.getSingleResult();
        assertNotNull(recepcao);
        recepcao.setNumero(2018007);
        em.clear();        
        em.merge(recepcao);
        em.flush();
        assertEquals(0, query.getResultList().size());
    }
    
    @Test
    public void e4removerRecepcao() {
        logger.info("Executando removerRecepcao()");
        TypedQuery<Recepcao> query = em.createNamedQuery("Recepcao.PorNumero", Recepcao.class);
        query.setParameter("numero", 2018007);
        Recepcao recepcao = query.getSingleResult();
        assertNotNull(recepcao);
        em.remove(recepcao);
        em.flush();
        assertEquals(0, query.getResultList().size());
    }
}
