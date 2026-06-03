package banco;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BD{
	public Connection con = null;
	public ResultSet rs = null;
	public PreparedStatement st = null;

	private final String DATABASENAME = "BionniponBD";
	private final String URL   = "jdbc:postgresql://localhost:5432/" + DATABASENAME;
	private final String LOGIN = "postgres";
	private final String SENHA = "Fernando36#$";

	// con = DriverManager.getConnection(URL,LOGIN,SENHA);
	
	public static void main(String[] args) {
		BD bd = new BD();
		bd.getConnection();
		bd.close();
	}
	/** 
	 * metodo que faz conexao com o banco de dados
	 * retorna true se houve sucesso, ou false em caso negativo
	 */
	public boolean getConnection(){
		try{
		//	Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,LOGIN,SENHA);
			System.out.println("Conectou");
			return true;
		}
		catch(SQLException erro){
				System.out.println(erro.toString());	
			return false;
		}
	}

	public void close(){
		try{
           if(rs!=null)
              rs.close();
		}
		catch(SQLException erro){}
		try{
           if(st!=null)
			  st.close();
		}
		catch(SQLException erro){} 
		try{
			con.close();
			System.out.println("Desconectou");
		}
		catch(SQLException erro){
			System.out.println(erro.toString());
		} 
	}  
}