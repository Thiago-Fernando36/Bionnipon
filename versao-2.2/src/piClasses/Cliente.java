package piClasses;

//import java.util.List;

public class Cliente extends Usuario {
    private boolean fidelidade;

	public Cliente() {
		
	}
    public Cliente(int id_usuario, String nome, String cpf, String telefone, String rua, String estado, String cidade, String cep, String login, String senha, boolean fidelidade) {
        super(id_usuario, nome, cpf, telefone, rua, estado, cidade, cep, login, senha);
        this.fidelidade = fidelidade;
    }
    
    public String toString() {
    	return "["+id_usuario+","+nome+","+cpf+","+telefone+","+rua+","+estado+","+cidade+","+cep+","+login+","+senha+","+fidelidade+"]";
    }
    
    /*public void solicitarOrcamento() {
    	
    }
    public List<Servico> consultarHistoricoServicos(){
    	
    	
    }*/
	public boolean isFidelidade() {
		return fidelidade;
	}
	public void setFidelidade(boolean fidelidade) {
		this.fidelidade = fidelidade;
	}
    public boolean getFidelidade() {
    	return fidelidade;
    }
    
}