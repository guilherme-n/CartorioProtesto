package Test;

import JPA.Devedor;
import static Test.TesteGenerico.logger;
import java.util.List;
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
                "SELECT d FROM Devedor d WHERE d.nome LIKE :nome",
                Devedor.class);
        querydevedor.setParameter("nome", "%Maria%");
        List<Devedor> devedores = querydevedor.getResultList();

        devedores.forEach((credor) -> {
            assertTrue(credor.getNome().contains("Maria"));
       });

        assertEquals(3, devedores.size());
    }
    
    @Test
    public void devedorPorCPF() {
        logger.info("Executando devedorPorCPF()");
        TypedQuery<Devedor> querydevedor = em.createQuery(
                "SELECT d FROM Devedor d WHERE d.cpf = :cpf",
                Devedor.class);
        querydevedor.setParameter("cpf", "400.078.780-28");
        List<Devedor> devedores = querydevedor.getResultList();

        assertEquals(1, devedores.size());
    }
    
    @Test
    public void recepcoesPorDevedor() {
        logger.info("Executando recepcoesPorDevedor()");
        TypedQuery<Devedor> querydevedor = em.createQuery(
                "SELECT d FROM Devedor d WHERE d.cpf = :cpf",
                Devedor.class);
        querydevedor.setParameter("cpf", "427.161.640-02");
        List<Devedor> devedores = querydevedor.getResultList();
        assertEquals(1, devedores.size());
        
        assertEquals(2, devedores.get(0).getRecepcoes().size());
    }    
}
