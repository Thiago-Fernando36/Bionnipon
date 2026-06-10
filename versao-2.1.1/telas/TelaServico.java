package telas;

import javax.swing.*;

public class TelaServico extends JFrame {

    JTextField nomeServico;
    JTextField preco;
    JTextField id;

    public TelaServico(){

        setTitle("Cadastro de Serviço");
        setSize(400,300);
        setLocationRelativeTo(null);

        JPanel p = new JPanel();

        p.add(new JLabel("Serviço"));
        nomeServico = new JTextField(20);
        p.add(nomeServico);

        p.add(new JLabel("Preço"));
        preco = new JTextField(10);
        p.add(preco);
        
        p.add(new JLabel("ID"));
        id = new JTextField(20);
        p.add(id);

        JButton salvar = new JButton("Cadastrar");
        salvar.addActionListener(e ->
        JOptionPane.showMessageDialog(null,"Serviço cadastrado"));
        JButton editar = new JButton("Editar");
        editar.addActionListener(e ->
        JOptionPane.showMessageDialog(null,"Serviço editado"));
        JButton excluir = new JButton("Excluir");
        excluir.addActionListener(e ->
        JOptionPane.showMessageDialog(null,"Serviço excluido"));

        p.add(salvar);
        p.add(editar);
        p.add(excluir);

        add(p);
    }
}