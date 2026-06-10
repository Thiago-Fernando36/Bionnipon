package telas;

import javax.swing.*;
import java.awt.Font;
import java.awt.SystemColor;

public class TelaMenu extends JFrame {

    public TelaMenu() {

        setTitle("Sistema");
        setSize(758,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        JButton servico = new JButton("Serviços");
        servico.setBounds(274, 151, 105, 21);
        JButton pedido = new JButton("Pedidos / Garantia");
        pedido.setBounds(490, 151, 160, 21);
        JButton estoque = new JButton("Estoque");
        estoque.setBounds(389, 151, 91, 21);
        painel.setLayout(null);
        JButton cliente = new JButton("Clientes");
        cliente.setBounds(34, 151, 98, 21);
        painel.add(cliente);
        cliente.addActionListener(e -> new TelaCliente().setVisible(true));
        painel.add(servico);
        
                JButton funcionario = new JButton("Funcionários");
                funcionario.setBounds(142, 151, 122, 21);
                
                        painel.add(funcionario);
                        
                                funcionario.addActionListener(e -> new TelaFuncionario().setVisible(true));
        painel.add(pedido);
        painel.add(estoque);

        getContentPane().add(painel);
        
        JLabel lblNewLabel = new JLabel("Bem vindo!");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        lblNewLabel.setBounds(284, 25, 201, 41);
        painel.add(lblNewLabel);
        servico.addActionListener(e -> new TelaServico().setVisible(true));
        pedido.addActionListener(e -> new TelaPedido().setVisible(true));
        estoque.addActionListener(e -> new TelaEstoque().setVisible(true));

    }
}
