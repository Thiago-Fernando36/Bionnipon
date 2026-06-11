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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import banco.BD;
import banco.MyTableModel;
import piClasses.Funcionario;
import piClasses.FuncionarioDAO;


public class GuiFuncionario extends JPanel {
	private JButton btNovo;
	private JButton btSalvar;
	private JButton btBuscar;
	private JLabel lbCodigo;
	private JLabel lbNome;
	private JLabel lbCpf;
	private JLabel lbTelefone;
	private JButton btExcluir;
	private JTextField tfIdUsuario;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTextField tfTelefone;
	private FuncionarioDAO dao; 
	private Funcionario f;

	private JTextField tfLocalizar;
	private JTable table;
	private DefaultTableModel model;
	private BD bd;
	private JTextField tfRua;
	private JTextField tfCidade;
	private JTextField tfEstado;
	private JTextField tfCep;
	private JTextField tfLogin;
	private JTextField tfSenha;
	private JTextField tfCargo;
	private JTextField tfSalario;
	private JTextField tfEscala;
	private JTextField tfCargaHora;


	public GuiFuncionario() {
		dao  = new FuncionarioDAO(); 
		f  = new Funcionario(); 
		bd = new BD();
		bd.getConnection();

		inicializarComponentes();
		definirEventos();
	}

	public void inicializarComponentes() {
		btNovo = new JButton ("Novo");
		btSalvar = new JButton ("Salvar");
		btBuscar = new JButton ("Buscar");
		lbCodigo = new JLabel ("IdUsuario");
		lbNome = new JLabel ("Nome");
		lbCpf = new JLabel ("CPF");
		lbTelefone = new JLabel ("Telefone");
		btExcluir = new JButton ("Excluir");
		tfIdUsuario = new JTextField (5);
		tfNome = new JTextField (5);
		tfCpf = new JTextField (5);
		tfTelefone = new JTextField (5);
		tfLocalizar = new JTextField(10);
		table = new JTable();

		setPreferredSize (new Dimension(814, 509));
		setLayout (null);

		add (btNovo);
		add (btSalvar);
		add (btBuscar);
		add (lbCodigo);
		add (lbNome);
		add (lbCpf);
		add (lbTelefone);
		add (btExcluir);
		add (tfIdUsuario);
		add (tfNome);
		add (tfCpf);
		add (tfTelefone);
		add(tfLocalizar);

		btNovo.setBounds (676, 47, 75, 20);
		btSalvar.setBounds (671, 93, 80, 20);
		btBuscar.setBounds (676, 15, 75, 25);
		btExcluir.setBounds (661, 123, 90, 20);

		lbCodigo.setBounds (5, 15, 75, 25);
		lbNome.setBounds (5, 45, 45, 25);
		lbCpf.setBounds (5, 75, 90, 25);
		lbTelefone.setBounds (5, 105, 55, 25);
		tfIdUsuario.setBounds (82, 15, 45, 25);
		tfNome.setBounds (55, 45, 183, 25);
		tfCpf.setBounds (55, 75, 110, 25);
		tfTelefone.setBounds (60, 105, 136, 25);

		table = new JTable();
		atualizarGrade();
		JScrollPane scroller = new JScrollPane(table);
		scroller.setBounds(24,286,768,200);
		add(scroller);
		tfLocalizar.setBounds(24,256,200,20);
		add(tfLocalizar);
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setBounds(6, 148, 44, 12);
		add(lblRua);
		
		tfRua = new JTextField(5);
		tfRua.setBounds(55, 145, 136, 25);
		add(tfRua);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(5, 185, 44, 12);
		add(lblCidade);
		
		tfCidade = new JTextField(5);
		tfCidade.setBounds(55, 182, 136, 25);
		add(tfCidade);
		
		tfEstado = new JTextField(5);
		tfEstado.setBounds(55, 221, 136, 25);
		add(tfEstado);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(5, 227, 44, 12);
		add(lblEstado);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(263, 21, 44, 12);
		add(lblCep);
		
		tfCep = new JTextField(5);
		tfCep.setBounds(304, 18, 136, 25);
		add(tfCep);
		
		tfLogin = new JTextField(5);
		tfLogin.setBounds(304, 53, 136, 25);
		add(tfLogin);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(248, 55, 44, 12);
		add(lblLogin);
		
		tfSenha = new JTextField(5);
		tfSenha.setBounds(304, 83, 136, 25);
		add(tfSenha);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(248, 90, 44, 12);
		add(lblSenha);
		
		tfCargo = new JTextField(5);
		tfCargo.setBounds(304, 113, 136, 25);
		add(tfCargo);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setBounds(248, 118, 44, 12);
		add(lblCargo);
		
		tfSalario = new JTextField(5);
		tfSalario.setBounds(304, 142, 136, 25);
		add(tfSalario);
		
		JLabel lblSalario = new JLabel("Salario");
		lblSalario.setBounds(248, 148, 44, 12);
		add(lblSalario);
		
		tfEscala = new JTextField(5);
		tfEscala.setBounds(304, 179, 136, 25);
		add(tfEscala);
		
		JLabel lblEscala = new JLabel("Escala");
		lblEscala.setBounds(248, 185, 44, 12);
		add(lblEscala);
		
		tfCargaHora = new JTextField(5);
		tfCargaHora.setBounds(304, 221, 136, 25);
		add(tfCargaHora);
		
		JLabel lblCargaHora = new JLabel("Carga Horaria");
		lblCargaHora.setBounds(201, 227, 99, 12);
		add(lblCargaHora);
		
	}

	public void atualizarGrade() {
		try {
			// Query completa trazendo os dados do Usuário juntamente com os do Funcionário
			String sql = "SELECT u.id_usuario AS \"Código\", "
					   + "u.nome AS \"Nome\", "
					   + "u.cpf AS \"CPF\", "
					   + "u.telefone AS \"Telefone\", "
					   + "f.cargo AS \"Cargo\", "
					   + "f.salario AS \"Salário\", "
					   + "f.escala AS \"Escala\", "
					   + "f.cargaHora AS \"Carga Horária\", "
					   + "u.cidade AS \"Cidade\" "
					   + "FROM funcionario f "
					   + "INNER JOIN usuario u ON f.id_usuario = u.id_usuario "
					   + "ORDER BY u.id_usuario";
			
			model = MyTableModel.getModel(bd, sql);
			table.setModel(model);
			ajustarGrade();
		} catch (Exception erro) {
			System.out.println("Erro ao atualizar grade: " + erro.toString());
		}
	}

	private void ajustarGrade() {
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setResizable(false);
		DefaultTableCellRenderer alinhaDireita =  new DefaultTableCellRenderer();
		alinhaDireita.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumnModel().getColumn(2).setCellRenderer(alinhaDireita);
		
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setCellRenderer(alinhaDireita);
		
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setResizable(false);
		
		table.getTableHeader().setReorderingAllowed(false);
	}

	
	private void limparCampos() {
		tfIdUsuario.setText("");
		tfNome.setText("");
		tfCpf.setText("");
		tfTelefone.setText("");
		tfRua.setText("");
		tfEstado.setText("");
		tfCidade.setText("");
		tfCep.setText("");
		tfLogin.setText("");
		tfSenha.setText("");
		tfCargo.setText("");
		tfSalario.setText("");
		tfEscala.setText("");
		tfCargaHora.setText("");
		
		
	}
	
	
	public void definirEventos() {
		
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// Pega a linha selecionada
				int linha = table.getSelectedRow();
				if (linha != -1) {
					// Pega o ID que está na primeira coluna (coluna 0) da linha clicada
					String idString = table.getValueAt(linha, 0).toString();
					tfIdUsuario.setText(idString);
					
					// Dispara a busca simulando o clique no botão Buscar para carregar todos os campos
					f = dao.localizar(Integer.parseInt(idString));
					if (f != null) {
						tfNome.setText(f.getNome());
						tfCpf.setText(f.getCpf());
						tfTelefone.setText(f.getTelefone());
						tfCep.setText(f.getCep());
						tfCidade.setText(f.getCidade());
						tfEstado.setText(f.getEstado());
						tfRua.setText(f.getRua());
						tfLogin.setText(f.getLogin());
						tfSenha.setText(f.getSenha());
						tfCargo.setText(f.getCargo());
						tfSalario.setText(String.valueOf(f.getSalario()));
						tfEscala.setText(f.getEscala());
						tfCargaHora.setText(f.getCargaHoraria());
					}
				}
			}
		});
		
		btSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Verifica se o campo ID está vazio para um novo cadastro
				if (tfIdUsuario.getText().trim().isEmpty()) {
					f.setIdUsuario(0);
				} else {
					f.setIdUsuario(Integer.parseInt(tfIdUsuario.getText().trim()));
				}
				
				f.setNome(tfNome.getText());
				f.setCpf(tfCpf.getText());
				f.setTelefone(tfTelefone.getText());
				f.setRua(tfRua.getText());
				f.setEstado(tfEstado.getText());
				f.setCidade(tfCidade.getText());
				f.setCep(tfCep.getText());
				f.setLogin(tfLogin.getText());
				f.setSenha(tfSenha.getText());
				f.setCargo(tfCargo.getText());
				f.setEscala(tfEscala.getText());
				f.setCargaHoraria(tfCargaHora.getText());
				
				// Evita quebrar se o campo salário estiver vazio
				try {
					f.setSalario(Double.parseDouble(tfSalario.getText().replace(",", ".")));
				} catch (NumberFormatException ex) {
					f.setSalario(0.0);
				}
				
				JOptionPane.showMessageDialog(btSalvar, dao.salvar(f));
				atualizarGrade();
				limparCampos();
			}
		});
		
		btExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfIdUsuario.getText().isEmpty()){
					JOptionPane.showMessageDialog(btExcluir, "Selecione um funcionário para excluir.");
					return;
				}
				
				int r = JOptionPane.showConfirmDialog(btExcluir, "Tem certeza que deseja excluir?");
				if(r == 0) {
					f.setIdUsuario(Integer.parseInt(tfIdUsuario.getText()));
					JOptionPane.showMessageDialog(btExcluir, dao.excluir(f));
					limparCampos();
					atualizarGrade();
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
				if (tfIdUsuario.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(btBuscar, "Digite um ID para buscar.");
					return;
				}
				
				f = dao.localizar(Integer.parseInt(tfIdUsuario.getText().trim()));
				if(f != null && f.getNome() != null) {
					tfNome.setText(f.getNome());
					tfCpf.setText(f.getCpf());
					tfTelefone.setText(f.getTelefone());
					tfCep.setText(f.getCep());
					tfCidade.setText(f.getCidade());
					tfEstado.setText(f.getEstado());
					tfRua.setText(f.getRua());
					tfLogin.setText(f.getLogin());
					tfSenha.setText(f.getSenha());
					tfCargo.setText(f.getCargo());
					tfSalario.setText(String.valueOf(f.getSalario()));
					tfEscala.setText(f.getEscala());
					tfCargaHora.setText(f.getCargaHoraria());
				}
				else {
					JOptionPane.showMessageDialog(btBuscar,"Funcionário não encontrado!");
					limparCampos();
				}
			}
		});
		
		
		tfLocalizar.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					// Busca dinâmica baseada no cargo digitado, mas trazendo todas as informações
					String sql = "SELECT u.id_usuario AS \"Código\", "
							   + "u.nome AS \"Nome\", "
							   + "u.cpf AS \"CPF\", "
							   + "u.telefone AS \"Telefone\", "
							   + "f.cargo AS \"Cargo\", "
							   + "f.salario AS \"Salário\", "
							   + "f.escala AS \"Escala\", "
							   + "f.cargaHora AS \"Carga Horária\", "
							   + "u.cidade AS \"Cidade\" "
							   + "FROM funcionario f "
							   + "INNER JOIN usuario u ON f.id_usuario = u.id_usuario "
							   + "WHERE lower(f.cargo) LIKE '" + tfLocalizar.getText().toLowerCase() + "%' "
							   + "ORDER BY u.id_usuario";
					
					model = MyTableModel.getModel(bd, sql);
					table.setModel(model);
					ajustarGrade();
				}
				catch(Exception erro) {
					System.out.println("Erro na busca dinâmica: " + erro.toString());
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	public static void main (String[] args) {
		JFrame frame = new JFrame ("GuiFuncionario");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add (new GuiFuncionario());
		frame.pack();
		frame.setVisible (true);
	}
}
