package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Estadia;

public class EstadiaDao {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public EstadiaDao(){
        emf = Persistence.createEntityManagerFactory("SistemaGPEstacionamentoPU");
        em = emf.createEntityManager();
    }
    
    public void inserir(Estadia estadia){
        try {
            em.getTransaction().begin();
            em.persist(estadia);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public List<Estadia> listar(){
        em.getTransaction().begin();
        String jpql = "SELECT e FROM Estadia e";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
    
    public void listarEstadia(){
        
        for(Estadia estadias: listar()){
            System.out.println("Marca: " + estadias.getVeiculo() );
        }
    }
    
    public void actualizar(Estadia estadia) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(estadia);
            em.getTransaction().commit();
            System.out.println("Dados Actualizados com sucesso.");
        } catch (Exception e) {
            throw new Exception("Erro na actualizacao de dados");
        } finally {
            em.close();
        }
    }
    
    public Estadia pesquisar(int id) throws Exception{
        
        Estadia estadia = null;
        
        try {
            estadia = em.find(Estadia.class, id);
        } catch (Exception e) {
            throw new Exception("Nao foi localizado um estadia com o ID informado");
        } finally {
        }
        return estadia;
    }
    
    public void deletar(int id) throws Exception{
        try {
            em.getTransaction().begin();
            Estadia estadia = em.find(Estadia.class, id);
            estadia.setEstado(false);
            em.merge(estadia);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Erro na eliminacao do veiculo");
        } finally {
            em.close();
        }
    }
}
