package Models;

public class Clientes {
	
	public static int id, cep, telefone;
	public static String nome, endereco, bairro, cidade, estado, detalhes;
	
	public Clientes() {
		super();
	}

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		Clientes.id = id;
	}

	public static int getCep() {
		return cep;
	}

	public static void setCep(int cep) {
		Clientes.cep = cep;
	}

	public static int getTelefone() {
		return telefone;
	}

	public static void setTelefone(int telefone) {
		Clientes.telefone = telefone;
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		Clientes.nome = nome;
	}

	public static String getEndereco() {
		return endereco;
	}

	public static void setEndereco(String endereco) {
		Clientes.endereco = endereco;
	}

	public static String getBairro() {
		return bairro;
	}

	public static void setBairro(String bairro) {
		Clientes.bairro = bairro;
	}

	public static String getCidade() {
		return cidade;
	}

	public static void setCidade(String cidade) {
		Clientes.cidade = cidade;
	}

	public static String getEstado() {
		return estado;
	}

	public static void setEstado(String estado) {
		Clientes.estado = estado;
	}

	public static String getDetalhes() {
		return detalhes;
	}

	public static void setDetalhes(String detalhes) {
		Clientes.detalhes = detalhes;
	}

}
