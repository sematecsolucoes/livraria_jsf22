package br.com.sematec.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sematec.livraria.dao.AutorDAO;
import br.com.sematec.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {
	private Autor autor = new Autor();
	private Long autorId;

	public void carregar(Autor autor) {
		System.out.println("Carregando autor");
		this.autor = autor;
	}

	public void carregarAutorPelaId() {
		this.autor = AutorDAO.getInstance().buscaPorId(autorId);
	}

	public Autor getAutor() {
		return autor;
	}

	public List<Autor> getAutores() {
		return AutorDAO.getInstance().listaTodos();
	}

	public Long getAutorId() {
		return autorId;
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		if (this.autor.getId() == null) {
			AutorDAO.getInstance().adiciona(this.autor);
		} else {
			AutorDAO.getInstance().atualiza(this.autor);
		}
		this.autor = new Autor();
		return "livro?faces-redirect=true";
	}

	public void remover(Autor autor) {
		System.out.println("Removendo autor");
		AutorDAO.getInstance().remove(autor);
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}
}
