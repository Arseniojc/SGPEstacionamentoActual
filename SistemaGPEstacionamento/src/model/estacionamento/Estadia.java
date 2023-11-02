package model.estacionamento;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import model.usuario.Atendente;

@Entity
@Table(name = "estadias")
public class Estadia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate data; 
    private LocalTime horaEntrada;
    private LocalTime horaSaida;
    @ManyToOne(cascade = CascadeType.ALL)
    private Veiculo veiculo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Vaga vaga;
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Atendente atendente;
    private float preco;

    public Estadia() {}

    public Estadia(Atendente atendente) {
        this.atendente = atendente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Atendente getAtendente() {
        return atendente;
    }
    
    public void registrarEntrada(Veiculo veiculo, Vaga vaga){
        this.setVeiculo(veiculo);
        this.data = LocalDate.now();
        this.setHoraEntrada(LocalTime.now());
        this.vaga = vaga;
    }
    
    public void registrarSaida() {
        this.setHoraSaida(LocalTime.now());
        vaga.liberarVaga();
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Estadia{" + "id=" + id + ", data=" + data + ", horaEntrada=" + horaEntrada + ", horaSaida=" + horaSaida + ", veiculo=" + veiculo + ", vaga=" + vaga + '}';
    }
}
