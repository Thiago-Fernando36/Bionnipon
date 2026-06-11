package piClasses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import banco.BD;

public class PragaDAO {
    private String sql, men;
    private BD bd;
    
    public PragaDAO() {
        bd = new BD();
    }

    /**
	 * Realiza a gravação de uma praga (Inserção ou Atualização se já existir)
	 */
	public String salvar(Praga p) {
		try {
			bd.getConnection();
			
			// Se o ID for 0, é um novo registro (INSERT)
			if (p.getIdPraga() == 0) {
				// Omitimos o id_praga no insert para o banco gerar sozinho
				sql = "insert into pragas (nome_tipo, veneno, valor) values (?,?,?)";
				bd.st = bd.con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
				bd.st.setString(1, p.getNomeTipo());
				bd.st.setString(2, p.getVeneno());
				bd.st.setDouble(3, p.getValor());
				
				bd.st.executeUpdate();
				
				// Pega o ID gerado pelo PostgreSQL
				bd.rs = bd.st.getGeneratedKeys();
				int idGerado = 0;
				if (bd.rs.next()) {
					idGerado = bd.rs.getInt(1);
				}
				
				men = "Praga " + p.getNomeTipo() + " inserida com sucesso! (ID: " + idGerado + ")";
				
			} else {
				// Se o ID for maior que 0, é uma edição (UPDATE)
				sql = "update pragas set nome_tipo=?, veneno=?, valor=? where id_praga=?";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, p.getNomeTipo());
				bd.st.setString(2, p.getVeneno());
				bd.st.setDouble(3, p.getValor());
				bd.st.setInt(4, p.getIdPraga());
				
				bd.st.executeUpdate();
				men = "Praga " + p.getNomeTipo() + " alterada com sucesso!";
			}
			
		} catch (SQLException erro) {
			men = "Falha ao salvar: " + erro.getMessage();
		} finally {
			bd.close();
		}
		return men;
	}
    
    /**
     * Realiza a exclusão de uma praga no banco de dados
     */
    public String excluir(Praga p) {
        sql = "delete from pragas where id_praga = ?";
        try {
            bd.getConnection();
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, p.getIdPraga());
            
            if (bd.st.executeUpdate() == 1) {
                men = "Praga excluída com sucesso!";
            } else {
                men = "Praga não encontrada!";
            }
        } catch (SQLException erro) {
            men = "Falha ao excluir: " + erro.getMessage();
        } finally {
            bd.close();
        }
        return men;
    }

    /**
     * Localiza uma praga pelo seu ID único de forma isolada e segura
     */
    public Praga localizar(int idPraga) {
        Praga p = null;
        sql = "SELECT id_praga, nome_tipo, veneno, valor FROM pragas WHERE id_praga = ?";
        
        PreparedStatement localSt = null;
        ResultSet localRs = null;
        
        try {
            bd.getConnection();
            localSt = bd.con.prepareStatement(sql);
            localSt.setInt(1, idPraga);
            localRs = localSt.executeQuery();
            
            if (localRs.next()) {
                p = new Praga();
                p.setIdPraga(localRs.getInt("id_praga"));
                p.setNomeTipo(localRs.getString("nome_tipo"));
                p.setVeneno(localRs.getString("veneno"));
                p.setValor(localRs.getDouble("valor"));
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao localizar praga: " + erro.getMessage());
            p = null;
        } finally {
            try {
                if (localRs != null) localRs.close();
                if (localSt != null) localSt.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar componentes locais: " + e.getMessage());
            }
            bd.close();
        }
        return p;
    }
}