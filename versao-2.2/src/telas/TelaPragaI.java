package telas;

import javax.swing.JFrame;

import view.GuiPraga;

public class TelaPragaI extends JFrame{

	public TelaPragaI(){
		
		 setTitle("Cadastro de Cliente");
	     setSize(1000,800);
	     setLocationRelativeTo(null);
	     GuiPraga p = new GuiPraga();
	     add(p);
}
}