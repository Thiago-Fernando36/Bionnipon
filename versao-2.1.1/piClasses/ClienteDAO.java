package piClasses;

import java.sql.SQLException;

import banco.BD;

public class ClienteDAO {
	private String sql,men;
	private BD bd;
	
	public ClienteDAO() {
		bd = new BD();
	}
	/**
	 * Realiza a gravação de um funcionario no banco de dados
	 * @param f - o funcionario a ser salvo
	 * @return - uma mensagem informando o ocorrido
	 */
	public String salvar(Cliente c) {
		sql = "insert into usuario values (?,?,?,?,?,?,?,?,?,?); insert into cliente values (?)";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1,c.getIdUsuario());
			bd.st.setString(2, c.getNome());
			bd.st.setString(3, c.getCpf());
			bd.st.setString(4, c.getTelefone());
			bd.st.setString(5, c.getRua());
			bd.st.setString(6, c.getEstado());
			bd.st.setString(7, c.getCidade());
			bd.st.setString(8, c.getCep());
			bd.st.setString(9, c.getLogin());
			bd.st.setString(10,c.getSenha());
			bd.st.setBoolean(11,c.getFidelidade());

			bd.st.executeUpdate();
			men = "Cliente "+ c.getNome()+" inserido com sucesso!";
			
		} catch (SQLException erro) {
			sql = "update usuario set nome=?,cpf=?,telefone=?,rua=?,estado=?,cidade=?,cep=?,login=?,senha=? where id_usuario=?;"
					+ " update cliente set fidelidade=? where id_usuario=?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(10,c.getIdUsuario());
				bd.st.setInt(12,c.getIdUsuario());
				bd.st.setString(1, c.getNome());
				bd.st.setString(2, c.getCpf());
				bd.st.setString(3, c.getTelefone());
				bd.st.setString(4, c.getRua());
				bd.st.setString(5, c.getEstado());
				bd.st.setString(6, c.getCidade());
				bd.st.setString(7, c.getCep());
				bd.st.setString(8, c.getLogin());
				bd.st.setString(9, c.getSenha());
				bd.st.setBoolean(11, c.getFidelidade());
				
				
				bd.st.executeUpdate();
				men = "Cliente "+ c.getNome()+" alterado com sucesso!";
				
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
	public String excluir(Cliente c) {
		sql = "delete from cliente where id_usuario = ?";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1,c.getIdUsuario());
			if(bd.st.executeUpdate()==1) {
				men = "Cliente excluido com sucesso!";
			}else {
				men = "Cliente não encontrado!";
			}
		}catch (SQLException erro) {
		men = "Falha: " + erro.toString();
		}finally {
		bd.close();
		}
		return men;
	
}
	public Cliente localizar( int idUsuario) {
		Cliente c = new Cliente();
		sql = "SELECT u.id_usuario, u.nome, u.cpf, u.telefone, u.rua, u.estado, u.cidade, u.cep, u.login, u.senha, "
	               + "c.fidelidade"
	               + " FROM cliente c "
	               + "INNER JOIN usuario u ON c.id_usuario = u.id_usuario "
	               + "WHERE c.id_usuario = ?";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1,idUsuario);
			bd.rs = bd.st.executeQuery();
			if(bd.rs.next()) {
				c.setIdUsuario(bd.rs.getInt(1));
				c.setNome(bd.rs.getString(2));
				c.setCpf(bd.rs.getString(3));
				c.setTelefone(bd.rs.getString(4));
				c.setRua(bd.rs.getString(5));
				c.setEstado(bd.rs.getString(6));
				c.setCidade(bd.rs.getString(7));
				c.setCep(bd.rs.getString(8));
				c.setLogin(bd.rs.getString(9));
				c.setSenha(bd.rs.getString(10));
				c.setFidelidade(bd.rs.getBoolean(11));

			}else {
				c = null;
			}
		}catch (SQLException erro) {
		c = null;
		}finally {
		bd.close();
		}
		return c;
	
	}
}
