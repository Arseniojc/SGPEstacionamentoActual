package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Estadia;

public class EstadiaDao {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public EstadiaDao(){
        emf = Persistence.createEntityManagerFactory("SistemaGPEPU");
        em = emf.createEntityManager();
    }
    
    public void salvar(Estadia estadia){
        try {
            em.getTransaction().begin();
            em.persist(estadia);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
