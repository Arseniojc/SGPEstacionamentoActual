package Aplicacao;

import dao.estacionamento.EstadiaDao;
import dao.estacionamento.ProprientarioDao;
import dao.usuario.UsuarioDao;
import dao.estacionamento.VagaDao;
import dao.estacionamento.VeiculoDao;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import model.estacionamento.Disponibilidade;
import model.estacionamento.Estadia;
import model.estacionamento.Proprietario;
import model.usuario.Usuario;
import model.estacionamento.Vaga;
import model.estacionamento.Veiculo;
import model.usuario.Atendente;
import model.usuario.NivelDeAcesso;

public class Main {

    public static void main(String[] args) throws Exception {
//        Proprietario p = new Proprietario();
//        p.setNome("Arsenio");
//        p.setEndereco("Matola");
//        p.setContacto("88888");
////
//        Veiculo v = new Veiculo();
//        v.setMarca("Toyota");
//        v.setModelo("Corola");
//        v.setMatricula("ADM-123");
//        v.setProprietario(p);
//
//        Vaga vg = new Vaga();
//        vg.setEndereco("aaaaaaa");
//        
//        Atendente a = new Atendente();
//        a.setNome("carla");
////
//        System.out.println(a);
//        Estadia e = new Estadia(a);
//
//        e.registrarEntrada(v, vg);
//        e.registrarSaida();
//
//        EstadiaDao dao = new EstadiaDao();
//        dao.inserir(e);

//        VagaDao dao = new VagaDao();
//        List<Vaga> vagas = dao.pesquisarPorDisponibilidade(Disponibilidade.DISPONIVEL);
//        System.out.println(vagas);
            
           UsuarioDao udao = new UsuarioDao();
           
//           List<Usuario> usuarios = udao.listar();
           
           Usuario u = udao.pesquisarPorNomeESenha("carla", "1111");
           
           System.out.println(u);
    }
}
