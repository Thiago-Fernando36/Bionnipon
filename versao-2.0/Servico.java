package piClasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Servico {
    private String descricao;
    private String clienteNome;
    private LocalDate dataExecucao;
    private int mesesGarantia;

    public Servico(String descricao, String clienteNome, LocalDate dataExecucao, int mesesGarantia) {
        this.descricao = descricao;
        this.clienteNome = clienteNome;
        this.dataExecucao = dataExecucao;
        this.mesesGarantia = mesesGarantia;
    }

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
}