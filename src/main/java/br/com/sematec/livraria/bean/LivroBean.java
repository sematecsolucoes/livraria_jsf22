package br.com.sematec.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import br.com.sematec.livraria.dao.AutorDAO;
import br.com.sematec.livraria.dao.LivroDAO;
import br.com.sematec.livraria.modelo.Autor;
import br.com.sematec.livraria.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	private Long autorId;
	private Long livroId;

	public void adicionarAutor() {
		Autor autor = AutorDAO.getInstance().buscaPorId(this.autorId);
		this.livro.adicionaAutor(autor);
		System.out.println("Escrito por: " + autor.getNome());
	}

	public void carregar(Livro livro) {
		System.out.println("Carregando livro " + livro.getTitulo());
		this.livro = livro;
	}

	public void carregarLivroPelaId() {
		this.livro = LivroDAO.getInstance().buscaPorId(livroId);
	}

	public void comecaCom978(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		if (!StringUtils.startsWithIgnoreCase((String) value, "978")) {
			throw new ValidatorException(new FacesMessage("ISBN deveria começar com 978"));
		}
	}

	public String formAutor() {
		System.out.println("Chamanda do formulario do Autor.");
		return "autor?faces-redirect=true";
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public Long getAutorId() {
		return autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	public Long getLivroId() {
		return livroId;
	}

	public List<Livro> getLivros() {
		return LivroDAO.getInstance().listaTodos();
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());
		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}
		if (this.livro.getId() == null) {
			LivroDAO.getInstance().adiciona(this.livro);
		} else {
			LivroDAO.getInstance().atualiza(this.livro);
		}
		init();
	}

	@PostConstruct
	private void init() {
		livro = new Livro();
		autorId = null;
	}

	public void remover(Livro livro) {
		System.out.println("Removendo livro " + livro.getTitulo());
		LivroDAO.getInstance().remove(livro);
	}

	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public void setLivroId(Long livroId) {
		this.livroId = livroId;
	}
}
