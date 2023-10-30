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
    
    public static void main(String[] args) {
        
        Usuario u = new Usuario();
        Veiculo v = new Veiculo();
        Vaga vaga = new Vaga();
        Estadia e = new Estadia();
        Proprietario p = new Proprietario();
        
        UsuarioDao daoU = new UsuarioDao();
        VeiculoDao daoV = new VeiculoDao();
        VagaDao daoVaga = new VagaDao();
        EstadiaDao daoE = new EstadiaDao();
        ProprientarioDao daoP = new ProprientarioDao();
        
        p.setNome("Wilton");
        p.setContacto("888888");
        p.setEndereco("Matola,Mathlemele");
        
        v.setMarca("Toyota");
        v.setModelo("Corolla");
        v.setMatricula("ADM-123");
        v.setProprietario(p);
        
        //daoP.salvar(p);
        daoV.salvar(v);
        
    }
}
