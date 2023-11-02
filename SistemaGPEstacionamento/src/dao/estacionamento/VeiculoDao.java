package dao.estacionamento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.estacionamento.Veiculo;

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
    
    public List<Veiculo> listar() {
        em.getTransaction().begin();
        String jpql = "SELECT v FROM Veiculo v";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
    
    public void actualizar(Veiculo veiculo) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(veiculo);
            em.getTransaction().commit();
            System.out.println("Dados Actualizados com sucesso.");
        } catch (Exception e) {
            throw new Exception("Erro na actualizacao de dados");
        }
    }
    
    public Veiculo pesquisar(int id) throws Exception {
        Veiculo veiculo = null;
        
        try {
            veiculo = em.find(Veiculo.class, id);
        } catch (Exception e) {
            throw new Exception("Nao foi localizado um veiculo com o ID informado");
        }
        
        return veiculo;
    }
}
