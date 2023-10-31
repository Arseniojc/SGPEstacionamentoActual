package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Veiculo;

public class VeiculoDao {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public VeiculoDao(){
        emf = Persistence.createEntityManagerFactory("SistemaGPEstacionamentoPU");
        em = emf.createEntityManager();
    }
    
    public void inserir(Veiculo veiculo){
        try {
            em.getTransaction().begin();
            em.persist(veiculo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public List<Veiculo> listar(){
        em.getTransaction().begin();
        String jpql = "SELECT v FROM Veiculo v";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
    
    public void listarVeiculo(){
        
        for(Veiculo veiculos: listar()){
            System.out.println("Marca: " + veiculos.getModelo() );
        }
    }
    
    public void actualizar(Veiculo veiculo) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(veiculo);
            em.getTransaction().commit();
            System.out.println("Dados Actualizados com sucesso.");
        } catch (Exception e) {
            throw new Exception("Erro na actualizacao de dados");
        } finally {
            em.close();
        }
    }
    
    public Veiculo pesquisar(int id) throws Exception{
        
        Veiculo veiculo = null;
        
        try {
            veiculo = em.find(Veiculo.class, id);
        } catch (Exception e) {
            throw new Exception("Nao foi localizado um veiculo com o ID informado");
        } finally {
        }
        return veiculo;
    }
    
    public void deletar(int id) throws Exception{
        try {
            em.getTransaction().begin();
            Veiculo veiculo = em.find(Veiculo.class, id);
            veiculo.setEstado(false);
            em.merge(veiculo);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Erro na eliminacao do veiculo");
        } finally {
            em.close();
        }
    }
    
}
