package br.ce.wcaquino.runners.steps;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import br.ce.wcaquino.runners.entidades.Filme;
import br.ce.wcaquino.runners.entidades.NotaAluguel;
import br.ce.wcaquino.runners.entidades.TipoAluguel;
import br.ce.wcaquino.runners.servicos.AluguelService;
import br.ce.wcaquino.runners.utils.DateUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AlugarFilmesSteps {

	private Filme filme;
	private AluguelService aluguel = new AluguelService();
	private NotaAluguel nota;
	private String erro;
	private TipoAluguel tipoAluguel;

	@Dado("^um filme com estoque de (\\d+) unidades$")
	public void umFilmeComEstoqueDeUnidades(int arg1) throws Throwable {
		filme = new Filme();
		filme.setEstoque(arg1);
	}

	@Dado("^que o preço do aluguel seja R\\$ (\\d+)$")
	public void queOPreçoDoAluguelSejaR$(int arg1) throws Throwable {
		filme.setAluguel(arg1);
	}

	@Dado("^um filme$")
	public void umFilme(DataTable table) throws Throwable {
		Map<String, String> map = table.asMap(String.class, String.class);
		filme = new Filme();
		filme.setEstoque(Integer.parseInt(map.get("estoque")));
		filme.setAluguel(Integer.parseInt(map.get("preço")));
		String tipo = map.get("tipo");
		tipoAluguel = tipo.equals("semanal") ? TipoAluguel.SEMANAL
				: tipo.equals("extendido") ? TipoAluguel.EXTENDIDO : TipoAluguel.COMUM;
	}

	@Quando("^alugar$")
	public void alugar() throws Throwable {
		try {
			nota = aluguel.alugar(filme, tipoAluguel);
		} catch (RuntimeException e) {
			erro = e.getMessage();
		}

	}

	@Então("^o preço do aluguel será R\\$ (\\d+)$")
	public void oPreçoDoAluguesSeráR$(int arg1) throws Throwable {
		assertEquals(arg1, nota.getPreço());
	}

	@Então("^o estoque do filme será (\\d+) unidade$")
	public void oEstoqueDoFilmeSeraUnidade(int arg1) throws Throwable {
		assertEquals(arg1, filme.getEstoque());
	}

	@Então("^não será possível por falta de estoque$")
	public void nãoSeráPossívelPorFaltaDeEstoque() throws Throwable {
		assertEquals("Filme sem estoque", erro);
	}

	@Dado("^que o tipo do aluguel seja (.*)$")
	public void queOTipoDoAluguelSejaExtendido(String tipo) throws Throwable {
		tipoAluguel = tipo.equals("semanal") ? TipoAluguel.SEMANAL
				: tipo.equals("extendido") ? TipoAluguel.EXTENDIDO : TipoAluguel.COMUM;
	}

	@Então("^a data de entrega será em (\\d+) dias?$")
	public void aDataDeEntregaSeráEmDias(int arg1) throws Throwable {
		Date dataEsperada = DateUtils.obterDataDiferencaDias(arg1);
		Date dataReal = nota.getDataEntrega();

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		assertEquals(format.format(dataEsperada), format.format(dataReal));
	}

	@Então("^a pontuação será de (\\d+) pontos$")
	public void aPontuaçãoSeráDePontos(int arg1) throws Throwable {
		assertEquals(arg1, nota.getPontuacao());
	}
}