package Test;

import JPA.Credor;
import JPA.Devedor;
import static Test.TesteGenerico.logger;
import java.util.List;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DevedorJpqlTest extends TesteGenerico {

    @Test
    public void devedorPorNome() {
        logger.info("Executando credorPorNome()");
        TypedQuery<Devedor> querydevedor = em.createQuery(
                "SELECT c FROM Devedor c WHERE c.nome LIKE :nome",
                Devedor.class);
        querydevedor.setParameter("nome", "%Maria%");
        List<Devedor> devedores = querydevedor.getResultList();

        devedores.forEach((credor) -> {
            assertTrue(credor.getNome().contains("Maria"));
       });

        assertEquals(3, devedores.size());
    }
    
}
