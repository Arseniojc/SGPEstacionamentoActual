package model.usuario;

import javax.persistence.Entity;

@Entity
public class Atendente extends Usuario {
    public Atendente() {
        super(NivelDeAcesso.ATENDENTE);
    }
}
