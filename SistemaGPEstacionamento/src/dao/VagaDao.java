package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Vaga;

public class VagaDao {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public VagaDao(){
        emf = Persistence.createEntityManagerFactory("SistemaGPEPU");
        em = emf.createEntityManager();
    }
    
    public void salvar(Vaga vaga){
        try {
            em.getTransaction().begin();
            em.persist(vaga);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
