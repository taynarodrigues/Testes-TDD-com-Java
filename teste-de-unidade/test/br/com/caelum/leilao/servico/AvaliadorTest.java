package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

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
		assertEquals(400, leiloeiro.getMedia(), 0.0001);
		
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
	
	@Ignore
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		//parte 1: montar cenário -> Criando usuário, criando Leilão e propondo lances
		Usuario joao = new Usuario("João");
		
		Leilao leilao = new Leilao("Playstation 3 Novo");
		
		
		leilao.propoe(new Lance(joao, 1000.0));
		
		//parte 2: Executa uma ação -> Invoca o método avalia
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(1000.0, leiloeiro.getMenorLance(), 0.00001);
		
	}
	
	//Nova Regra: Calcular 3 maiores lances para o Leilão
	@Test
	public void deveEncontarOsTresMaioresLances() {
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Playstation 3 Novo");
		
		//propor Lances
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		//Criar o leiloeiro 
		Avaliador leiloeiro = new Avaliador();
		//e invocar o método avalia, o qual queremos testar
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		//verificar o conteúdo da lista
		assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void deveDevolverTodosLancesCasohajaNoMinimo3() {
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Playstation 3 Novo ");
		
		 leilao.propoe(new Lance(joao, 100.0));
	     leilao.propoe(new Lance(maria, 200.0));  
	        
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance>maiores = leiloeiro.getTresMaiores();
		
		assertEquals(2, maiores.size());
		assertEquals(200, maiores.get(0).getValor(), 0.00001);
		assertEquals(100, maiores.get(1).getValor(), 0.00001);
				
}
	
	@Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }
	
	
	@Test
	public void deveEntenderleilaoComLancesEmOrdemRandomica() {
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Plastation 3 Novo");
		
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 450.0));
		leilao.propoe(new Lance(joao, 120.0));
		leilao.propoe(new Lance(maria, 700.0));
		leilao.propoe(new Lance(joao, 630.0));
		leilao.propoe(new Lance(maria, 230.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
	}
	
	@Test
	public void deveEntenderLeilaoComlancesEmOrdemDecrescente() {
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Playstation 3 novo");
		
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 100.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
	}
}

/*Testes repetitivos, na área de teste de software,
 * chamamos de equivalência, ou seja,
 * Precisamos de um teste por classe de equivalência.
 * 
 * 
 * O método assertEquals() é estático, e portanto, 
 * podemos importá-lo de maneira estática! 
 * Basta fazer uso do import static! 
 * 
 * DICA DE ATALHO
 * ctrl + F6, para mudar de aba.
 * ctrl + Shift + à letra O* serve para importar o arraylist*/

