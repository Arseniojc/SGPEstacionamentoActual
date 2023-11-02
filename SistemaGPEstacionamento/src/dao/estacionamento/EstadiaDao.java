package dao.estacionamento;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.estacionamento.Estadia;
import model.estacionamento.Proprietario;

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
        }
    }
    
    public List<Estadia> listar(){
        em.getTransaction().begin();
        String jpql = "SELECT e FROM Estadia e";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
    
    public void actualizar(Estadia estadia) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(estadia);
            em.getTransaction().commit();
            System.out.println("Dados Actualizados com sucesso.");
        } catch (Exception e) {
            throw new Exception("Erro na actualizacao de dados");
        }
    }
    
    public Estadia pesquisar(int id) throws Exception {
        Estadia estadia = null;
        
        try {
            estadia = em.find(Estadia.class, id);
        } catch (Exception e) {
            throw new Exception("Nao foi localizado um estadia com o ID informado");
        }
        
        return estadia;
    }
    
    public Estadia pesquisarComBaseNaVaga(String vaga) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Estadia> criteriaQuery = criteriaBuilder.createQuery(Estadia.class);
        Root<Estadia> root = criteriaQuery.from(Estadia.class);
        
        criteriaQuery.select(root).where(
                        criteriaBuilder.equal(root.get("vaga"), vaga)
        );
        
        em.getTransaction().begin();
        
        Estadia estadia = em.createQuery(criteriaQuery).getSingleResult();
        
        em.close();
        emf.close();
        
        return estadia;
    }
    
    public Estadia pesquisarComBaseNoProprietario(Proprietario proprietario) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Estadia> criteriaQuery = criteriaBuilder.createQuery(Estadia.class);
        Root<Estadia> root = criteriaQuery.from(Estadia.class);
        
        criteriaQuery.select(root).where(
                        criteriaBuilder.equal(root.get("proprietario"), proprietario)
        );
        
        em.getTransaction().begin();
        
        Estadia estadia = em.createQuery(criteriaQuery).getSingleResult();
        
        em.close();
        emf.close();
        
        return estadia;
    }
    
    public Estadia pesquisarComBaseNoProprietarioEVaga(Proprietario proprietario  ) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Estadia> criteriaQuery = criteriaBuilder.createQuery(Estadia.class);
        Root<Estadia> root = criteriaQuery.from(Estadia.class);
        
        criteriaQuery.select(root).where(
                        criteriaBuilder.equal(root.get("proprietario"), proprietario)
        );
        
        em.getTransaction().begin();
        
        Estadia estadia = em.createQuery(criteriaQuery).getSingleResult();
        
        em.close();
        emf.close();
        
        return estadia;
    }
}
