package piClasses;

public class Funcionario extends Usuario {
    private String cargo;

    public Funcionario(int id, String nome, String login, String senha, String cargo) {
        super(id, nome, login, senha);
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Funcionário: " + nome + " | Cargo: " + cargo;
    }
}