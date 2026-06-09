package piClasses;

import java.sql.SQLException;

import banco.BD;

public class FuncionarioDAO {
	private String sql,men;
	private BD bd;
	
	public FuncionarioDAO() {
		bd = new BD();
	}
	/**
	 * Realiza a gravação de um funcionario no banco de dados
	 * @param f - o funcionario a ser salvo
	 * @return - uma mensagem informando o ocorrido
	 */
	public String salvar(Funcionario f) {
		sql = "insert into usuario values (?,?,?,?,?,?,?,?,?,?); insert into funcionario values (?,?,?,?,?)";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1,f.getIdUsuario());
			bd.st.setString(2, f.getNome());
			bd.st.setString(3, f.getCpf());
			bd.st.setString(4, f.getTelefone());
			bd.st.setString(5, f.getRua());
			bd.st.setString(6, f.getEstado());
			bd.st.setString(7, f.getCidade());
			bd.st.setString(8, f.getCep());
			bd.st.setString(9, f.getLogin());
			bd.st.setString(10,f.getSenha());
			bd.st.setString(12,f.getCargo());
			bd.st.setDouble(13,f.getSalario());
			bd.st.setString(14,f.getEscala());
			bd.st.setString(15,f.getCargaHoraria());
			bd.st.setInt(11,f.getIdUsuario());
			bd.st.executeUpdate();
			men = "Funcionario "+ f.getNome()+" inserido com sucesso!";
			
		} catch (SQLException erro) {
			sql = "update usuario set nome=?,cpf=?,telefone=?,rua=?,estado=?,cidade=?,cep=?,login=?,senha=? where id_usuario=?;"
					+ " update funcionario set cargo=?,salario=?,escala=?,cargaHora=? where id_usuario=?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(10,f.getIdUsuario());
				bd.st.setInt(15,f.getIdUsuario());
				bd.st.setString(1, f.getNome());
				bd.st.setString(2, f.getCpf());
				bd.st.setString(3, f.getTelefone());
				bd.st.setString(4, f.getRua());
				bd.st.setString(5, f.getEstado());
				bd.st.setString(6, f.getCidade());
				bd.st.setString(7, f.getCep());
				bd.st.setString(8, f.getLogin());
				bd.st.setString(9, f.getSenha());
				bd.st.setString(11, f.getCargo());
				bd.st.setDouble(12, f.getSalario());
				bd.st.setString(13, f.getEscala());
				bd.st.setString(14, f.getCargaHoraria());
				
				bd.st.executeUpdate();
				men = "Funcionario "+ f.getNome()+" alterado com sucesso!";
				
			} catch (SQLException erro2) {
				men = "Falha: " + erro2.toString();
			}
			
		}finally {
			bd.close();
		}
		return men;
	}
	
	/**
	 * Realiza a exclusão de um usuario no banco de dados
	 * @param u - o usuario a ser excluido
	 * @return - uma mensagem informando o ocorrido
	 */
	public String excluir(Funcionario f) {
		sql = "delete from funcionario where id_usuario = ?";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1,f.getIdUsuario());
			if(bd.st.executeUpdate()==1) {
				men = "Funcionario excluido com sucesso!";
			}else {
				men = "Funcionario não encontrado!";
			}
		}catch (SQLException erro) {
		men = "Falha: " + erro.toString();
		}finally {
		bd.close();
		}
		return men;
	
}
	public Funcionario localizar( int idUsuario) {
		Funcionario f = new Funcionario();
		sql = "SELECT u.id_usuario, u.nome, u.cpf, u.telefone, u.rua, u.estado, u.cidade, u.cep, u.login, u.senha, "
	               + "f.cargo, f.salario, f.escala, f.cargaHora "
	               + "FROM funcionario f "
	               + "INNER JOIN usuario u ON f.id_usuario = u.id_usuario "
	               + "WHERE f.id_usuario = ?";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1,idUsuario);
			bd.rs = bd.st.executeQuery();
			if(bd.rs.next()) {
				f.setIdUsuario(bd.rs.getInt(1));
				f.setNome(bd.rs.getString(2));
				f.setCpf(bd.rs.getString(3));
				f.setTelefone(bd.rs.getString(4));
				f.setRua(bd.rs.getString(5));
				f.setEstado(bd.rs.getString(6));
				f.setCidade(bd.rs.getString(7));
				f.setCep(bd.rs.getString(8));
				f.setLogin(bd.rs.getString(9));
				f.setSenha(bd.rs.getString(10));
				f.setCargo(bd.rs.getString(11));
				f.setSalario(bd.rs.getDouble(12));
				f.setEscala(bd.rs.getString(13));
				f.setCargaHoraria(bd.rs.getString(14));
			}else {
				f = null;
			}
		}catch (SQLException erro) {
		f = null;
		}finally {
		bd.close();
		}
		return f;
	
	}
}
