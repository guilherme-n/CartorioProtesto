package Main;

import JPA.Devedor;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    private static EntityManagerFactory emf;
    private static Logger logger;
    private static EntityManager em;
    private static EntityTransaction et;
        
    public static void main(String[] args){
        Devedor devedor = new Devedor();
        devedor.setNome("Guilherme Ricardo");
        devedor.setCpf("112.123.123-33");
        
        try{
            emf = Persistence.createEntityManagerFactory("CartorioProtesto");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(devedor);
            
            em.getTransaction().commit();
        } catch(Exception ex){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
         
    }
    
}