package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import banco.BD;
import banco.MyTableModel;
import piClasses.Servico;
import piClasses.ServicoDAO;

public class GuiServico extends JPanel {
    private JButton btNovo, btSalvar, btBuscar, btExcluir;
    
    // Labels atualizadas conforme as colunas reais da tabela do banco de dados
    private JLabel lbIdServico, lbIdUsuario, lbDataInicio, lbDataFim, lbValor, lbEndereco, lbCidade, lbCep;
    
    // TextFields declarados corretamente como atributos da classe
    private JTextField tfIdServico, tfIdUsuario, tfDataInicio, tfDataFim, tfValor, tfEndereco, tfCidade, tfCep;
    private JTextField tfLocalizar;
    
    private ServicoDAO dao; 
    private Servico servico;
    private BD bd;
    
    private JTable table;
    private DefaultTableModel model;

    public GuiServico() {
        dao = new ServicoDAO(); 
        servico = new Servico(); 
        bd = new BD();
        bd.getConnection();

        inicializarComponentes();
        definirEventos();
    }

    public void inicializarComponentes() {
        btNovo = new JButton("Novo");
        btSalvar = new JButton("Salvar");
        btBuscar = new JButton("Buscar");
        btExcluir = new JButton("Excluir");
        
        // Inicialização dos componentes mapeados com a tabela do banco
        lbIdServico = new JLabel("Id Serviço");
        lbIdUsuario = new JLabel("Id Cliente (User)");
        lbDataInicio = new JLabel("Data Início");
        lbDataFim = new JLabel("Data Fim");
        lbValor = new JLabel("Valor (R$)");
        lbEndereco = new JLabel("Endereço");
        lbCidade = new JLabel("Cidade");
        lbCep = new JLabel("CEP");
        
        tfIdServico = new JTextField(5);
        tfIdUsuario = new JTextField(5);
        tfDataInicio = new JTextField(15);
        tfDataFim = new JTextField(15);
        tfValor = new JTextField(10);
        tfEndereco = new JTextField(30);
        tfCidade = new JTextField(20);
        tfCep = new JTextField(8);
        tfLocalizar = new JTextField(10);
        
        table = new JTable();

        setPreferredSize(new Dimension(814, 530));
        setLayout(null);

        // Adicionando componentes na janela
        add(btNovo); add(btSalvar); add(btBuscar); add(btExcluir);
        add(lbIdServico); add(lbIdUsuario); add(lbDataInicio); add(lbDataFim);
        add(lbValor); add(lbEndereco); add(lbCidade); add(lbCep);
        add(tfIdServico); add(tfIdUsuario); add(tfDataInicio); add(tfDataFim);
        add(tfValor); add(tfEndereco); add(tfCidade); add(tfCep);
        add(tfLocalizar);

        // Posicionamento dos Botões Laterais
        btBuscar.setBounds(676, 15, 75, 25);
        btNovo.setBounds(676, 47, 75, 20);
        btSalvar.setBounds(671, 93, 80, 20);
        btExcluir.setBounds(661, 123, 90, 20);

        // Layout Posicionamento Coluna 1
        lbIdServico.setBounds(5, 15, 80, 25);
        tfIdServico.setBounds(110, 15, 50, 25);
        
        lbIdUsuario.setBounds(5, 45, 100, 25);
        tfIdUsuario.setBounds(110, 45, 50, 25);
        
        lbDataInicio.setBounds(5, 75, 80, 25);
        tfDataInicio.setBounds(110, 75, 140, 25);
        
        lbDataFim.setBounds(5, 105, 80, 25);
        tfDataFim.setBounds(110, 105, 140, 25);

        // Layout Posicionamento Coluna 2
        lbValor.setBounds(310, 15, 80, 25);
        tfValor.setBounds(400, 15, 100, 25);
        
        lbEndereco.setBounds(310, 45, 80, 25);
        tfEndereco.setBounds(400, 45, 200, 25);
        
        lbCidade.setBounds(310, 75, 80, 25);
        tfCidade.setBounds(400, 75, 150, 25);
        
        lbCep.setBounds(310, 105, 80, 25);
        tfCep.setBounds(400, 105, 100, 25);

        // Tabela inferior
        atualizarGrade();
        JScrollPane scroller = new JScrollPane(table);
        scroller.setBounds(24, 300, 768, 200);
        add(scroller);
        
        tfLocalizar.setBounds(24, 270, 200, 20);
        add(tfLocalizar);
        
        JLabel lblAviso = new JLabel("Filtrar por código do cliente (id_usuario):");
        lblAviso.setBounds(24, 250, 250, 15);
        add(lblAviso);
    }

    private void atualizarGrade() {
        try {
            model = MyTableModel.getModel(bd, "select id_servico, id_usuario, data_inicio, valor from servico");
            if (model != null) {
                table.setModel(model);
                ajustarGrade();
            } else {
                criarGradeVazia();
            }
        } catch (Exception e) {
            criarGradeVazia();
        }
    }

    private void criarGradeVazia() {
        String[] colunas = {"Id Serviço", "Id Usuário", "Data Início", "Valor"};
        model = new DefaultTableModel(colunas, 0);
        table.setModel(model);
    }

    private void ajustarGrade() {
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getTableHeader().setReorderingAllowed(false);
    }

    private void limparCampos() {
        tfIdServico.setText("");
        tfIdUsuario.setText("");
        tfDataInicio.setText("");
        tfDataFim.setText("");
        tfValor.setText("");
        tfEndereco.setText("");
        tfCidade.setText("");
        tfCep.setText("");
        tfIdServico.requestFocus();
    }

    public void definirEventos() {
        
        // Clique na tabela joga os dados de volta para as caixas de texto (TextFields)
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = table.getSelectedRow();
                int id = Integer.parseInt(String.valueOf(table.getValueAt(linha, 0)));
                servico = dao.localizar(id);
                if (servico != null) {
                    tfIdServico.setText(String.valueOf(servico.getIdServico()));
                    tfIdUsuario.setText(String.valueOf(servico.getIdUsuario()));
                    tfDataInicio.setText(servico.getDataInicio());
                    tfDataFim.setText(servico.getDataFim());
                    tfValor.setText(String.valueOf(servico.getValor()));
                    tfEndereco.setText(servico.getEndereco());
                    tfCidade.setText(servico.getCidade());
                    tfCep.setText(servico.getCEP());
                }
            }
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
        });
        
        // Botão Salvar (Insere ou Atualiza caso já exista o ID)
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    servico.setIdServico(Integer.parseInt(tfIdServico.getText()));
                    servico.setIdUsuario(Integer.parseInt(tfIdUsuario.getText()));
                    servico.setDataInicio(tfDataInicio.getText());
                    servico.setDataFim(tfDataFim.getText());
                    servico.setValor(Double.parseDouble(tfValor.getText()));
                    servico.setEndereco(tfEndereco.getText());
                    servico.setCidade(tfCidade.getText());
                    servico.setCEP(tfCep.getText());

                    JOptionPane.showMessageDialog(btSalvar, dao.salvar(servico));
                    atualizarGrade();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(btSalvar, "Verifique se ID Serviço, ID Usuário e Valor estão devidamente preenchidos com números!");
                }
            }
        });
        
        // Botão Excluir
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfIdServico.getText().isEmpty()) {
                    int r = JOptionPane.showConfirmDialog(btExcluir, "Deseja excluir permanentemente este serviço?");
                    if (r == 0) {
                        servico.setIdServico(Integer.parseInt(tfIdServico.getText()));
                        JOptionPane.showMessageDialog(btExcluir, dao.excluir(servico));
                        limparCampos();
                        atualizarGrade();
                    }
                } else {
                    JOptionPane.showMessageDialog(btExcluir, "Insira ou busque um ID para poder excluir!");
                }
            }
        });
        
        // Botão Novo (Limpa a tela para digitação)
        btNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        
        // Botão Buscar por Código do Serviço
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(tfIdServico.getText());
                    servico = dao.localizar(id);
                    
                    if (servico != null) {
                        tfIdUsuario.setText(String.valueOf(servico.getIdUsuario()));
                        tfDataInicio.setText(servico.getDataInicio());
                        tfDataFim.setText(servico.getDataFim());
                        tfValor.setText(String.valueOf(servico.getValor()));
                        tfEndereco.setText(servico.getEndereco());
                        tfCidade.setText(servico.getCidade());
                        tfCep.setText(servico.getCEP());
                    } else {
                        JOptionPane.showMessageDialog(btBuscar, "Serviço não encontrado!");
                        limparCampos();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(btBuscar, "Digite um número de ID válido para pesquisar!");
                }
            }
        });
        
        // Filtro em tempo real por código do cliente (id_usuario) conforme digita
        tfLocalizar.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    String filtro = tfLocalizar.getText();
                    String sql;
                    if(filtro.trim().isEmpty()) {
                        sql = "select id_servico, id_usuario, data_inicio, valor from servico";
                    } else {
                        // Como id_usuario é numérico (INT), fazemos um CAST no PostgreSQL para usar o operador LIKE
                        sql = "select id_servico, id_usuario, data_inicio, valor from servico where cast(id_usuario as text) like '" + filtro + "%'";
                    }
                    DefaultTableModel modeloBusca = MyTableModel.getModel(bd, sql);
                    if(modeloBusca != null) {
                        model = modeloBusca;
                        table.setModel(model);
                        ajustarGrade();
                    }
                } catch (Exception erro) {}
            }
            @Override public void keyTyped(KeyEvent e) {}
            @Override public void keyPressed(KeyEvent e) {}
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gerenciamento de Serviços - BioNippon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GuiServico());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}