package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Proprietario;


public class ProprientarioDao {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public ProprientarioDao(){
        emf = Persistence.createEntityManagerFactory("SistemaGPEPU");
        em = emf.createEntityManager();
    }
    
    public void salvar(Proprietario proprietario){
        try {
            em.getTransaction().begin();
            em.persist(proprietario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
