package telas;

import javax.swing.JFrame;

import view.GuiFuncionario;

public class TelaFuncionarioI extends JFrame{

	public TelaFuncionarioI(){
		
		 setTitle("Cadastro de Cliente");
	     setSize(1000,800);
	     setLocationRelativeTo(null);
	     GuiFuncionario p = new GuiFuncionario();
	     add(p);
}
}