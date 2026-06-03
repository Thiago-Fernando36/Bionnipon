package piClasses;

public class Cliente extends Usuario {
    private String cpfCnpj;
    private String telefone;
    private String endereco;

    public Cliente(int id, String nome, String login, String senha, String cpfCnpj, String telefone, String endereco) {
        super(id, nome, login, senha);
        this.cpfCnpj = cpfCnpj;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    
    public String getNome() {
        return "Cliente: " + nome + " | CPF/CNPJ: " + cpfCnpj + " | Tel: " + telefone;
    }
}