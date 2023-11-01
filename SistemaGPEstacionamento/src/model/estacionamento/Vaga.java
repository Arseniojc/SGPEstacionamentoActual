package model.estacionamento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vagas")
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String endereco;
    private String disponibilidade;

    public Vaga(){
        liberarVaga();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public void ocuparVaga() {
        disponibilidade = Disponibilidade.OCUPADA.valor;
    }
    
    public void liberarVaga() {
        disponibilidade = Disponibilidade.DISPONIVEL.valor;
    }
    
    public void fecharVaga() {
        disponibilidade = Disponibilidade.INDISPONIVEL.valor;
    }

    @Override
    public String toString() {
        return "Vaga{" + "id=" + id + ", endereco=" + endereco + ", disponibilidade=" + disponibilidade + '}';
    }
}
