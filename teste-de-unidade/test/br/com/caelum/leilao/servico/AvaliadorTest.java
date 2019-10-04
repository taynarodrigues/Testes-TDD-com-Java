package br.com.caelum.leilao.servico;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import static org.junit.Assert.*;
import org.junit.Assert;

public class AvaliadorTest {
	
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
		
		
		//parte 3: Validação -> Imprime o resultado que o  método produziu
		//valores esperados
		double maiorEsperado = 400;
		double menorEsperado = 250;
		
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
		
		
	}
}

/*Teste manuais:
 * -Pensar em um cenário
 * -Executar uma ação
 * -Validar a saída
 * 
 * Adicionando JUnit no projeto como biblioteca
 * Botão direito do mouse em cima do projeto
 * Build Path
 * Add Libraries...
 * Selecionar JUnit 
 * Escolher JUnit 4, versão mais recente até o momento desse curso
 * Finish
 * 
 * 
 * O parâmetro com o valor 0.0001 é um delta, como o double tem problema de 
 * arrendondamento, o JUnit irá aceitar esta pequena diferença entre os valores
 * 
 * O pacote correto é o org.junit, e devemos sempre utilizá-lo.
 * O pacote junit.framework é o pacote da versão mais antiga do JUnit, e deve ser evitado.*/

