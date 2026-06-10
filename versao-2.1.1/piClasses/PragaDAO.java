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
        sql = "insert into pragas (id_praga, nome_tipo, veneno, valor) values (?,?,?,?)";
        try {
            bd.getConnection();
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, p.getIdPraga());
            bd.st.setString(2, p.getNomeTipo());
            bd.st.setString(3, p.getVeneno());
            bd.st.setDouble(4, p.getValor());

            bd.st.executeUpdate();
            men = "Praga " + p.getNomeTipo() + " inserida com sucesso!";
            
        } catch (SQLException erro) {
            sql = "update pragas set nome_tipo=?, veneno=?, valor=? where id_praga=?";
            try {
                bd.st = bd.con.prepareStatement(sql);
                bd.st.setString(1, p.getNomeTipo());
                bd.st.setString(2, p.getVeneno());
                bd.st.setDouble(3, p.getValor());
                bd.st.setInt(4, p.getIdPraga());
                
                bd.st.executeUpdate();
                men = "Praga " + p.getNomeTipo() + " alterada com sucesso!";
                
            } catch (SQLException erro2) {
                men = "Falha ao atualizar: " + erro2.getMessage();
            }
            
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