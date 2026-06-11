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
	    try {
	        bd.getConnection();
	        
	        // Se o ID for 0, significa que o campo na tela estava vazio (Novo Cliente)
	        if (c.getIdUsuario() == 0) {
	            // 1. Inserimos no USUARIO omitindo o id_usuario para o SERIAL do Postgres agir
	            String sqlUsuario = "insert into usuario (nome, cpf, telefone, rua, estado, cidade, cep, login, senha) values (?,?,?,?,?,?,?,?,?)";
	            
	            // Informamos ao Driver que queremos o ID gerado de volta
	            bd.st = bd.con.prepareStatement(sqlUsuario, java.sql.Statement.RETURN_GENERATED_KEYS);
	            bd.st.setString(1, c.getNome());
	            bd.st.setString(2, c.getCpf());
	            bd.st.setString(3, c.getTelefone());
	            bd.st.setString(4, c.getRua());
	            bd.st.setString(5, c.getEstado());
	            bd.st.setString(6, c.getCidade());
	            bd.st.setString(7, c.getCep());
	            bd.st.setString(8, c.getLogin());
	            bd.st.setString(9, c.getSenha());
	            bd.st.executeUpdate();
	            
	            // Recuperamos o ID gerado automaticamente pelo banco
	            bd.rs = bd.st.getGeneratedKeys();
	            int idGerado = 0;
	            if (bd.rs.next()) {
	                idGerado = bd.rs.getInt(1);
	            }
	            
	            // 2. Agora inserimos no CLIENTE usando o ID que o banco acabou de gerar
	            String sqlCliente = "insert into cliente (id_usuario, fidelidade) values (?,?)";
	            bd.st = bd.con.prepareStatement(sqlCliente);
	            bd.st.setInt(1, idGerado);
	            bd.st.setBoolean(2, c.getFidelidade());
	            bd.st.executeUpdate();
	            
	            men = "Cliente " + c.getNome() + " inserido com sucesso! (ID gerado: " + idGerado + ")";
	            
	        } else {
	            // Se o ID for maior que 0, significa que veio da tabela ou busca (Alterar Cliente)
	            String updateUsuario = "update usuario set nome=?,cpf=?,telefone=?,rua=?,estado=?,cidade=?,cep=?,login=?,senha=? where id_usuario=?";
	            bd.st = bd.con.prepareStatement(updateUsuario);
	            bd.st.setString(1, c.getNome());
	            bd.st.setString(2, c.getCpf());
	            bd.st.setString(3, c.getTelefone());
	            bd.st.setString(4, c.getRua());
	            bd.st.setString(5, c.getEstado());
	            bd.st.setString(6, c.getCidade());
	            bd.st.setString(7, c.getCep());
	            bd.st.setString(8, c.getLogin());
	            bd.st.setString(9, c.getSenha());
	            bd.st.setInt(10, c.getIdUsuario());
	            bd.st.executeUpdate();
	            
	            String updateCliente = "update cliente set fidelidade=? where id_usuario=?";
	            bd.st = bd.con.prepareStatement(updateCliente);
	            bd.st.setBoolean(1, c.getFidelidade());
	            bd.st.setInt(2, c.getIdUsuario());
	            bd.st.executeUpdate();
	            
	            men = "Cliente " + c.getNome() + " alterado com sucesso!";
	        }
	        
	    } catch (SQLException erro) {
	        men = "Falha ao salvar: " + erro.toString();
	    } finally {
	        bd.close();
	    }
	    return men;
	}
	
	/**
	 * Realiza a exclusão de um usuario e cliente no banco de dados
	 * @param c - o cliente a ser excluido
	 * @return - uma mensagem informando o ocorrido
	 */
	public String excluir(Cliente c) {
		// Ao deletar o usuário, o "ON DELETE CASCADE" da tabela cliente 
		// vai apagar o registro filho automaticamente.
		sql = "delete from usuario where id_usuario = ?";
		try {
			bd.getConnection();
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, c.getIdUsuario());
			
			if(bd.st.executeUpdate() == 1) {
				men = "Cliente excluido com sucesso!";
			} else {
				men = "Cliente não encontrado!";
			}
		} catch (SQLException erro) {
			men = "Falha ao excluir: " + erro.toString();
		} finally {
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
