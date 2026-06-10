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
import piClasses.Cliente;
import piClasses.ClienteDAO;


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
		
	}

	private void atualizarGrade() {
		model = MyTableModel.getModel(bd,"select * from cliente");
		table.setModel(model);
		ajustarGrade();
		
	}

	private void ajustarGrade() {
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setResizable(false);
		
		table.getTableHeader().setReorderingAllowed(false);
	}

	
	private void limparCampos() {
		tfIdUsuario.setText("");
		tfNome.setText("");
		tfCpf.setText("");
		tfTelefone.setText("");
		tfIdUsuario.requestFocus();
		
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
				c = dao.localizar(Integer.parseInt(tfIdUsuario.getText()));
				if(c!=null) {
					tfNome.setText(c.getNome());
					tfCpf.setText(""+c.getTelefone());
					tfTelefone.setText(""+c.getCidade());
					tfCep.setText(""+c.getCep());
					tfCidade.setText(""+c.getCidade());
					tfEstado.setText(""+c.getEstado());
					tfRua.setText(""+c.getRua());
					tfLogin.setText(""+c.getLogin());
					tfSenha.setText(""+c.getSenha());
				}
				else {
					JOptionPane.showMessageDialog(btBuscar,"Cliente n�o encontrado!");
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
					String sql = "select * from cliente where nome like '"+tfLocalizar.getText()+"%'";
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
		frame.setVisible (true);
	}
}
