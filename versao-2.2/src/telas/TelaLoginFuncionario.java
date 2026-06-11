package telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLoginFuncionario extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    public TelaLoginFuncionario() {

        setTitle("Login do Funcionário");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(null);

        JLabel titulo = new JLabel("Login do Funcionário");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(150, 20, 250, 30);
        painel.add(titulo);

        JLabel usuario = new JLabel("Usuário:");
        usuario.setBounds(80, 100, 80, 25);
        painel.add(usuario);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(150, 100, 200, 25);
        painel.add(campoUsuario);

        JLabel senha = new JLabel("Senha:");
        senha.setBounds(80, 150, 80, 25);
        painel.add(senha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(150, 150, 200, 25);
        painel.add(campoSenha);

        JButton botaoLogin = new JButton("Entrar");
        botaoLogin.setBounds(150, 200, 90, 30);
        painel.add(botaoLogin);

        JButton botaoSair = new JButton("Sair");
        botaoSair.setBounds(260, 200, 90, 30);
        painel.add(botaoSair);

        botaoLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());

                if (usuario.equals("admin") && senha.equals("1234")) {
                	
                	 new TelaMenu().setVisible(true);
                     dispose();
                    //JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");

                } else {

                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");

                }
            }
        });

        botaoSair.addActionListener(e -> System.exit(0));

        add(painel);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TelaLoginFuncionario().setVisible(true);
        });

    }
}