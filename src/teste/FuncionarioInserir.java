package teste;

import piClasses.Funcionario;
import piClasses.FuncionarioDAO;

public class FuncionarioInserir {

	public static void main(String[] args) {
		
		Funcionario f = new Funcionario(101,"João Rodrigo l", "12345679901", "99969999", "Rua A", "SP", "São Paulo", "01300000", "joao123", "senhaSegura",
				"Auxiliar",4000,"5x2","45h/semana");
		FuncionarioDAO dao = new FuncionarioDAO();
		System.out.println(dao.salvar(f));
	}

}
