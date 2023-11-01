package model.estacionamento;

public enum Disponibilidade {
    OCUPADA("ocupada"),
    DISPONIVEL("disponivel"),
    INDISPONIVEL("indisponivel"),;
    
    public String valor;
    
    Disponibilidade(String valor) {
        this.valor = valor;
    }
}
