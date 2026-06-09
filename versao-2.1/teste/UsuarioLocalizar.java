package teste;

import piClasses.UsuarioDAO;

public class UsuarioLocalizar {
	
	public static void main(String[] args) {
	
		UsuarioDAO dao = new UsuarioDAO();
		System.out.println(dao.localizar(100));
}
}
