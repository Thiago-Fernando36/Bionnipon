package piClasses;
/**
 * 
 */
public class Usuario {
    protected int id_usuario;
    protected String nome;
    protected String cpf;
    protected String telefone;
    protected String rua;
    protected String estado;
    protected String cidade;
    protected String cep;
    protected String login;
    protected String senha;

    public Usuario() {
		
	}
    public Usuario(int id_usuario, String nome, String cpf, String telefone, String rua, String estado, String cidade, String cep, String login, String senha) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.rua = rua;
        this.estado = estado;
        this.cidade = cidade;
        this.cep = cep;
        this.login = login;
        this.senha = senha;
    }
    
    public String toString() {
    	return "["+id_usuario+","+nome+","+cpf+","+telefone+","+rua+","+estado+","+cidade+","+cep+","+login+","+senha+"]";
    }

    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }
    public boolean fazerLogin(String login, String senha) {
    	boolean i;
    	if(this.login == login && this.senha == senha ) {
    		i = true;
    	}else {
    		i = false;
    	}
    	return i;
    }
    public int getIdUsuario() {
		return id_usuario;
	}

	public void setIdUsuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void alterarSenha(String novaSenha) {
    	this.senha = novaSenha;
    }
}