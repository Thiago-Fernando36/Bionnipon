package telas;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TelaMenu extends JFrame {

    public TelaMenu() {

        setTitle("Sistema");
        setSize(758,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        JButton servico = new JButton("Serviços");
        servico.setBounds(397, 151, 105, 21);
        JButton praga = new JButton("Pragas");
        praga.setBounds(539, 151, 122, 21);
        painel.setLayout(null);
        JButton cliente = new JButton("Clientes");
        cliente.setBounds(107, 151, 98, 21);
        painel.add(cliente);
        cliente.addActionListener(e -> new TelaClienteI().setVisible(true));
        painel.add(servico);
        
                JButton funcionario = new JButton("Funcionários");
                funcionario.setBounds(241, 151, 122, 21);
                
                        painel.add(funcionario);
                        
                                funcionario.addActionListener(e -> new TelaFuncionarioI().setVisible(true));
        painel.add(praga);

        getContentPane().add(painel);
        
        JLabel lblNewLabel = new JLabel("Bem vindo!");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 36));
        lblNewLabel.setBounds(284, 25, 201, 41);
        painel.add(lblNewLabel);
        //servico.addActionListener(e -> new GuiServico().setVisible(true));
        praga.addActionListener(e -> new TelaPragaI().setVisible(true));

        servico.addActionListener(e -> new TelaServicoI().setVisible(true));
    }
}
