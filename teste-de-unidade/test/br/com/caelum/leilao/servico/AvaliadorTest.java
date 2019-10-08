package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@Before
	public void criaAvaliador() {
		
		this.leiloeiro = new Avaliador();
		System.out.println("inicializando teste!");
		 this.joao = new Usuario("João");
		 this.jose = new Usuario("José");
		 this.maria = new Usuario("Maria");
	}
	
	@After
	public void finaliza() {
		System.out.println("fim");
	}

	@Test
	public void DeveEntenderLancesEmOrdemCrescente() {
		
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 250.0)
				.lance(maria, 300.0)
				.lance(jose, 400.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}
	

	@Test
	public void deveCalcularMedia() {
	
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 300.0)
				.lance(maria, 400.0)
				.lance(jose, 500.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		assertEquals(400, leiloeiro.getMedia(), 0.0001);
		
	}
	

	@Test
	public void testaMediaDeZeroLance() {
		
		
		//acao
		Leilao leilao = new Leilao("Iphone 7");
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		//validacao
		assertEquals(0, avaliador.getMedia(), 0.0001);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 1000.0)
				.constroi();
		
		
		leiloeiro.avalia(leilao);
		
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
		
	}
	
	@Ignore
	@Test
	public void deveEncontarOsTresMaioresLances() {
	
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 1000.0)
				.lance(maria, 200.0)
				.lance(joao, 300.0)
				.lance(maria, 400.0)
				.constroi();
	

		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());

		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void deveDevolverTodosLancesCasohajaNoMinimo3() {

		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo ")
		
				.lance(joao, 100.0)
				.lance(maria, 200.0)
				.constroi();
	        
		leiloeiro.avalia(leilao);
		
		List<Lance>maiores = leiloeiro.getTresMaiores();
		
		assertEquals(2, maiores.size());
		assertEquals(200, maiores.get(0).getValor(), 0.00001);
		assertEquals(100, maiores.get(1).getValor(), 0.00001);
				
}
	@Ignore
	@Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
		
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
        		.constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }
	
//	@Ignore
	@Test
	public void deveEntenderleilaoComLancesEmOrdemRandomica() {
	
		Leilao leilao = new CriadorDeLeilao().para("Plastation 3 Novo")
				
				.lance(joao, 2000.0)
				.lance(maria, 450.0)
				.lance(jose, 120.0)
				.lance(maria, 700.0)
				.lance(jose, 630.0)
				.lance(joao, 230.0)
				.constroi();
	
		leiloeiro.avalia(leilao);
		
		assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
	}
	
	@Ignore
	@Test
	public void deveEntenderLeilaoComlancesEmOrdemDecrescente() {

		Leilao leilao = new CriadorDeLeilao().para("Plastation 3 Novo")
				
				.lance(joao, 400.0)
				.lance(maria, 300.0)
				.lance(joao, 200.0)
				.lance(joao, 100.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario billGates = new Usuario("Bill Gates");
		
		Leilao leilao = new CriadorDeLeilao().para("Macbook Pro 15")
				.lance(steveJobs, 2000)
				.lance(billGates, 3000)
                .lance(steveJobs, 4000)
                .lance(billGates, 5000)
                .lance(steveJobs, 6000)
                .lance(billGates, 7000)
                .lance(steveJobs, 8000)
                .lance(billGates, 9000)
                .lance(steveJobs, 10000)
                .lance(billGates, 11000)
                .lance(steveJobs, 12000)
                .constroi();
		assertEquals(10, leilao.getLances().size());
		int ultimo = leilao.getLances().size()-1;
		assertEquals(11000.0, leilao.getLances().get(ultimo).getValor(), 0.00001);
	}
}



