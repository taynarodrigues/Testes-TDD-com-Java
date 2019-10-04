package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	
	public static void main(String[] args) {
	//parte 1: montar cenário -> Criando usuário, criando Leilão e propondo lances
		Usuario joao = new Usuario("João");
		Usuario jose = new Usuario("José");
		Usuario maria = new Usuario("Maria");
		
		Leilao leilao = new Leilao("Playdtation 3 Novo");
		
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		leilao.propoe(new Lance(maria, 250.0));
		
		
		//parte 2: Executa uma ação -> Invoca o método avalia
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//parte 3: Validação -> Imprime o resultado que o  método produziu
		System.out.println(leiloeiro.getMaiorLance());
		System.out.println(leiloeiro.getMenorLance());
		
		
	}
	

/*Teste manuais:
 * -Pensar em um cenário
 * -Executar uma ação
 * -Validar a saída*/

}
