package model.usuario;

public enum NivelDeAcesso {
    ADMINSTRADOR("admin"),
    ATENDENTE("atendente"),;
    
    public String nivel;
    
    NivelDeAcesso(String nivel) {
        this.nivel = nivel;
    }
}
