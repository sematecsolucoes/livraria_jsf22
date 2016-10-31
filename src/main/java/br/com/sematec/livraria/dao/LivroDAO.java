package br.com.sematec.livraria.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.sematec.livraria.modelo.Autor;
import br.com.sematec.livraria.modelo.Livro;

public class LivroDAO extends DAO<Livro> {
	public static synchronized LivroDAO getInstance() {
		if (instancia == null) {
			instancia = new LivroDAO();
		}
		return instancia;
	}

	private static LivroDAO instancia;

	private LivroDAO() {
		super(Livro.class);
		geraDados();
	}

	@Override
	void geraDados() {
		AutorDAO autorDAO = AutorDAO.getInstance();
		List<Autor> assis = autorDAO.listaPorNome("assis");
		geraIdEAdiciona(new Livro("978-8-52-504464-8", "Dom Casmurro", parseData("10/01/1899"), 24.90, assis));
		geraIdEAdiciona(new Livro("978-8-50-815415-9", "Memorias Postumas de Bras Cubas", parseData("01/01/1881"), 19.90, assis));
		geraIdEAdiciona(new Livro("978-8-50-804084-1", "Quincas Borba", parseData("01/01/1891"), 16.90, assis));
		List<Autor> coelho = autorDAO.listaPorNome("coelho");
		geraIdEAdiciona(new Livro("978-8-57-542758-3", "O Alquimista", parseData("01/01/1988"), 19.90, coelho));
		geraIdEAdiciona(new Livro("978-8-50-567258-7", "Brida", parseData("01/01/1990"), 12.90, coelho));
		geraIdEAdiciona(new Livro("978-8-52-812458-8", "As Valkirias", parseData("01/01/1992"), 29.90, coelho));
		geraIdEAdiciona(new Livro("978-8-51-892238-9", "O Diario de um Mago", parseData("01/01/1987"), 9.90, coelho));
		List<Autor> amado = autorDAO.listaPorNome("amado");
		geraIdEAdiciona(new Livro("978-8-50-831169-1", "Capitaes da Areia", parseData("01/01/1937"), 6.90, amado));
		geraIdEAdiciona(new Livro("978-8-53-592569-9", "Dona Flor e Seus Dois Maridos", parseData("01/01/1966"), 18.90, amado));
	}

	private Calendar parseData(String data) {
		try {
			Calendar instance = Calendar.getInstance();
			instance.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(data));
			return instance;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}