package telas;

import javax.swing.JFrame;

import view.GuiServico;

public class TelaServicoI extends JFrame{

	public TelaServicoI(){
		
		 setTitle("Cadastro de Cliente");
	     setSize(1000,800);
	     setLocationRelativeTo(null);
	     GuiServico p = new GuiServico();
	     add(p);
}
}