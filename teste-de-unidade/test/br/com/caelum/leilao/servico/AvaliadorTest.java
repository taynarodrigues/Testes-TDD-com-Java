package br.com.caelum.leilao.servico;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Ignore;

public class AvaliadorTest {
	@Ignore
	@Test
	public void DeveEntenderLancesEmOrdemCrescente() {
		//parte 1: montar cenário -> Criando usuário, criando Leilão e propondo lances
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playdtation 3 Novo");
		
		leilao.propoe(new Lance(joao, 250.0));
		leilao.propoe(new Lance(jose, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		
		//parte 2: Executa uma ação -> Invoca o método avalia
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//parte 3: Validação
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}
	
	@Ignore
	@Test
	public void deveCalcularMedia() {
		
		//cenario: 3 lances em ordem crescente
		Usuario joao = new Usuario("Joao");
		Usuario jorge = new Usuario("jorge");
		Usuario mari = new Usuario("mari");
		
		Leilao leilao = new Leilao("Playstation 3");
		
		leilao.propoe(new Lance(joao,300.0));
		leilao.propoe(new Lance(jorge,400.0));
		leilao.propoe(new Lance(mari,500.0));
		
		//executando a acao
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//comparando a saida com o esperado
		Assert.assertEquals(400, leiloeiro.getMedia(), 0.0001);
		
	}
	
	@Ignore
	@Test
	public void testaMediaDeZeroLance() {
		
		//cenario
		Usuario ewertom = new Usuario("Ewertom");
		
		//acao
		Leilao leilao = new Leilao("Iphone 7");
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		//validacao
		assertEquals(0, avaliador.getMedia(), 0.0001);
	}
	
	@Test
	public void deveEntenderLancesEmOrdemCrescenteComOutrosValores() {
		
		//parte 1: cenário
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		//ação
		Leilao leilao = new Leilao("Playstation 3 Novo");
		
		leilao.propoe(new Lance(joao, 1000.0));
		leilao.propoe(new Lance(jose, 2000.0));
		leilao.propoe(new Lance(maria, 3000.0));
		
		//parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//parte 3: validação
		Assert.assertEquals(3000.0, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
	}
}



