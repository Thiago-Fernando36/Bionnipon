package teste;

import piClasses.FuncionarioDAO;

public class FuncionarioLocalizar {

	public static void main(String[] args) {

		FuncionarioDAO dao = new FuncionarioDAO();
		System.out.println(dao.localizar(101));

	}

}
