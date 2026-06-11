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
		try {
			bd.getConnection();
			
			// Se o ID for 0, é um novo registro (INSERT)
			if (f.getIdUsuario() == 0) {
				// 1. Insere na tabela pai (usuario) e pede o ID de volta
				String sqlUsuario = "insert into usuario (nome, cpf, telefone, rua, estado, cidade, cep, login, senha) values (?,?,?,?,?,?,?,?,?)";
				bd.st = bd.con.prepareStatement(sqlUsuario, java.sql.Statement.RETURN_GENERATED_KEYS);
				bd.st.setString(1, f.getNome());
				bd.st.setString(2, f.getCpf());
				bd.st.setString(3, f.getTelefone());
				bd.st.setString(4, f.getRua());
				bd.st.setString(5, f.getEstado());
				bd.st.setString(6, f.getCidade());
				bd.st.setString(7, f.getCep());
				bd.st.setString(8, f.getLogin());
				bd.st.setString(9, f.getSenha());
				bd.st.executeUpdate();
				
				// Pega o ID gerado pelo PostgreSQL
				bd.rs = bd.st.getGeneratedKeys();
				int idGerado = 0;
				if (bd.rs.next()) {
					idGerado = bd.rs.getInt(1);
				}
				
				// 2. Insere na tabela filha (funcionario) usando o ID gerado
				String sqlFuncionario = "insert into funcionario (id_usuario, cargo, salario, escala, cargaHora) values (?,?,?,?,?)";
				bd.st = bd.con.prepareStatement(sqlFuncionario);
				bd.st.setInt(1, idGerado);
				bd.st.setString(2, f.getCargo());
				bd.st.setDouble(3, f.getSalario());
				bd.st.setString(4, f.getEscala());
				bd.st.setString(5, f.getCargaHoraria());
				bd.st.executeUpdate();
				
				men = "Funcionário " + f.getNome() + " inserido com sucesso! (ID: " + idGerado + ")";
				
			} else {
				// Se o ID > 0, é uma edição (UPDATE)
				String updateUsuario = "update usuario set nome=?,cpf=?,telefone=?,rua=?,estado=?,cidade=?,cep=?,login=?,senha=? where id_usuario=?";
				bd.st = bd.con.prepareStatement(updateUsuario);
				bd.st.setString(1, f.getNome());
				bd.st.setString(2, f.getCpf());
				bd.st.setString(3, f.getTelefone());
				bd.st.setString(4, f.getRua());
				bd.st.setString(5, f.getEstado());
				bd.st.setString(6, f.getCidade());
				bd.st.setString(7, f.getCep());
				bd.st.setString(8, f.getLogin());
				bd.st.setString(9, f.getSenha());
				bd.st.setInt(10, f.getIdUsuario());
				bd.st.executeUpdate();
				
				String updateFunc = "update funcionario set cargo=?, salario=?, escala=?, cargaHora=? where id_usuario=?";
				bd.st = bd.con.prepareStatement(updateFunc);
				bd.st.setString(1, f.getCargo());
				bd.st.setDouble(2, f.getSalario());
				bd.st.setString(3, f.getEscala());
				bd.st.setString(4, f.getCargaHoraria());
				bd.st.setInt(5, f.getIdUsuario());
				bd.st.executeUpdate();
				
				men = "Funcionário " + f.getNome() + " alterado com sucesso!";
			}
			
		} catch (SQLException erro) {
			men = "Falha ao salvar: " + erro.toString();
		} finally {
			bd.close();
		}
		return men;
	}

	public String excluir(Funcionario f) {
		// Deleta da tabela principal, o ON DELETE CASCADE cuida da tabela funcionario
		sql = "delete from usuario where id_usuario = ?";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, f.getIdUsuario());
			
			if(bd.st.executeUpdate() == 1) {
				men = "Funcionário excluído com sucesso!";
			} else {
				men = "Funcionário não encontrado!";
			}
		} catch (SQLException erro) {
			men = "Falha ao excluir: " + erro.toString();
		} finally {
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
