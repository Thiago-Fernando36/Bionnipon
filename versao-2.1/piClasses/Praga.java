package piClasses;

public class Praga {

    private int idPraga;
    private String nomeTipo;
    private String veneno;
    private double valor;
    
    public Praga() {
        
    }
    
    public Praga(int idPraga, String nomeTipo, String veneno, double valor) {
        this.idPraga = idPraga;
        this.nomeTipo = nomeTipo;
        this.veneno = veneno;
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return "[" + idPraga + "," + nomeTipo + "," + veneno + "," + valor + "]";
    }
    
    public int getIdPraga() {
        return idPraga;
    }
    
    public void setIdPraga(int idPraga) {
        this.idPraga = idPraga;
    }
    
    public String getNomeTipo() {
        return nomeTipo;
    }
    
    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
    
    public String getVeneno() {
        return veneno;
    }
    
    public void setVeneno(String veneno) {
        this.veneno = veneno;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
}