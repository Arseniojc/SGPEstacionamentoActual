
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Estadia")
public class Estadia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate data; 
    @Temporal(TemporalType.TIME)
    private Date horaEntrada;
    @Temporal(TemporalType.TIME)
    private Date horaSaida;
    @ManyToOne(cascade = CascadeType.ALL)
    private Veiculo veiculo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Vaga vaga;

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

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Date horaSaida) {
        this.horaSaida = horaSaida;
    }


    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
    
    public void registrarEntrada(Veiculo veiculo){
        this.setVeiculo(veiculo);
        this.data = LocalDate.now();
        this.setHoraEntrada(new Date());
    }
    
    public void registrarSaida() {
        this.setHoraSaida(new Date());
    }
    
}
