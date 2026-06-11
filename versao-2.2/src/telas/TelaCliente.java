package telas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import view.GuiCliente;

public class TelaCliente extends JFrame {

    JTextField nome;
    JTextField telefone;

    public TelaCliente(){

        setTitle("Cadastro de Cliente");
        setSize(400,300);
        setLocationRelativeTo(null);

        GuiCliente p = new GuiCliente();

        p.add(new JLabel("Nome"));
        nome = new JTextField(20);
        p.add(nome);

        p.add(new JLabel("Telefone"));
        telefone = new JTextField(20);
        p.add(telefone);

        JButton salvar = new JButton("Cadastrar");
        JButton editar = new JButton("Editar");
        JButton excluir = new JButton("Excluir");

        p.add(salvar);
        p.add(editar);
        p.add(excluir);

        add(p);
    }
}