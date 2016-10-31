package br.com.sematec.livraria.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.com.sematec.livraria.modelo.Autor;

public class AutorDAO extends DAO<Autor> {
	public static synchronized AutorDAO getInstance() {
		if (instancia == null) {
			instancia = new AutorDAO();
		}
		return instancia;
	}

	private static AutorDAO instancia;

	private AutorDAO() {
		super(Autor.class);
		geraDados();
	}

	public Autor buscaPorNome(String nome) {
		Long i = 0L;
		boolean achou = false;
		Autor retorno = null;
		while (!achou && i < LISTA.size()) {
			if (StringUtils.containsIgnoreCase(LISTA.get(i).getNome(), nome)) {
				achou = true;
				retorno = LISTA.get(i);
			}
			i++;
		}
		return retorno;
	}

	@Override
	void geraDados() {
		geraIdEAdiciona(new Autor("Machado de Assis", "machado.assis@email.com"));
		geraIdEAdiciona(new Autor("Jorge Amado", "jorge.amado@email.com"));
		geraIdEAdiciona(new Autor("Paulo Coelho", "paulo.coelho@email.com"));
	}

	public List<Autor> listaPorNome(String nome) {
		Long i = 0l;
		List<Autor> retorno = new ArrayList<>();
		while (i < LISTA.size()) {
			if (StringUtils.containsIgnoreCase(LISTA.get(i).getNome(), nome)) {
				retorno.add(LISTA.get(i));
			}
			i++;
		}
		return retorno;
	}
}