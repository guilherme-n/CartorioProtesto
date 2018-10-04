package Test;

import JPA.Credor;
import static Test.TesteGenerico.logger;
import java.util.List;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CredorJpqlTets extends TesteGenerico {

    @Test
    public void credorPorNome() {
        logger.info("Executando credorPorNome()");
        TypedQuery<Credor> querycredor = em.createQuery(
                "SELECT c FROM Credor c WHERE c.nome LIKE :nome",
                Credor.class);
        querycredor.setParameter("nome", "Kassimo%");
        List<Credor> credores = querycredor.getResultList();

        credores.forEach((credor) -> {
            assertTrue(credor.getNome().startsWith("Kassimo"));
       });

        assertEquals(2, credores.size());
    }
    
    @Test
    public void credorPorCPF() {
        logger.info("Executando credorPorCPF()");
        TypedQuery<Credor> querycredor = em.createQuery(
                "SELECT c FROM Credor c WHERE c.cpf = :cpf",
                Credor.class);
        querycredor.setParameter("cpf", "562.989.480-31");
        List<Credor> credores = querycredor.getResultList();

        assertEquals(1, credores.size());
    }
    
    @Test
    public void recepcaoPorCredor() {
        logger.info("Executando recepcaoPorCredor()");
        TypedQuery<Credor> querycredor = em.createQuery(
                "SELECT c FROM Credor c WHERE c.cpf = :cpf",
                Credor.class);
        querycredor.setParameter("cpf", "445.456.456-66");
        List<Credor> credores = querycredor.getResultList();
        assertEquals(1, credores.size());
        
        assertEquals(2, credores.get(0).getRecepcoes().size());
    }
}
