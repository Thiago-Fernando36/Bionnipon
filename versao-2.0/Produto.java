package piClasses;

public class Produto {
    private int id;
    private String nome;
    private int quantidade;

    public Produto(int id, String nome, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public void darBaixa(int qtdUsada) {
        if (qtdUsada <= this.quantidade) {
            this.quantidade -= qtdUsada;
            System.out.println("Baixa realizada: " + qtdUsada + " unidades de " + nome);
        } else {
            System.out.println("Erro: Estoque insuficiente de " + nome);
        }
    }

    @Override
    public String toString() {
        return "Produto: " + nome + " | Estoque Atual: " + quantidade;
    }
}
