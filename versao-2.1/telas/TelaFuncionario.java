package telas;

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
import java.awt.GridLayout;

public class TelaFuncionario extends JFrame {

    private JTextField tfNome;
    private JTextField tfCargo;
    private JTextField tfCpf;
    private JTextField tfTelefone;
    private JTextField tfRua;
    private JTextField tfCidade;
    private JTextField tfEstado;
    private JTextField tfCep;
    private JTextField tfSalario;
    private JTextField tfEscala;
    private JTextField tfCargaHora;
    private JTextField tfLogin;
    private JTextField tfSenha;
    private JTextField tfIdFuncionario;
    private JTextField tfLocalizar;
    
    private JTable table;
    private DefaultTableModel model;
    
    
    private FuncionarioDAO dao;
    private Funcionario f;
    private BD bd;

    public TelaFuncionario(){
    	
    	dao = new FuncionarioDAO();
    	f = new Funcionario();
    	bd = new BD();
    	bd.getConnection();
    	
    	
    	
        setTitle("Cadastro de Funcionário");
        setSize(868,516);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

        JPanel p = new JPanel();
        p.setLayout(null);

        JLabel label = new JLabel("Nome");
        label.setBounds(22, 9, 44, 13);
        p.add(label);
        tfNome = new JTextField(20);
        tfNome.setBounds(71, 6, 166, 19);
        p.add(tfNome);

        JLabel label_1 = new JLabel("Cargo");
        label_1.setBounds(290, 9, 56, 13);
        p.add(label_1);
        tfCargo = new JTextField(20);
        tfCargo.setBounds(356, 6, 166, 19);
        p.add(tfCargo);

        JButton salvar = new JButton("Cadastrar");
        salvar.setBounds(610, 41, 110, 21);
        JButton editar = new JButton("Editar");
        editar.setBounds(610, 84, 85, 21);
        JButton excluir = new JButton("Excluir");
        excluir.setBounds(610, 128, 99, 21);

        p.add(salvar);
        p.add(editar);
        p.add(excluir);

        getContentPane().add(p);
        
        JLabel lblNewLabel = new JLabel("CPF");
        lblNewLabel.setBounds(22, 45, 44, 12);
        p.add(lblNewLabel);
        
        tfCpf = new JTextField(20);
        tfCpf.setBounds(71, 42, 166, 19);
        p.add(tfCpf);
        
        JLabel lblNewLabel_1 = new JLabel("Telefone");
        lblNewLabel_1.setBounds(10, 88, 56, 12);
        p.add(lblNewLabel_1);
        
        tfTelefone = new JTextField(20);
        tfTelefone.setBounds(71, 85, 166, 19);
        p.add(tfTelefone);
        
        JLabel lblNewLabel_1_1 = new JLabel("Rua");
        lblNewLabel_1_1.setBounds(22, 132, 44, 12);
        p.add(lblNewLabel_1_1);
        
        tfRua = new JTextField(20);
        tfRua.setBounds(71, 129, 166, 19);
        p.add(tfRua);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Cidade");
        lblNewLabel_1_1_1.setBounds(10, 161, 44, 12);
        p.add(lblNewLabel_1_1_1);
        
        tfCidade = new JTextField(20);
        tfCidade.setBounds(71, 158, 166, 19);
        p.add(tfCidade);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Estado");
        lblNewLabel_1_1_1_1.setBounds(22, 196, 44, 12);
        p.add(lblNewLabel_1_1_1_1);
        
        tfEstado = new JTextField(20);
        tfEstado.setBounds(71, 193, 166, 19);
        p.add(tfEstado);
        
        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("CEP");
        lblNewLabel_1_1_1_1_1.setBounds(22, 231, 44, 12);
        p.add(lblNewLabel_1_1_1_1_1);
        
        tfCep = new JTextField(20);
        tfCep.setBounds(71, 228, 166, 19);
        p.add(tfCep);
        
        JLabel label_1_1 = new JLabel("Salario");
        label_1_1.setBounds(290, 45, 50, 13);
        p.add(label_1_1);
        
        tfSalario = new JTextField(20);
        tfSalario.setBounds(356, 42, 166, 19);
        p.add(tfSalario);
        
        JLabel escala = new JLabel("Escala");
        escala.setBounds(290, 88, 50, 13);
        p.add(escala);
        
        tfEscala = new JTextField(20);
        tfEscala.setBounds(356, 85, 166, 19);
        p.add(tfEscala);
        
        JLabel label_1_1_1_1 = new JLabel("Carga Horaria");
        label_1_1_1_1.setBounds(268, 132, 86, 13);
        p.add(label_1_1_1_1);
        
        tfCargaHora = new JTextField(20);
        tfCargaHora.setBounds(356, 129, 166, 19);
        p.add(tfCargaHora);
        
        JLabel label_1_1_1_1_1 = new JLabel("Login");
        label_1_1_1_1_1.setBounds(290, 196, 64, 13);
        p.add(label_1_1_1_1_1);
        
        tfLogin = new JTextField(20);
        tfLogin.setBounds(356, 193, 166, 19);
        p.add(tfLogin);
        
        JLabel label_1_1_1_1_1_1 = new JLabel("Senha");
        label_1_1_1_1_1_1.setBounds(290, 231, 64, 13);
        p.add(label_1_1_1_1_1_1);
        
        tfSenha = new JTextField(20);
        tfSenha.setBounds(356, 228, 166, 19);
        p.add(tfSenha);
        
        JLabel label_1_1_1_1_1_1_1 = new JLabel("ID Funcionario");
        label_1_1_1_1_1_1_1.setBounds(532, 9, 117, 13);
        p.add(label_1_1_1_1_1_1_1);
        
        tfIdFuncionario = new JTextField(20);
        tfIdFuncionario.setBounds(636, 6, 166, 19);
        p.add(tfIdFuncionario);
        
        table = new JTable();
		atualizarGrade();
		
		JLabel label_2 = new JLabel("");
		getContentPane().add(label_2);
		JScrollPane scroller = new JScrollPane(table);
		scroller.setBounds(5,220,580,200);
		getContentPane().add(scroller);
		tfLocalizar.setBounds(5,195,200,20);
		getContentPane().add(tfLocalizar);
        
        salvar.addActionListener(e ->
            JOptionPane.showMessageDialog(null,"Funcionário cadastrado"));

        editar.addActionListener(e ->
            JOptionPane.showMessageDialog(null,"Funcionário editado"));

        excluir.addActionListener(e ->
            JOptionPane.showMessageDialog(null,"Funcionário excluído"));
        
        
        /*table = new JTable();
        JScrollPane scroller = new JScrollPane(table);
        scroller.setBounds(5,100,100,100);
        getContentPane().add(scroller);
        */
        
        
        
    }
    private void atualizarGrade() {
		model = MyTableModel.getModel(bd,"select * from produtos");
		table.setModel(model);
		ajustarGrade();
		
	}
    private void ajustarGrade() {
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setResizable(false);
		DefaultTableCellRenderer alinhaDireita =  new DefaultTableCellRenderer();
		alinhaDireita.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumnModel().getColumn(2).setCellRenderer(alinhaDireita);
		
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setCellRenderer(alinhaDireita);
		
		table.getTableHeader().setReorderingAllowed(false);
	}
}