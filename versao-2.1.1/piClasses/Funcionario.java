package piClasses;

public class Funcionario extends Usuario {
    private String cargo;
    private double salario;
    private String escala;
    private String cargaHoraria;

    public Funcionario() {
		
	}
    public Funcionario(int idUsuario, String nome, String cpf, String telefone, String rua, String estado, String cidade, String cep, String login, String senha, String cargo, double salario, String escala, String cargaHoraria) {
        super(idUsuario, nome, cpf, telefone, rua, estado, cidade, cep, login, senha);
        this.cargo = cargo;
        this.salario = salario;
        this.escala = escala;
        this.cargaHoraria = cargaHoraria;
    }
   
    public String toString() {
    	return "["+idUsuario+","+nome+","+cpf+","+telefone+","+rua+","+estado+","+cidade+","+cep+","+login+","+senha+","+cargo+","+salario+","+escala+","+cargaHoraria+"]";
    }
    public void agendarServico(Servico servico) {
    	
    }
    public void atualizarStatusServico(int idServico, String status) {
    	
    }
    public void emitirOrdemServico(int idServico) {
    	
    }
    public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getEscala() {
		return escala;
	}

	public void setEscala(String escala) {
		this.escala = escala;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
}