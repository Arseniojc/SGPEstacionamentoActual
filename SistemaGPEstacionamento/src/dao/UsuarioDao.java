package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Usuario;

public class UsuarioDao {
    
    private EntityManager em;
    private EntityManagerFactory emf;
    
    public UsuarioDao(){
        emf = Persistence.createEntityManagerFactory("SistemaGPEstacionamentoPU");
        em = emf.createEntityManager();
    }
    
    public void inserir(Usuario usuario){
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
    
    public void listarUsuario(){
        
        for(Usuario usuarios: listar()){
            System.out.println("Marca: " + usuarios.getNomeUsuario() );
        }
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
        }
    }
    
    public Usuario pesquisar(int id) throws Exception{
        
        Usuario usuario = null;
        
        try {
            usuario = em.find(Usuario.class, id);
        } catch (Exception e) {
            throw new Exception("Nao foi localizado um veiculo com o ID informado");
        } finally {
        }
        return usuario;
    }
    
    public void deletar(int id) throws Exception{
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
        }
    }
    
    
    
}
