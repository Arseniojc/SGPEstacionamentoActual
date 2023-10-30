package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Usuario;

public class UsuarioDao {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public UsuarioDao(){
        emf = Persistence.createEntityManagerFactory("SistemaGPEPU");
        em = emf.createEntityManager();
    }
    
    public void salvar(Usuario usuario){
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
