package telas;

import javax.swing.JFrame;

import view.GuiCliente;

public class TelaClienteI extends JFrame{

	public TelaClienteI(){
		
		 setTitle("Cadastro de Cliente");
	     setSize(1000,800);
	     setLocationRelativeTo(null);
	     GuiCliente p = new GuiCliente();
	     add(p);
}
}