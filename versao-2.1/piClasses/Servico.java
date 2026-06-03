package piClasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Servico {
	private int idServico;
	private String dataInicio;
	private double valor;
	private String endereco;
    private String descricao;
    private String clienteNome;
    private LocalDate dataExecucao;
    private int mesesGarantia;

    
    public Servico() {
		
	}
    public Servico(String descricao, String clienteNome, LocalDate dataExecucao, int mesesGarantia, int idServico, String dataInicio, double valor, String endereco) {
        this.descricao = descricao;
        this.clienteNome = clienteNome;
        this.dataExecucao = dataExecucao;
        this.mesesGarantia = mesesGarantia;
        this.dataInicio = dataInicio;
        this.endereco = endereco;
        this.idServico = idServico;
        this.valor = valor;
    }
    public String toString() {
    	return "["+idServico+","+dataInicio+","+valor+","+endereco+","+descricao+","+clienteNome+","+dataExecucao+","+mesesGarantia+"]";
    }
    /*public double calcularValorTotal() {
    	
    }
    public void iniciaServico() {
    	
    }
    public void finalizaServico() {
    	
    }
    public Garantia emitirGarantia() {
    	
    }*/

    public boolean isGarantiaAtiva() {
        LocalDate dataVencimento = dataExecucao.plusMonths(mesesGarantia);
        return LocalDate.now().isBefore(dataVencimento);
    }

    public void exibirRelatorio() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String status = isGarantiaAtiva() ? "ATIVA" : "EXPIRADA";
        System.out.println("Serviço: " + descricao + " | Cliente: " + clienteNome + 
                           " | Data: " + dataExecucao.format(fmt) + " | Status: " + status);
    }
    
    public int getIdServico() {
		return idServico;
	}

	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public LocalDate getDataExecucao() {
		return dataExecucao;
	}

	public void setDataExecucao(LocalDate dataExecucao) {
		this.dataExecucao = dataExecucao;
	}

	public int getMesesGarantia() {
		return mesesGarantia;
	}

	public void setMesesGarantia(int mesesGarantia) {
		this.mesesGarantia = mesesGarantia;
	}

	
}