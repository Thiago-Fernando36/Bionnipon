package piClasses;

import java.sql.SQLException;
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
        // SQL ajustado conforme as colunas reais da tabela Servico no banco de dados
        sql = "insert into servico (id_servico, id_usuario, data_inicio, data_fim, valor, endereco, cidade, cep) values (?,?,?,?,?,?,?,?)";
        try {
            bd.getConnection();
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, s.getIdServico());
            bd.st.setInt(2, s.getIdUsuario());
            bd.st.setString(3, s.getDataInicio());
            bd.st.setString(4, s.getDataFim());
            bd.st.setDouble(5, s.getValor());
            bd.st.setString(6, s.getEndereco());
            bd.st.setString(7, s.getCidade());
            bd.st.setString(8, s.getCEP());

            bd.st.executeUpdate();
            men = "Serviço " + s.getIdServico() + " inserido com sucesso!";
            
        } catch (SQLException erro) {
            // Se der erro (como chave duplicada), tenta atualizar o registro existente
            sql = "update servico set id_usuario=?, data_inicio=?, data_fim=?, valor=?, endereco=?, cidade=?, cep=? where id_servico=?";
            try {
                // Reaproveita a conexão ativa com segurança para o update
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
                men = "Serviço " + s.getIdServico() + " alterado com sucesso!";
                
            } catch (SQLException erro2) {
                men = "Falha ao atualizar: " + erro2.getMessage();
            }
            
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