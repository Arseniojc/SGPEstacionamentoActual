package dao.estacionamento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.estacionamento.Disponibilidade;
import model.estacionamento.Vaga;

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
        }
    }
    
    public List<Vaga> listar(){
        em.getTransaction().begin();
        String jpql = "SELECT v FROM Veiculo v";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
    
    public void actualizar(Vaga vaga) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(vaga);
            em.getTransaction().commit();
            System.out.println("Dados Actualizados com sucesso.");
        } catch (Exception e) {
            throw new Exception("Erro na actualizacao de dados");
        }
    }
    
    public Vaga pesquisarComBaseNoEndereco(String endereco) throws Exception{
        Vaga vaga = null;
        
        try {
            vaga = em.find(Vaga.class, endereco);
        } catch (Exception e) {
            throw new Exception("Nao foi localizado um vaga com o endereco informado");
        } finally {
            em.close();
            emf.close();
        }
        
        return vaga;
    }
    
    public List<Vaga> pesquisarComBaseNaDisponibilidade(Disponibilidade disponibilidade) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Vaga> criteriaQuery = criteriaBuilder.createQuery(Vaga.class);
        Root<Vaga> root = criteriaQuery.from(Vaga.class);
        
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("disponibilidade"), disponibilidade.valor));
        
        em.getTransaction().begin();
        
        List<Vaga> vagasDisponiveis = em.createQuery(criteriaQuery).getResultList();
        
        return vagasDisponiveis;
    }
}
