package piClasses;

import java.sql.SQLException;

import banco.BD;

public class UsuarioDAO {

	private String sql,men;
	private BD bd;
	
	public UsuarioDAO() {
		bd = new BD();
	}
	/**
	 * Realiza a gravação de um usuario no banco de dados
	 * @param u - o usuario a ser salvo
	 * @return - uma mensagem informando o ocorrido
	 */
	public String salvar(Usuario u) {
		sql = "insert into usuario values (?,?,?,?,?,?,?,?,?,?)";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1,u.getIdUsuario());
			bd.st.setString(2, u.getNome());
			bd.st.setString(3, u.getCpf());
			bd.st.setString(4, u.getTelefone());
			bd.st.setString(5, u.getRua());
			bd.st.setString(6, u.getEstado());
			bd.st.setString(7, u.getCidade());
			bd.st.setString(8, u.getCep());
			bd.st.setString(9, u.getLogin());
			bd.st.setString(10, u.getSenha());
			bd.st.executeUpdate();
			men = "Usuario "+ u.getNome()+" inserido com sucesso!";
			
		} catch (SQLException erro) {
			sql = "update usuario set nome=?,cpf=?,telefone=?,rua=?,estado=?,cidade=?,cep=?,login=?,senha=? where id_usuario=?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(10,u.getIdUsuario());
				bd.st.setString(1, u.getNome());
				bd.st.setString(2, u.getCpf());
				bd.st.setString(3, u.getTelefone());
				bd.st.setString(4, u.getRua());
				bd.st.setString(5, u.getEstado());
				bd.st.setString(6, u.getCidade());
				bd.st.setString(7, u.getCep());
				bd.st.setString(8, u.getLogin());
				bd.st.setString(9, u.getSenha());
				bd.st.executeUpdate();
				men = "Usuario "+ u.getNome()+" alterado com sucesso!";
				
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
	public String excluir(Usuario u) {
		sql = "delete from usuario where id_usuario = ?";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1,u.getIdUsuario());
			if(bd.st.executeUpdate()==1) {
				men = "Usuario excluido com sucesso!";
			}else {
				men = "Usuario não encontrado!";
			}
		}catch (SQLException erro) {
		men = "Falha: " + erro.toString();
		}finally {
		bd.close();
		}
		return men;
	
}
	public Usuario localizar( int idUsuario) {
		Usuario u = new Usuario();
		sql = "select * from usuario where id_usuario = ?";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1,idUsuario);
			bd.rs = bd.st.executeQuery();
			if(bd.rs.next()) {
				u.setIdUsuario(bd.rs.getInt(1));
				u.setNome(bd.rs.getString(2));
				u.setCpf(bd.rs.getString(3));
				u.setTelefone(bd.rs.getString(4));
				u.setRua(bd.rs.getString(5));
				u.setEstado(bd.rs.getString(6));
				u.setCidade(bd.rs.getString(7));
				u.setCep(bd.rs.getString(8));
				u.setLogin(bd.rs.getString(9));
				u.setSenha(bd.rs.getString(10));
			}else {
				u = null;
			}
		}catch (SQLException erro) {
		u = null;
		}finally {
		bd.close();
		}
		return u;
	
	}
	
	
	
	
	
}
