package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Vaga;

public class VagaDao {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public VagaDao(){
        emf = Persistence.createEntityManagerFactory("SistemaGPEstacionamentoPU");
        em = emf.createEntityManager();
    }
    
    public void inserir(Vaga vaga){
        try {
            em.getTransaction().begin();
            em.persist(vaga);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public List<Vaga> listar(){
        em.getTransaction().begin();
        String jpql = "SELECT v FROM Veiculo v";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
    
    public void listarVaga(){
        
        for(Vaga vaga: listar()){
            System.out.println("Marca: " + vaga.getEndereco() );
        }
    }
    
    public void actualizar(Vaga vaga) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(vaga);
            em.getTransaction().commit();
            System.out.println("Dados Actualizados com sucesso.");
        } catch (Exception e) {
            throw new Exception("Erro na actualizacao de dados");
        } finally {
            em.close();
        }
    }
    
    public Vaga pesquisar(int id) throws Exception{
        
        Vaga vaga = null;
        
        try {
            vaga = em.find(Vaga.class, id);
        } catch (Exception e) {
            throw new Exception("Nao foi localizado um vaga com o ID informado");
        } finally {
        }
        return vaga;
    }
    
    public void deletar(int id) throws Exception{
        try {
            em.getTransaction().begin();
            Vaga vaga = em.find(Vaga.class, id);
            vaga.setEstado(false);
            em.merge(vaga);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Erro na eliminacao do Vaga");
        } finally {
            em.close();
        }
    }
}
