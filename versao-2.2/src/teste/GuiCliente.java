package teste;
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
import piClasses.Cliente;
import piClasses.ClienteDAO;
import javax.swing.JCheckBox;


public class GuiCliente extends JPanel {
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
	private ClienteDAO dao; 
	private Cliente c;

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



	public GuiCliente() {
		dao  = new ClienteDAO(); 
		c  = new Cliente(); 
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

		setPreferredSize (new Dimension(1024, 600)); // Inicializa em tamanho confortável para 11 colunas
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
		add (tfLocalizar);

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
		scroller.setBounds(24,286,970,260); // Ajustado limite padrão inicial
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
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Fidelidade");
		chckbxNewCheckBox.setBounds(304, 123, 92, 20);
		add(chckbxNewCheckBox);
		
	}

	private void atualizarGrade() {
		// Ajustado SQL para trazer as colunas combinadas usando INNER JOIN
		String sql = "SELECT u.id_usuario, u.nome, u.cpf, u.telefone, u.rua, u.estado, u.cidade, u.cep, u.login, u.senha, c.fidelidade "
				   + "FROM cliente c "
				   + "INNER JOIN usuario u ON c.id_usuario = u.id_usuario";
		model = MyTableModel.getModel(bd, sql);
		table.setModel(model);
		ajustarGrade();
		
	}

	private void ajustarGrade() {
		// Habilita o auto-ajuste elástico e responsivo das colunas
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setResizable(true);
		}

		// Configuração de tamanhos sugeridos proporcionais (Totalizando 11 colunas)
		table.getColumnModel().getColumn(0).setPreferredWidth(45);   // ID
		table.getColumnModel().getColumn(1).setPreferredWidth(170);  // Nome
		table.getColumnModel().getColumn(2).setPreferredWidth(95);   // CPF
		table.getColumnModel().getColumn(3).setPreferredWidth(95);   // Telefone
		table.getColumnModel().getColumn(4).setPreferredWidth(110);  // Rua
		table.getColumnModel().getColumn(5).setPreferredWidth(45);   // Estado
		table.getColumnModel().getColumn(6).setPreferredWidth(90);   // Cidade
		table.getColumnModel().getColumn(7).setPreferredWidth(75);   // CEP
		table.getColumnModel().getColumn(8).setPreferredWidth(75);   // Login
		table.getColumnModel().getColumn(9).setPreferredWidth(75);   // Senha
		table.getColumnModel().getColumn(10).setPreferredWidth(65);  // Fidelidade
		
		table.getTableHeader().setReorderingAllowed(false);
	}

	
	private void limparCampos() {
		tfIdUsuario.setText("");
		tfNome.setText("");
		tfCpf.setText("");
		tfTelefone.setText("");
		tfRua.setText("");
		tfCidade.setText("");
		tfEstado.setText("");
		tfCep.setText("");
		tfLogin.setText("");
		tfSenha.setText("");
		tfIdUsuario.requestFocus();
		
	}
	
	
	public void definirEventos() {
		
		// Evento Dinâmico para tornar o JScrollPane e o Campo Localizar responsivos
		this.addComponentListener(new java.awt.event.ComponentAdapter() {
			@Override
			public void componentResized(java.awt.event.ComponentEvent e) {
				int larguraPainel = getWidth();
				int alturaPainel = getHeight();
				
				tfLocalizar.setBounds(24, 256, larguraPainel - 50, 20);
				
				if (table.getParent() != null && table.getParent().getParent() instanceof JScrollPane) {
					JScrollPane scroller = (JScrollPane) table.getParent().getParent();
					scroller.setBounds(24, 286, larguraPainel - 50, alturaPainel - 320);
				}
			}
		});
		
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
				// Mapeado todas as 10 strings/inteiros para atualizar as caixas de texto ao clicar na linha
				tfIdUsuario.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
				tfNome.setText((String)table.getValueAt(table.getSelectedRow(), 1));
				tfCpf.setText((String)table.getValueAt(table.getSelectedRow(), 2));
				tfTelefone.setText((String)table.getValueAt(table.getSelectedRow(), 3));
				tfRua.setText((String)table.getValueAt(table.getSelectedRow(), 4));
				tfEstado.setText((String)table.getValueAt(table.getSelectedRow(), 5));
				tfCidade.setText((String)table.getValueAt(table.getSelectedRow(), 6));
				tfCep.setText((String)table.getValueAt(table.getSelectedRow(), 7));
				tfLogin.setText((String)table.getValueAt(table.getSelectedRow(), 8));
				tfSenha.setText((String)table.getValueAt(table.getSelectedRow(), 9));
				
			}
		});
		
		btSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.setIdUsuario(Integer.parseInt(tfIdUsuario.getText()));
				c.setNome(tfNome.getText());
				c.setCpf(tfCpf.getText());
				c.setTelefone(tfTelefone.getText());
				c.setRua(tfRua.getText());
				c.setEstado(tfEstado.getText());
				c.setCidade(tfCidade.getText());
				c.setCep(tfCep.getText());
				c.setLogin(tfLogin.getText());
				c.setSenha((tfSenha.getText()));
				
				JOptionPane.showMessageDialog(btSalvar,dao.salvar(c));
				atualizarGrade();
			}
		});
		
		btExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(btExcluir, "Tem certeza?");
				if(r==0) {
					//JOptionPane.showMessageDialog(btExcluir,dao.excluir(Integer.parseInt(tfIdUsuario.getText())));
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
				c = dao.localizar(Integer.parseInt(tfIdUsuario.getText()));
				if(c!=null) {
					tfNome.setText(c.getNome());
					tfCpf.setText(c.getCpf());          // Corrigido: estava trocado por c.getTelefone()
					tfTelefone.setText(c.getTelefone()); // Corrigido: estava trocado por c.getCidade()
					tfCep.setText(c.getCep());
					tfCidade.setText(c.getCidade());
					tfEstado.setText(c.getEstado());
					tfRua.setText(c.getRua());
					tfLogin.setText(c.getLogin());
					tfSenha.setText(c.getSenha());
				}
				else {
					JOptionPane.showMessageDialog(btBuscar,"Cliente não encontrado!");
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
					// Query de localização dinâmica também adaptada para INNER JOIN trazendo tudo
					String sql = "SELECT u.id_usuario, u.nome, u.cpf, u.telefone, u.rua, u.estado, u.cidade, u.cep, u.login, u.senha, c.fidelidade "
							   + "FROM cliente c "
							   + "INNER JOIN usuario u ON c.id_usuario = u.id_usuario "
							   + "WHERE u.nome LIKE '" + tfLocalizar.getText() + "%'";
					model = MyTableModel.getModel(bd, sql);
					table.setModel(model);
					ajustarGrade();
				}
				catch(Exception erro) {}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	public static void main (String[] args) {
		JFrame frame = new JFrame ("GuiCliente");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add (new GuiCliente());
		frame.pack();
		frame.setResizable(true);        // Garante que o usuário possa redimensionar e maximizar a tela
		frame.setLocationRelativeTo(null); // Centraliza no monitor ao abrir
		frame.setVisible (true);
	}
}