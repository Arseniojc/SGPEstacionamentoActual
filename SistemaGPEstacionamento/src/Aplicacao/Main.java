package Aplicacao;

import dao.EstadiaDao;
import dao.ProprientarioDao;
import dao.UsuarioDao;
import dao.VagaDao;
import dao.VeiculoDao;
import model.Estadia;
import model.Proprietario;
import model.Usuario;
import model.Vaga;
import model.Veiculo;

public class Main {
    
    public static void main(String[] args) throws Exception{
        
//        Usuario u = new Usuario();
//        UsuarioDao dao = new UsuarioDao();
//        u.setNomeUsuario("felix");
//        u.setSenha("123");
//       u.setId(1);
//        
////        dao.actualizar(u);
//        System.out.println(dao.listar());
//        dao.deletar(1);
//        System.out.println(dao.listar()); 

        VeiculoDao dao = new VeiculoDao();
        ProprientarioDao pdao = new ProprientarioDao();
        
        Veiculo v = new Veiculo();
        Proprietario p = new Proprietario();
        
        p.setNome("felix");
        p.setContacto("87655");
        p.setEndereco("utreywt");
        p.setId(1);
//        
        v.setMarca("bmw");
        v.setMatricula("123456");
        v.setModelo("m4");
        v.setProprietario(p);
        
        dao.inserir(v);
    }
}
