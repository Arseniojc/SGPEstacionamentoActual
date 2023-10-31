package Aplicacao;

import dao.EstadiaDao;
import dao.ProprientarioDao;
import dao.UsuarioDao;
import dao.VagaDao;
import dao.VeiculoDao;
import java.time.LocalDateTime;
import java.util.Date;
import model.Estadia;
import model.Proprietario;
import model.Usuario;
import model.Vaga;
import model.Veiculo;

public class Main {
    
    public static void main(String[] args) throws Exception{
        
        Veiculo v = new Veiculo();
        Estadia e = new Estadia();
        Vaga vg = new Vaga();
        Proprietario p = new Proprietario();
        EstadiaDao dao = new EstadiaDao();
        
        
        p.setNome("Arsenio");
        p.setEndereco("Matola");
        p.setContacto("88888");
       
        v.setMarca("Toyota");
        v.setModelo("Corollla");
        v.setProprietario(p);
        v.setMatricula("ADM-123");
        
        vg.setEndereco("aaaaaaa");
        
        e.setVeiculo(v);
        e.setHoraEntrada(new Date());
        e.setHoraSaida(new Date());
        e.setVaga(vg);
        
        dao.inserir(e);
        
    }
}
