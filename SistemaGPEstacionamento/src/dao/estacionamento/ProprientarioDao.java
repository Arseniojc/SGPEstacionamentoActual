package dao.estacionamento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.estacionamento.Proprietario;

public class ProprientarioDao {
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public ProprientarioDao(){
        emf = Persistence.createEntityManagerFactory("SistemaGPEstacionamentoPU");
        em = emf.createEntityManager();
    }
    
    public List<Proprietario> listar(){
        em.getTransaction().begin();
        String jpql = "SELECT v FROM Veiculo v";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
    
    public void actualizar(Proprietario proprietario) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(proprietario);
            em.getTransaction().commit();
            System.out.println("Dados Actualizados com sucesso.");
        } catch (Exception e) {
            throw new Exception("Erro na actualizacao de dados");
        }
    }
    
    public Proprietario pesquisar(int id) throws Exception {
        Proprietario proprietario = null;
        
        try {
            proprietario = em.find(Proprietario.class, id);
        } catch (Exception e) {
            throw new Exception("Nao foi localizado um Proprietario com o ID informado");
        }
        
        return proprietario;
    }
    
    public List<Proprietario> pesquisarComBaseNoNome(String nome) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Proprietario> criteriaQuery = criteriaBuilder.createQuery(Proprietario.class);
        Root<Proprietario> root = criteriaQuery.from(Proprietario.class);
        
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("nome"), nome));
        
        em.getTransaction().begin();
        
        List<Proprietario> proprietarios = em.createQuery(criteriaQuery).getResultList();
        
        em.close();
        emf.close();
        
        return proprietarios;
    }
}
