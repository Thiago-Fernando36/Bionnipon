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
import piClasses.Praga;
import piClasses.PragaDAO;

public class GuiPraga extends JPanel {
    private JButton btNovo, btSalvar, btBuscar, btExcluir;
    
    private JLabel lbIdPraga, lbNomeTipo, lbVeneno, lbValor;
    private JTextField tfIdPraga, tfNomeTipo, tfVeneno, tfValor;
    private JTextField tfLocalizar;
    
    private PragaDAO dao; 
    private Praga praga;
    private BD bd;
    
    private JTable table;
    private DefaultTableModel model;

    public GuiPraga() {
        dao = new PragaDAO(); 
        praga = new Praga(); 
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
        
        lbIdPraga = new JLabel("Id Praga");
        lbNomeTipo = new JLabel("Nome/Tipo");
        lbVeneno = new JLabel("Veneno Utilizado");
        lbValor = new JLabel("Preço Base (R$)");
        
        tfIdPraga = new JTextField(5);
        tfNomeTipo = new JTextField(30);
        tfVeneno = new JTextField(30);
        tfValor = new JTextField(10);
        tfLocalizar = new JTextField(15);
        
        table = new JTable();

        setPreferredSize(new Dimension(814, 530));
        setLayout(null);

        add(btNovo); add(btSalvar); add(btBuscar); add(btExcluir);
        add(lbIdPraga); add(lbNomeTipo); add(lbVeneno); add(lbValor);
        add(tfIdPraga); add(tfNomeTipo); add(tfVeneno); add(tfValor);
        add(tfLocalizar);

        btBuscar.setBounds(676, 15, 75, 25);
        btNovo.setBounds(676, 47, 75, 20);
        btSalvar.setBounds(671, 93, 80, 20);
        btExcluir.setBounds(661, 123, 90, 20);

        lbIdPraga.setBounds(5, 15, 80, 25);
        tfIdPraga.setBounds(110, 15, 60, 25);
        
        lbNomeTipo.setBounds(5, 45, 100, 25);
        tfNomeTipo.setBounds(110, 45, 180, 25);

        lbVeneno.setBounds(310, 15, 110, 25);
        tfVeneno.setBounds(430, 15, 180, 25);
        
        lbValor.setBounds(310, 45, 110, 25);
        tfValor.setBounds(430, 45, 100, 25);

        atualizarGrade();
        JScrollPane scroller = new JScrollPane(table);
        scroller.setBounds(24, 300, 768, 200);
        add(scroller);
        
        tfLocalizar.setBounds(24, 270, 200, 20);
        add(tfLocalizar);
        
        JLabel lblAviso = new JLabel("Filtrar por nome/tipo da praga:");
        lblAviso.setBounds(24, 250, 200, 15);
        add(lblAviso);
    }

    private void atualizarGrade() {
		try {
			// Proteção 1: Limpa qualquer seleção residual para não confundir o driver
			table.clearSelection();
			
			// Nova SQL trazendo todas as colunas e com nomes amigáveis para a tabela
			String sql = "SELECT id_praga AS \"ID\", nome_tipo AS \"Nome/Tipo\", veneno AS \"Veneno\", valor AS \"Valor (R$)\" FROM pragas ORDER BY id_praga";
			DefaultTableModel modeloBusca = MyTableModel.getModel(bd, sql);
			
			if (modeloBusca != null) {
				model = modeloBusca;
				table.setModel(model);
				ajustarGrade();
			} else {
				criarGradeVazia();
			}
		} catch (Exception e) {
			System.out.println("Erro ao atualizar grade: " + e.getMessage());
			criarGradeVazia();
		}
	}

	private void criarGradeVazia() {
		// Adicionadas as novas colunas na estrutura vazia
		String[] colunas = {"ID", "Nome/Tipo", "Veneno", "Valor (R$)"};
		model = new DefaultTableModel(colunas, 0);
		table.setModel(model);
	}

	private void ajustarGrade() {
		// Definindo o tamanho proporcional para as 4 colunas
		table.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
		table.getColumnModel().getColumn(1).setPreferredWidth(200);  // Nome/Tipo
		table.getColumnModel().getColumn(2).setPreferredWidth(200);  // Veneno
		table.getColumnModel().getColumn(3).setPreferredWidth(80);   // Valor
		
		table.getTableHeader().setReorderingAllowed(false);
	}

    private void limparCampos() {
        tfIdPraga.setText("");
        tfNomeTipo.setText("");
        tfVeneno.setText("");
        tfValor.setText("");
        tfIdPraga.requestFocus();
    }

    public void definirEventos() {
        
        // Proteção 2: Tratamento completo no clique da tabela para ignorar ponteiros soltos
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int linha = table.getSelectedRow();
                    if (linha >= 0) {
                        int id = Integer.parseInt(String.valueOf(table.getValueAt(linha, 0)));
                        praga = dao.localizar(id);
                        if (praga != null) {
                            tfIdPraga.setText(String.valueOf(praga.getIdPraga()));
                            tfNomeTipo.setText(praga.getNomeTipo());
                            tfVeneno.setText(praga.getVeneno());
                            tfValor.setText(String.valueOf(praga.getValor()));
                        }
                    }
                } catch (Exception erro) {
                    System.out.println("Aviso: Clique na linha tratado para evitar estouro de ResultSet.");
                }
            }
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
        });
        
        btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (tfNomeTipo.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(btSalvar, "O campo Nome/Tipo não pode ficar em branco!");
						return;
					}
					
					// Verifica se o campo ID está vazio para um novo cadastro
					if (tfIdPraga.getText().trim().isEmpty()) {
						praga.setIdPraga(0);
					} else {
						praga.setIdPraga(Integer.parseInt(tfIdPraga.getText().trim()));
					}
					
					praga.setNomeTipo(tfNomeTipo.getText());
					praga.setVeneno(tfVeneno.getText());
					
					// Previne erro se o usuário digitar o valor com vírgula (ex: 50,50) ou vazio
					try {
						praga.setValor(Double.parseDouble(tfValor.getText().replace(",", ".")));
					} catch (NumberFormatException ex) {
						praga.setValor(0.0);
					}

					JOptionPane.showMessageDialog(btSalvar, dao.salvar(praga));
					atualizarGrade();
					limparCampos(); // Limpa os campos após salvar com sucesso
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(btSalvar, "Verifique os dados preenchidos: " + ex.getMessage());
				}
			}
		});
        
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfIdPraga.getText().isEmpty()) {
                    int r = JOptionPane.showConfirmDialog(btExcluir, "Deseja excluir permanentemente esta praga?");
                    if (r == 0) {
                        praga.setIdPraga(Integer.parseInt(tfIdPraga.getText()));
                        JOptionPane.showMessageDialog(btExcluir, dao.excluir(praga));
                        limparCampos();
                        atualizarGrade();
                    }
                } else {
                    JOptionPane.showMessageDialog(btExcluir, "Insira ou busque um ID para poder excluir!");
                }
            }
        });
        
        btNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        
        btBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Evita que o programa quebre se o usuário clicar em buscar com o campo vazio
				if (tfIdPraga.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(btBuscar, "Digite um ID de praga para buscar.");
					return;
				}
				
				try {
					int id = Integer.parseInt(tfIdPraga.getText().trim());
					praga = dao.localizar(id);
					
					if (praga != null) {
						tfNomeTipo.setText(praga.getNomeTipo());
						tfVeneno.setText(praga.getVeneno());
						tfValor.setText(String.valueOf(praga.getValor()));
					} else {
						JOptionPane.showMessageDialog(btBuscar, "Praga não encontrada!");
						limparCampos();
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(btBuscar, "Digite um número de ID válido para pesquisar!");
				}
			}
		});
        
        tfLocalizar.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String filtro = tfLocalizar.getText();
					String sql;
					if (filtro.trim().isEmpty()) {
						sql = "SELECT id_praga AS \"ID\", nome_tipo AS \"Nome/Tipo\", veneno AS \"Veneno\", valor AS \"Valor (R$)\" FROM pragas ORDER BY id_praga";
					} else {
						sql = "SELECT id_praga AS \"ID\", nome_tipo AS \"Nome/Tipo\", veneno AS \"Veneno\", valor AS \"Valor (R$)\" FROM pragas WHERE lower(nome_tipo) LIKE '" + filtro.toLowerCase() + "%' ORDER BY id_praga";
					}
					DefaultTableModel modeloBusca = MyTableModel.getModel(bd, sql);
					if (modeloBusca != null) {
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
        JFrame frame = new JFrame("Gerenciamento de Pragas - BioNippon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GuiPraga());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}