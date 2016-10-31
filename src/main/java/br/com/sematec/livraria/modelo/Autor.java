package br.com.sematec.livraria.modelo;

public class Autor extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private String email;

	public Autor() {
		this("","");
	}

	public Autor(String nome,String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
