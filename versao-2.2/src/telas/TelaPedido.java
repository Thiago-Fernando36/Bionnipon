package telas;

import javax.swing.*;

public class TelaPedido extends JFrame {

    public TelaPedido(){

        setTitle("Pedidos e Garantia");
        setSize(400,300);
        setLocationRelativeTo(null);

        JPanel p = new JPanel();

        JTextField servico = new JTextField(15);
        JTextField garantia = new JTextField(10);

        JButton pedido = new JButton("Fazer Pedido");

        p.add(new JLabel("Serviço"));
        p.add(servico);

        p.add(new JLabel("Garantia (dias)"));
        p.add(garantia);

        p.add(pedido);

        add(p);
    }
}