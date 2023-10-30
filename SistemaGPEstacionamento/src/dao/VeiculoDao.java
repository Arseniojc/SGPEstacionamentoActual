package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Veiculo;

public class VeiculoDao {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public void salvar(Veiculo veiculo){
        try {
            em.getTransaction().begin();
            em.persist(veiculo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
