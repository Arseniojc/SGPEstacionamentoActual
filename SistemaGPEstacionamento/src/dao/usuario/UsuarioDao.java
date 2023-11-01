package dao.usuario;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.usuario.Usuario;

public class UsuarioDao {
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public UsuarioDao(){
        emf = Persistence.createEntityManagerFactory("SistemaGPEstacionamentoPU");
        em = emf.createEntityManager();
    }
    
    public void inserir(Usuario usuario) {
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public List<Usuario> listar(){
        em.getTransaction().begin();
        String jpql = "SELECT u FROM Usuario u";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }
    
    public void actualizar(Usuario usuario) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
            System.out.println("Dados Actualizados com sucesso.");
        } catch (Exception e) {
            throw new Exception("Erro na actualizacao de dados");
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public Usuario pesquisar(int id) throws Exception {
        Usuario usuario = null;
        
        try {
            usuario = em.find(Usuario.class, id);
        } catch (Exception e) {
            throw new Exception("Nao foi localizado um veiculo com o ID informado");
        } finally {
            em.close();
            emf.close();
        }
        
        return usuario;
    }
    
    public Usuario pesquisarPorNomeESenha(String nome, String senha) throws Exception {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
        Root<Usuario> root = criteriaQuery.from(Usuario.class);
        
        criteriaQuery.select(root).where(
                        criteriaBuilder.equal(root.get("nome"), nome)
        );
        
        Usuario usuario = null;
        
        try {
            em.getTransaction().begin();
            usuario = em.createQuery(criteriaQuery).getSingleResult();
            em.getTransaction().commit();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
            emf.close();
        }

        return usuario;
    }
    
    public void deletar(int id) throws Exception {
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, id);
            
            System.out.println(usuario);
            
            usuario.setEstado(false);
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Erro na eliminacao do Usuario");
        } finally {
            em.close();
            emf.close();
        }
    }
}
