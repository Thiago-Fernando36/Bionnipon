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

	private void atualizarGrade() {
		model = MyTableModel.getModel(bd,"SELECT u.id_usuario, u.nome, u.cpf, u.telefone, u.rua, u.estado, u.cidade, u.cep, u.login, u.senha, "
	               + "f.cargo, f.salario, f.escala, f.cargaHora "
	               + "FROM funcionario f "
	               + "INNER JOIN usuario u ON f.id_usuario = u.id_usuario");
		table.setModel(model);
		ajustarGrade();
		
	}

	private void ajustarGrade() {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	    
	   
	    for (int i = 0; i < table.getColumnCount(); i++) {
	        table.getColumnModel().getColumn(i).setResizable(true);
	    }
		table.getColumnModel().getColumn(0).setPreferredWidth(40);   // id_usuario
	    table.getColumnModel().getColumn(1).setPreferredWidth(150);  // nome
	    table.getColumnModel().getColumn(2).setPreferredWidth(90);   // cpf
	    table.getColumnModel().getColumn(3).setPreferredWidth(90);   // telefone
	    table.getColumnModel().getColumn(4).setPreferredWidth(100);  // rua
	    table.getColumnModel().getColumn(5).setPreferredWidth(40);   // estado
	    table.getColumnModel().getColumn(6).setPreferredWidth(80);   // cidade
	    table.getColumnModel().getColumn(7).setPreferredWidth(70);   // cep
	    table.getColumnModel().getColumn(8).setPreferredWidth(70);   // login
	    table.getColumnModel().getColumn(9).setPreferredWidth(70);   // senha
	    table.getColumnModel().getColumn(10).setPreferredWidth(80);  // cargo
	    table.getColumnModel().getColumn(11).setPreferredWidth(70);  // salario
	    table.getColumnModel().getColumn(12).setPreferredWidth(50);  // escala
	    table.getColumnModel().getColumn(13).setPreferredWidth(80);  // carga_horaria

	    
	    DefaultTableCellRenderer alinhaDireita = new DefaultTableCellRenderer();
	    alinhaDireita.setHorizontalAlignment(SwingConstants.RIGHT);
	    table.getColumnModel().getColumn(11).setCellRenderer(alinhaDireita);
	    
	    
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
				tfIdUsuario.setText((String)table.getValueAt(table.getSelectedRow(), 0));
				tfNome.setText((String)table.getValueAt(table.getSelectedRow(), 1));
				tfCpf.setText((String)table.getValueAt(table.getSelectedRow(), 2));
				tfTelefone.setText((String)table.getValueAt(table.getSelectedRow(), 3));
				tfRua.setText((String)table.getValueAt(table.getSelectedRow(), 4));
				tfEstado.setText((String)table.getValueAt(table.getSelectedRow(), 5));
				tfCidade.setText((String)table.getValueAt(table.getSelectedRow(), 6));
				tfCep.setText((String)table.getValueAt(table.getSelectedRow(), 7));
				tfLogin.setText((String)table.getValueAt(table.getSelectedRow(), 8));
				tfSenha.setText((String)table.getValueAt(table.getSelectedRow(), 9));
				tfCargo.setText((String)table.getValueAt(table.getSelectedRow(), 10));
				tfSalario.setText((String)table.getValueAt(table.getSelectedRow(), 11));
				tfEscala.setText((String)table.getValueAt(table.getSelectedRow(), 12));
				tfCargaHora.setText((String)table.getValueAt(table.getSelectedRow(), 13));
				
			}
		});
		
		btSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setIdUsuario(Integer.parseInt(tfIdUsuario.getText()));
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
				f.setSalario(Double.parseDouble(tfSalario.getText()));
				f.setEscala(tfEscala.getText());
				f.setCargaHoraria(tfCargaHora.getText());
				JOptionPane.showMessageDialog(btSalvar,dao.salvar(f));
				atualizarGrade();
			}
		});
		
		btExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(btExcluir, "Tem certeza?");
				if(r==0) {
					//JOptionPane.showMessageDialog(btExcluir,dao.excluir(Integer.parseInt(tfCodigo.getText())));
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
				f = dao.localizar(Integer.parseInt(tfIdUsuario.getText()));
				if(f!=null) {
					tfNome.setText(f.getNome());
					tfCpf.setText(""+f.getTelefone());
					tfTelefone.setText(""+f.getCidade());
					tfCep.setText(""+f.getCep());
					tfCidade.setText(""+f.getCidade());
					tfEstado.setText(""+f.getEstado());
					tfRua.setText(""+f.getRua());
					tfLogin.setText(""+f.getLogin());
					tfSenha.setText(""+f.getSenha());
					tfCargo.setText(""+f.getCargo());
					tfSalario.setText(""+f.getSalario());
					tfEscala.setText(""+f.getEscala());
					tfCargaHora.setText(""+f.getCargaHoraria());
				}
				else {
					JOptionPane.showMessageDialog(btBuscar,"Funcionario não encontrado!");
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
			        // Query de busca ajustada para também trazer todas as informações estruturadas
			        String sql = "SELECT u.id_usuario, u.nome, u.cpf, u.telefone, u.rua, u.estado, u.cidade, u.cep, u.login, u.senha, "
			                   + "f.cargo, f.salario, f.escala, f.cargaHora "
			                   + "FROM funcionario f "
			                   + "INNER JOIN usuario u ON f.id_usuario = u.id_usuario "
			                   + "WHERE f.cargo LIKE '" + tfLocalizar.getText() + "%'";
			                   
			        model = MyTableModel.getModel(bd, sql);
			        table.setModel(model);
			        ajustarGrade();
			    }
			    catch(Exception erro) {
			        System.err.println("Erro na busca: " + erro.getMessage());
			    }
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		// Faz a tabela e o scroller acompanharem o tamanho da janela de forma responsiva
		this.addComponentListener(new java.awt.event.ComponentAdapter() {
		    @Override
		    public void componentResized(java.awt.event.ComponentEvent e) {
		        // Pega a largura atual do painel (janela)
		        int larguraPainel = getWidth();
		        int alturaPainel = getHeight();
		        
		        // Define a nova largura do campo de localizar acompanhando a tela
		        tfLocalizar.setBounds(24, 256, larguraPainel - 50, 20);
		        
		        // Se o scroller da tabela existir, ele se estica baseado no tamanho da janela
		        if (table.getParent() != null && table.getParent().getParent() instanceof JScrollPane) {
		            JScrollPane scroller = (JScrollPane) table.getParent().getParent();
		            
		            // Define a posição inicial (24, 286), a nova largura responsiva e a altura
		            scroller.setBounds(24, 286, larguraPainel - 50, alturaPainel - 320);
		        }
		    }
		});
	}

	public static void main (String[] args) {
	    JFrame frame = new JFrame ("GuiFuncionario");
	    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    
	    // Criamos o seu painel
	    GuiFuncionario painel = new GuiFuncionario();
	    
	    frame.getContentPane().add (painel);
	    frame.pack();
	    
	    // PERMITIR RESPONSIVIDADE NA JANELA:
	    // Faz com que o JFrame possa ser maximizado e redimensionado
	    frame.setResizable(true); 
	    frame.setLocationRelativeTo(null); // Centraliza a janela na tela ao abrir
	    frame.setVisible (true);
	}
}
