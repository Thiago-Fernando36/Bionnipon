package piClasses;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Servico {
    
    // 1. ATRIBUTOS PRIVADOS (Mapeados com as colunas reais da tabela 'Servico' do banco)
    private int idServico;
    private int idUsuario; // FK para a tabela Cliente
    private String dataInicio; // Pode ser alterado para LocalDateTime se desejar usar o TIMESTAMP do banco
    private String dataFim;    // Substitui a antiga 'dataExecucao'
    private double valor;
    private String endereco;
    private String cidade;
    private String cep;

    // Atributo auxiliar para manter a lógica de garantia que você tinha
    private int mesesGarantia;

    // 2. CONSTRUTOR VAZIO
    public Servico() {
    }

    // 3. CONSTRUTOR COMPLETO
    public Servico(int idServico, int idUsuario, String dataInicio, String dataFim, double valor, String endereco, String cidade, String cep) {
        this.idServico = idServico;
        this.idUsuario = idUsuario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valor = valor;
        this.endereco = endereco;
        this.cidade = cidade;
        this.cep = cep;
    }

    // 4. MÉTODOS DE REGRA DE NEGÓCIO (Ajustados para usar a nova estrutura de dados)
    
    public boolean isGarantiaAtiva() {
        if (dataFim == null || dataFim.trim().isEmpty()) {
            return false; // Serviço nem foi finalizado ainda
        }
        try {
            // Converte a string dataFim (no formato AAAA-MM-DD ou semelhante) para LocalDate
            // Se o seu banco salvar com hora (TIMESTAMP), ajuste o parse.
            LocalDate fim = LocalDate.parse(dataFim.substring(0, 10)); 
            LocalDate dataVencimento = fim.plusMonths(mesesGarantia);
            return LocalDate.now().isBefore(dataVencimento);
        } catch (Exception e) {
            return false;
        }
    }

    public void exibirRelatorio() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String status = isGarantiaAtiva() ? "ATIVA" : "EXPIRADA";
        System.out.println("Serviço ID: " + idServico + " | ID Cliente: " + idUsuario + 
                           " | Finalizado em: " + dataFim + " | Status Garantia: " + status);
    }

    // 5. GETTERS E SETTERS
    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCEP() {
        return cep;
    }

    public void setCEP(String cep) {
        this.cep = cep;
    }

    public int getMesesGarantia() {
        return mesesGarantia;
    }

    public void setMesesGarantia(int mesesGarantia) {
        this.mesesGarantia = mesesGarantia;
    }

    // 6. TO STRING
    @Override
    public String toString() {
        return "[" + idServico + "," + idUsuario + "," + dataInicio + "," + dataFim + "," + valor + "," + endereco + "," + cidade + "," + cep + "]";
    }
}