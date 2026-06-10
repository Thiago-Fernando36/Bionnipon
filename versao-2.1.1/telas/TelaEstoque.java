package telas;

import javax.swing.*;

public class TelaEstoque extends JFrame {

    public TelaEstoque(){

        setTitle("Controle de Estoque");
        setSize(400,300);
        setLocationRelativeTo(null);

        JPanel p = new JPanel();

        JTextField produto = new JTextField(15);
        JTextField quantidade = new JTextField(5);

        JButton adicionar = new JButton("Adicionar");
        JButton remover = new JButton("Remover");

        p.add(new JLabel("Produto"));
        p.add(produto);

        p.add(new JLabel("Quantidade"));
        p.add(quantidade);

        p.add(adicionar);
        p.add(remover);

        add(p);
    }
}