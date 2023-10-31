package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Proprietario;


public class ProprientarioDao {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public ProprientarioDao(){
        emf = Persistence.createEntityManagerFactory("SistemaGPEstacionamentoPU");
        em = emf.createEntityManager();
    }
    
    public void inserir(Proprietario proprietarios){
        try {
            em.getTransaction().begin();
            em.persist(proprietarios);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public List<Proprietario> listar(){
        em.getTransaction().begin();
        String jpql = "SELECT v FROM Veiculo v";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
    
    public void listarProprietarios(){
        
        for(Proprietario proprietarios: listar()){
            System.out.println("Marca: " + proprietarios.getNome() );
        }
    }
    
    public void actualizar(Proprietario proprietario) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(proprietario);
            em.getTransaction().commit();
            System.out.println("Dados Actualizados com sucesso.");
        } catch (Exception e) {
            throw new Exception("Erro na actualizacao de dados");
        } finally {
            em.close();
        }
    }
    
    public Proprietario pesquisar(int id) throws Exception{
        
        Proprietario proprietario = null;
        
        try {
            proprietario = em.find(Proprietario.class, id);
        } catch (Exception e) {
            throw new Exception("Nao foi localizado um Proprietario com o ID informado");
        } finally {
        }
        return proprietario;
    }
    
    public void deletar(int id) throws Exception{
        try {
            em.getTransaction().begin();
            Proprietario proprietario = em.find(Proprietario.class, id);
            proprietario.setEstado(false);
            em.merge(proprietario);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Erro na eliminacao do veiculo");
        } finally {
            em.close();
        }
    }
}
