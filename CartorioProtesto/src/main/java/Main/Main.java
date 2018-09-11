package Main;

import JPA.Credor;
import JPA.Devedor;
import JPA.Recepcao;
import java.util.Date;
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
        devedor.setNome("Guilherme Ricardo 2");
        devedor.setCpf("112.123.123-33");
        
        Credor credor = new Credor();
        credor.setNome("Beltrano");
        credor.setEmail("beltrano@gmail.com");
        credor.setTelefone("3344-5566");
        credor.setCpf("332.321.321-11");
        
        Recepcao recepcao = new Recepcao();
        recepcao.setNumero(7);
        recepcao.setData(new Date(System.currentTimeMillis()));
        //devedor.setRecepcao(recepcao);
        recepcao.setCredor(credor);
        recepcao.setDevedor(devedor);
        
        //credor.setRecepcao(recepcao);
        
        try{
            emf = Persistence.createEntityManagerFactory("CartorioProtesto");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(devedor);
           // em.persist(recepcao);
            //em.persist(credor);
            
            em.getTransaction().commit();
        } catch(Exception ex){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
         
    }
    
}