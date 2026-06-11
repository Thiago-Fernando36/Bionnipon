package piClasses;

import java.sql.SQLException;
import java.sql.Timestamp;

import banco.BD;

public class ServicoDAO {
    private String sql, men;
    private BD bd;
    
    public ServicoDAO() {
        bd = new BD();
    }

    /**
     * Realiza a gravação de um serviço (Inserção ou Atualização se já existir)
     */
    public String salvar(Servico s) {
        try {
            bd.getConnection();
            
            if (s.getIdServico() == 0) {
                // INSERT: Omitimos o id_servico para o SERIAL funcionar
                sql = "insert into servico (id_usuario, data_inicio, data_fim, valor, endereco, cidade, cep) values (?,?,?,?,?,?,?)";
                bd.st = bd.con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
                bd.st.setInt(1, s.getIdUsuario());
                bd.st.setTimestamp(3, Timestamp.valueOf(s.getDataInicio() + ":00")); 
                bd.st.setTimestamp(4, Timestamp.valueOf(s.getDataFim() + ":00"));
                bd.st.setDouble(4, s.getValor());
                bd.st.setString(5, s.getEndereco());
                bd.st.setString(6, s.getCidade());
                bd.st.setString(7, s.getCEP());
                
                bd.st.executeUpdate();
                
                bd.rs = bd.st.getGeneratedKeys();
                if (bd.rs.next()) {
                    s.setIdServico(bd.rs.getInt(1)); // Atualiza o objeto com o ID gerado
                }
                men = "Serviço inserido com sucesso! (ID: " + s.getIdServico() + ")";
                
            } else {
                // UPDATE: Mantém o ID informado
                sql = "update servico set id_usuario=?, data_inicio=?, data_fim=?, valor=?, endereco=?, cidade=?, cep=? where id_servico=?";
                bd.st = bd.con.prepareStatement(sql);
                bd.st.setInt(1, s.getIdUsuario());
                bd.st.setString(2, s.getDataInicio());
                bd.st.setString(3, s.getDataFim());
                bd.st.setDouble(4, s.getValor());
                bd.st.setString(5, s.getEndereco());
                bd.st.setString(6, s.getCidade());
                bd.st.setString(7, s.getCEP());
                bd.st.setInt(8, s.getIdServico());
                
                bd.st.executeUpdate();
                men = "Serviço " + s.getIdServico() + " atualizado com sucesso!";
            }
        } catch (SQLException erro) {
            men = "Falha ao salvar: " + erro.getMessage();
        } finally {
            bd.close();
        }
        return men;
    }
    
    /**
     * Realiza a exclusão de um serviço no banco de dados
     */
    public String excluir(Servico s) {
        sql = "delete from servico where id_servico = ?";
        try {
            bd.getConnection();
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, s.getIdServico());
            
            if (bd.st.executeUpdate() == 1) {
                men = "Serviço excluído com sucesso!";
            } else {
                men = "Serviço não encontrado!";
            }
        } catch (SQLException erro) {
            men = "Falha ao excluir: " + erro.getMessage();
        } finally {
            bd.close();
        }
        return men;
    }

    /**
     * Localiza um serviço pelo seu ID único
     */
    public Servico localizar(int idServico) {
        Servico s = null;
        sql = "SELECT id_servico, id_usuario, data_inicio, data_fim, valor, endereco, cidade, cep FROM servico WHERE id_servico = ?";
        
        try {
            bd.getConnection();
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, idServico);
            bd.rs = bd.st.executeQuery();
            
            // O rs.next() posiciona o cursor na primeira linha retornada do banco
            if (bd.rs.next()) {
                s = new Servico();
                s.setIdServico(bd.rs.getInt("id_servico"));
                s.setIdUsuario(bd.rs.getInt("id_usuario"));
                s.setDataInicio(bd.rs.getString("data_inicio"));
                s.setDataFim(bd.rs.getString("data_fim"));
                s.setValor(bd.rs.getDouble("valor"));
                s.setEndereco(bd.rs.getString("endereco"));
                s.setCidade(bd.rs.getString("cidade"));
                s.setCEP(bd.rs.getString("cep"));
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao localizar serviço: " + erro.getMessage());
            s = null;
        } finally {
            bd.close();
        }
        return s;
    }
}